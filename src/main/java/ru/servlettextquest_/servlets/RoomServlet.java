package ru.servlettextquest_.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import ru.servlettextquest_.classes.*;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.repository.RoomRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(name = "RoomServlet", value = "/room")
public class RoomServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(RoomServlet.class);

    private RoomRepository roomRepository = null;
    private Repository<Integer, Npc> npcRepository = null;
    private Repository<Integer, Item> itemRepository = null;

    private Repository<Integer, Quest> questRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        roomRepository = (RoomRepository) servletContext.getAttribute("roomRepository");
        npcRepository = (Repository<Integer, Npc>) servletContext.getAttribute("npcRepository");
        itemRepository = (Repository<Integer, Item>) servletContext.getAttribute("itemRepository");
        questRepository = (Repository<Integer, Quest>) servletContext.getAttribute("questRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Integer currentRoomId = user.getCurrentRoomId();
        Room currentRoom = roomRepository.getById(currentRoomId);

        req.setAttribute("currentRoom", currentRoom);

        Map<Integer, String> doors =

                roomRepository.getById(currentRoomId)
                        .getDoors()
                        .stream()
                        .map(Door::getRoomId)
                        .collect(Collectors.toMap(Function.identity(), roomId -> roomRepository.getById(roomId).getName()));


        List<RoomInfo> doorInfo = doors
                .entrySet()
                .stream()
                .map(s -> RoomInfo.builder().id(s.getKey()).name(s.getValue()).build()).toList();
        req.setAttribute("doorInfo", doorInfo);


        List<Npc> npcs = new ArrayList<>();
        for (Integer npcId : currentRoom.getNpcs()) {
            Npc npc = npcRepository.getById(npcId);
            npcs.add(npc);
        }

        req.setAttribute("npcs", npcs);

        List<Item> items = new ArrayList<>();
        for (Integer itemId : currentRoom.getItems()) {
            Item item = itemRepository.getById(itemId);
            items.add(item);
        }

        req.setAttribute("items", items);


        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/room.jsp");
        requestDispatcher.forward(req, resp);




    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        if (req.getParameter("nextRoomId") != null) {
            Integer nextRoomId = Integer.parseInt(req.getParameter("nextRoomId"));

            user.setCurrentRoomId(nextRoomId);
            LOGGER.info(
                    new ParameterizedMessage("Пользователь: {}, перешел в комнату: {}",
                            user.getUsername(), roomRepository.getById(nextRoomId).getName()));
         }

        if (req.getParameter("addItemId") != null) {
            Integer itemId = Integer.parseInt(req.getParameter("addItemId"));
            Item curItem = itemRepository.getById(itemId);

            if (curItem.getStrength() != null)
                user.setStrength(user.getStrength() + curItem.getStrength());
            if (curItem.getDexterity() != null)
                user.setDexterity(user.getDexterity() + curItem.getDexterity());
            if (curItem.getLife() != null)
                user.setLife(user.getLife() + curItem.getLife());


            user.addItem(itemId);

            LOGGER.info(
                    new ParameterizedMessage("Пользователь: {}, поднял: {}",
                            user.getUsername(), curItem.getName()));

            //Item curItem = itemRepository.getById()

            boolean isAllQuestFinished = true;
            for (int i = 1; i <= questRepository.size() ; i++) {
                if (!questRepository.getById(i).isFinished(user)){
                    isAllQuestFinished = false;
                    break;
                }
                if (user.getUserQuests().size()==0){
                    isAllQuestFinished = false;
                    break;
                } else {
                    if (!user.getUserQuests().contains(i)){
                        isAllQuestFinished = false;
                        break;
                    }
                }

            }
            if (isAllQuestFinished){
                user.setIsGameOver(true);
                LOGGER.info(
                        new ParameterizedMessage("Пользователь: {}, завершил игру.",
                                user.getUsername()));
            }

        }


        resp.sendRedirect("room");


    }


}

package ru.servlettextquest_.servlets;

import ru.servlettextquest_.classes.*;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.repository.RoomRepository;

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
    private RoomRepository roomRepository = null;
    private Repository<Integer, Npc> npcRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        roomRepository = (RoomRepository) servletContext.getAttribute("roomRepository");
        npcRepository = (Repository<Integer, Npc>) servletContext.getAttribute("npcRepository");
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
        for (Integer npcId : currentRoom.getNpcs()){
            Npc npc = npcRepository.getById(npcId);
            npcs.add(npc);
        }

        req.setAttribute("npcs", npcs);

        getServletContext()
                .getRequestDispatcher("/room.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String nextRoomId = req.getParameter("nextRoomId");
        user.setCurrentRoomId(Integer.parseInt(nextRoomId));
        resp.sendRedirect("room");


    }


}

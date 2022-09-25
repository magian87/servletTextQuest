package ru.servlettextquest_.servlets;

import ru.servlettextquest_.classes.*;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.repository.RoomRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        InitGame initGame = new InitGame();

        ServletContext servletContext = servletContextEvent.getServletContext();

        //Repository<Integer, Room> roomRepository = new Repository<>();
        RoomRepository roomRepository = new RoomRepository();

        Repository<Integer, Npc> npcRepository = new Repository<>();
        Repository<Integer, Question> questionRepository = new Repository<>();
        Repository<Integer, Quest> questRepository = new Repository<>();
        Repository<Integer, Item> itemRepository = new Repository<>();


        for(Room room: initGame.receiveRooms()){
            roomRepository.save(room.getId(), room);
        }
        servletContext.setAttribute("roomRepository", roomRepository);

        for(Npc npc: initGame.receiveNpcs()){
            npcRepository.save(npc.getId(), npc);
        }
        servletContext.setAttribute("npcRepository", npcRepository);

        for(Question question: initGame.receiveQuestions()){
            questionRepository.save(question.getId(), question);
        }
        servletContext.setAttribute("questionRepository", questionRepository);

        for(Quest quest: initGame.receiveQuests() ) {
            questRepository.save(quest.getId(), quest);
        }
        servletContext.setAttribute("questRepository", questRepository);

        for(Item item: initGame.receiveItems()){
            itemRepository.save(item.getId(), item);
        }
        servletContext.setAttribute("itemRepository", itemRepository);
    /*

                try {
            URL resource = ctx.getResource("/config.json");
            List<String> strings = Files.readAllLines(Path.of(resource.getPath()));
            System.out.println(strings);

            URL resource2 = getClass().getClassLoader().getResource("file.txt");
            List<String> strings2 = Files.readAllLines(Path.of(resource2.getPath()));
            System.out.println(strings2);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        ServletContextListener.super.contextDestroyed(sce);
    }
}

package ru.servlettextquest_.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.servlettextquest_.classes.*;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.repository.RoomRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServletTest {
    @Mock
    private ServletConfig servletConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    @Mock
    private ServletContext servletContext;

    @Mock
    private User user;
    @Mock
    private RequestDispatcher dispatcher;

    @Spy
    private RoomServlet roomServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        Item item = Item.builder()
                .id(1)
                .name("Меч")
                .strength(30)
                .dexterity(60)
                .life(90)
                .build();

        Repository<Integer, Item> itemRepository = new Repository<>();
        itemRepository.save(item.getId(), item);
        when(servletContext.getAttribute(argThat("itemRepository"::equals)))
                .thenReturn(itemRepository);

        Npc npc = Npc.builder()
                .id(1)
                .name("Гэндальф")
                .build();
        Repository<Integer, Npc> npcRepository = new Repository<>();
        npcRepository.save(npc.getId(), npc);
        when(servletContext.getAttribute(argThat("npcRepository"::equals)))
                .thenReturn(npcRepository);

        RoomRepository roomRepository = new RoomRepository();

        Room garden = Room.builder()
                .id(2)
                .name("Сад")
                .npcs(List.of(1))
                .items(List.of(1, 4))
                .build();

        Room hotel = Room.builder()
                .id(3)
                .name("Отель")
                .items(List.of(2))
                .build();

        garden.addDoor(Door.builder().roomId(hotel.getId()).build());


        roomRepository.save(garden.getId(), garden);
        roomRepository.save(hotel.getId(), hotel);

        when(servletContext.getAttribute(argThat("roomRepository"::equals)))
                .thenReturn(roomRepository);

        roomServlet.init(servletConfig);
    }

    @Test
    void testDoPost_WhenParameterUserIsExists_ShouldUserInSession() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);
        roomServlet.doPost(request, response);
    }

    @Test
    void testDoPost_WhenParameterNextRoomIdIsExists_ShouldUserIdSetCurrentRoomId() throws ServletException, IOException {
        when(request.getParameter(argThat("nextRoomId"::equals)))
                .thenReturn("2");

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);

        roomServlet.doPost(request, response);
        verify(user).setCurrentRoomId(2);
        verify(response).sendRedirect(argThat("room"::equals));
    }

    @Test
    void testDoPost_WhenParameterAddItemIdIsExists_ShouldAddItemToUser() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);

        User user1 = spy(User.class);
        user1.setLife(100);
        user1.setDexterity(100);
        user1.setStrength(100);

        when(httpSession.getAttribute("user")).thenReturn(user1);


        when(request.getParameter(argThat("nextRoomId"::equals)))
                .thenReturn(null);

        when(request.getParameter(argThat("addItemId"::equals)))
                .thenReturn("1");


        roomServlet.doPost(request, response);
        verify(user1).addItem(1);

        Item item = mock(Item.class);

        assertEquals(130, user1.getStrength());


        assertEquals(160, user1.getDexterity());

        assertEquals(190, user1.getLife());
        verify(response).sendRedirect(argThat("room"::equals));
    }

    @Test
    void testDoGet_XXX() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        User user1 = spy(User.class);
        user1.setCurrentRoomId(2);
        when(httpSession.getAttribute("user")).thenReturn(user1);

        when(servletContext.getRequestDispatcher("/room.jsp")).thenReturn(dispatcher);

        roomServlet.doGet(request, response);


        verify(dispatcher).forward(request, response);


    }
}

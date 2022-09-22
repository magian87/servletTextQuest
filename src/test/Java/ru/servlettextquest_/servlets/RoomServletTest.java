package ru.servlettextquest_.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.servlettextquest_.classes.Item;
import ru.servlettextquest_.classes.Npc;
import ru.servlettextquest_.classes.User;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.repository.RoomRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

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

    @Spy
    private RoomServlet roomServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        Item item = Item.builder()
                .id(1)
                .name("Меч")
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
        when(request.getParameter(argThat("nextRoomId"::equals)))
                .thenReturn(null);

        when(request.getParameter(argThat("addItemId"::equals)))
                .thenReturn("1");

        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);

        roomServlet.doPost(request, response);
        verify(user).addItem(1);
        verify(response).sendRedirect(argThat("room"::equals));
    }

   /* @Test
    void testDoGet_XXX() throws ServletException, IOException {
        when(request.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("user")).thenReturn(user);

        roomServlet.doGet(request, response);

    }*/
}

package ru.servlettextquest_.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.servlettextquest_.classes.User;
import ru.servlettextquest_.servlets.EntranceServlet;

import static org.mockito.Mockito.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class EntranceServletTest {

    @Mock
    private ServletConfig servletConfig;
    @Mock
    private ServletContext servletContext;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpSession httpSession;
    @Mock
    private User user;

    //@Spy
    private EntranceServlet entranceServlet = new EntranceServlet();

    @BeforeEach
    void initial() throws ServletException {
        when(servletConfig.getServletContext()).thenReturn(servletContext);

        entranceServlet.init(servletConfig);
    }

    @Test
    void testDoPost() throws IOException, ServletException {
        when(request.getParameter(argThat("username"::equals)))
                .thenReturn("name");
        when(request.getSession()).thenReturn(httpSession);
        httpSession.setAttribute("user",user);
        entranceServlet.doPost(request, response);
        verify(response).sendRedirect("room");

    }
}

package ru.servlettextquest_.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.BDDMockito.*;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.servlettextquest_.servlets.DialogServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class DialogServletTest {

    @Mock
    private ServletConfig servletConfig;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession httpSession;

    @Spy
    private DialogServlet dialogServlet;

   /* @BeforeEach
    void setup() throws ServletException{
        dialogServlet.init(servletConfig);
    }*/

    @Test
    void testDoGet_WhenParameterFinishIsExists_ShouldSendRedirect() throws ServletException, IOException {
        when(request.getParameter(argThat("finish"::equals)))
                .thenReturn("true");

        //given(request.getParameter(argThat("finish"::equals)))
        //               .willReturn("true");

        //doReturn("true")
        //        .when(request)
        //               .getParameter(argThat(s -> "finish".equals(s)));


        dialogServlet.doGet(request, response);
        verify(response).sendRedirect(argThat("room"::equals));
    }

    @Test
    void testDoGet_WhenParameterQuestIsExists_ShouldSendRedirect() throws ServletException, IOException {
        //doReturn("1")
       //         .when(request)
       //         .getParameter(argThat("quest"::equals));
        when(request.getParameter(argThat ("quest"::equals)))
             .thenReturn("1");

        //given(request.getParameter(startsWith("que")))
        //       .will("true");


        dialogServlet.doGet(request, response);
        verify(response).sendRedirect(argThat("room"::equals));

    }


}

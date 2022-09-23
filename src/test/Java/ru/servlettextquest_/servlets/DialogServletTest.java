package ru.servlettextquest_.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.servlettextquest_.classes.Question;
import ru.servlettextquest_.classes.User;
import ru.servlettextquest_.repository.Repository;
import ru.servlettextquest_.servlets.DialogServlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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

    @Mock
    private ServletContext servletContext;

    @Mock
    private RequestDispatcher dispatcher;

    @Spy
    private DialogServlet dialogServlet;


    @BeforeEach
    void setup() throws ServletException{
        Question question_ = Question.builder()
                .id(1)
                .text("Привет?")
                .answers(List.of(
                        Question.Answer.builder()
                                .text("Здорово приятель!")
                                .nextQuestion(2)
                                .build(),
                        Question.Answer.builder()
                                .text("Я тороплюсь, пока!")
                                .build()
                ))
                .build();


        Repository<Integer, Question> questionRepository = new Repository<>();
        questionRepository.save(question_.getId(), question_);


        when(servletConfig.getServletContext()).thenReturn(servletContext);

        when(servletContext.getAttribute(argThat("questionRepository"::equals)))
                //.thenReturn(new Repository<Integer, Question>());
                .thenReturn(questionRepository);

        dialogServlet.init(servletConfig);
    }

    @Test
    void testDoGet_WhenParameterFinishIsExists_ShouldSendRedirect() throws ServletException, IOException {
        when(request.getParameter(argThat("finish"::equals)))
                .thenReturn("true");
         dialogServlet.doGet(request, response);
        verify(response).sendRedirect(argThat("room"::equals));
    }

    @Test
    void testDoGet_WhenParameterQuestIsExists_ShouldSendRedirect() throws ServletException, IOException {
        when(request.getParameter(argThat("finish":: equals)))
                .thenReturn(null);

        when(request.getParameter(argThat("quest"::equals)))
                .thenReturn("1");

        when(request.getSession()).thenReturn(httpSession);
        User user = mock(User.class);
        when(httpSession.getAttribute("user")).thenReturn(user);

        dialogServlet.doGet(request, response);
        verify(user).addQuest(1);
        verify(response).sendRedirect(argThat("room"::equals));
    }

    @Test
    void testDoGet_WhenParameterQuestionIsExists_ShouldSendRedirect() throws ServletException, IOException {
        when(request.getParameter(argThat("finish":: equals)))
                .thenReturn(null);

        when(request.getParameter(argThat("quest":: equals)))
                .thenReturn(null);

        when(request.getParameter(argThat("question"::equals)))
                .thenReturn("1");

        //RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(servletContext.getRequestDispatcher("/dialog.jsp")).thenReturn(dispatcher);

        dialogServlet.doGet(request, response);
        verify(dispatcher).forward(request, response);


    }
    @Test
    void testDoPost() throws IOException, ServletException {
        when(request.getParameter(argThat("questionId"::equals)))
                .thenReturn("1");
        //verify(response).sendRedirect(argThat("room"::equals));
        //verify(response).sendRedirect(argThat("dialog?question=1" ::equals));
        dialogServlet.doPost(request, response);
        verify(response).sendRedirect(startsWith("dialog?question=1"));

    }


}

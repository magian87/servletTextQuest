package ru.servlettextquest_.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import ru.servlettextquest_.classes.Quest;
import ru.servlettextquest_.classes.Question;
import ru.servlettextquest_.classes.User;
import ru.servlettextquest_.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DialogServlet", value = "/dialog")
//@WebServlet(name = "DialogServlet", value = "/dialog", initParams = @WebInitParam(name = "location", value = "D:/Uploads"))
public class DialogServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DialogServlet.class);

    private Repository<Integer, Question> questionRepository = null;
    private Repository<Integer, Quest> questRepository = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();

        questionRepository = (Repository<Integer, Question>) servletContext.getAttribute("questionRepository");
        questRepository = (Repository<Integer, Quest>) servletContext.getAttribute("questRepository");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(req.getParameter("finish") != null) {
            resp.sendRedirect("room");

            LOGGER.info(
                    new ParameterizedMessage("Пользователь: {}, завершил диалог.",
                            user.getUsername()));
            return;
        }

        if(req.getParameter("quest") != null) {
            Integer questId = Integer.parseInt(req.getParameter("quest"));

            user.addQuest(questId);

            LOGGER.info(
                    new ParameterizedMessage("Пользователь: {}, взял квест {}.",
                            user.getUsername(), questRepository.getById(questId).getText()));


            resp.sendRedirect("room");
            return;
        }

        String questionId = req.getParameter("question");
        Question question_ = questionRepository.getById(Integer.parseInt(questionId));
        req.setAttribute("question", question_);

        /*getServletContext()
                .getRequestDispatcher("/dialog.jsp")
                .forward(req, resp);*/

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/dialog.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String questionId = req.getParameter("questionId");
        Question question = questionRepository.getById(Integer.parseInt(questionId));
        resp.sendRedirect("dialog?question="+Integer.parseInt(questionId));

  /*      getServletContext()
                //.getRequestDispatcher("/WEB-INF/jsp/dialog.jsp")
                .getRequestDispatcher("/dialog.jsp")
                .forward(req, resp);*/
    }


}

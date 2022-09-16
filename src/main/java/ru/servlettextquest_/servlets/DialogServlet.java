package ru.servlettextquest_.servlets;

import ru.servlettextquest_.classes.Question;
import ru.servlettextquest_.classes.User;
import ru.servlettextquest_.repository.Repository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DialogServlet", value = "/dialog", initParams = @WebInitParam(name = "location", value = "D:/Uploads"))
public class DialogServlet extends HttpServlet {

    private Repository<Integer, Question> questionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        questionRepository = (Repository<Integer, Question>) servletContext.getAttribute("questionRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("finish") != null) {
            resp.sendRedirect("room");
            return;
        }

        if(req.getParameter("quest") != null) {
            String questId = req.getParameter("quest");

            User user = (User) req.getSession().getAttribute("user");

            user.getQuests().add(Integer.parseInt(questId));

            resp.sendRedirect("room");
            return;
        }

        String questionId = req.getParameter("question");
        Question question = questionRepository.getById(Integer.parseInt(questionId));
        req.setAttribute("question", question);

        getServletContext()
                .getRequestDispatcher("/dialog.jsp")
                .forward(req, resp);
        //resp.sendRedirect("dialog?question="+questionId);
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        String questionId = req.getParameter("questionId");
        Question question = questionRepository.getById(Integer.parseInt(questionId));
        //req.setAttribute("question", question);
        resp.sendRedirect("dialog?question="+Integer.parseInt(questionId));

  /*      getServletContext()
                //.getRequestDispatcher("/WEB-INF/jsp/dialog.jsp")
                .getRequestDispatcher("/dialog.jsp")
                .forward(req, resp);*/
    }


}

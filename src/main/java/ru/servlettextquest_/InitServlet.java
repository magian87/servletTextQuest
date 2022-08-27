package ru.servlettextquest_;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.servlettextquest_.settings.QuestionSettings;
import ru.servlettextquest_.settings.QuestionsSettings;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//@WebServlet(name = "LogicServlet", value = "/logic")
@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        Questions questions = new Questions();
        QuestionsSettings questionsSettings = questions.getQuestionsSettings();

        // Создание новой сессии
        HttpSession currentSession = req.getSession(true);
        // Добавление в сессию параметров поля (нужно будет для хранения состояния между запросами)
        currentSession.setAttribute("questionsSettings", questionsSettings);
        //currentSession.setAttribute("currentNumQuestion", 0);
        currentSession.setAttribute("currentQuestion", questionsSettings.getQuestionSettings()[0]);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

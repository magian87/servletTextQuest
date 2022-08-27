package ru.servlettextquest_;

import ru.servlettextquest_.settings.QuestionSettings;
import ru.servlettextquest_.settings.QuestionsSettings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

@WebServlet(name = "LogicServlet", value = "/logic")
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        // Получаем текущую сессию
        HttpSession currentSession = req.getSession();

        QuestionsSettings questionsSettings = (QuestionsSettings)  currentSession.getAttribute("questionsSettings");

        int index = getSelectedIndex(req);

        //ничего не делаем и отправляем пользователя на ту же страницу без изменений
        // параметров в сессии
        if (index==0){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        QuestionSettings questionSettings =

        Arrays.stream(questionsSettings.getQuestionSettings()).toList()
                .stream().filter(s -> s.getNum() == index)
                .findAny()
                .get();

        //int newCurrentQuestion = index; //questionSettings.getNum();

        //currentQuestion++;

        //currentSession.setAttribute("currentNumQuestion", newCurrentQuestion);
        currentSession.setAttribute("currentQuestion", questionSettings );



        resp.sendRedirect("/index.jsp");
        //getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    private int getSelectedIndex(HttpServletRequest request){
        String click = request.getParameter("click");
        int result;

        try {
            result = Integer.parseInt(click);
        } catch (NumberFormatException e) {
            result = 0;
            //throw new RuntimeException(e);
        }
        return result;


        //boolean isNumeric = click.chars().allMatch(Character::is Digit);
        //return isNumeric?Integer.parseInt(click):0;
    }
}

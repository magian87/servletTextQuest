package ru.servlettextquest_.servlets;

import ru.servlettextquest_.classes.Room;
import ru.servlettextquest_.classes.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EntranceServlet", value = "/entrance")
public class EntranceServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");

        HttpSession session = req.getSession();

        User user = new User();
        user.setUsername(userName);
        user.setCurrentRoomId(1);


        session.setAttribute("user", user);

        resp.sendRedirect("room");

        //super.doPost(req, resp);
    }


}

package view.controller.impl;

import model.User;
import model.dto.Order;
import service.OrderService;
import service.UserService;
import service.impl.OrderServiceImpl;
import service.impl.UserServiceImpl;
import view.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateUserController implements Controller {
    private UserService userService = UserServiceImpl.getInstance();


        @Override
        public void execute (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (login == null || password == null) {
                resp.setHeader("errorMsg", "Invalid Login or Password");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
                return;
            }
            User user = new User();

            if (login != null && password != null) {
                user.setName(login);
                user.setPassword(password);
                userService.addUser(user);
                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath+ "/frontController?command=login");
            } else {
                resp.setHeader("errorMsg", "Error");
                req.setAttribute("errorMsg", "Error");
                RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
                dispatcher.forward(req, resp);
            }
        }
    }










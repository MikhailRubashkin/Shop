package view.controller.impl;

import service.ScooterService;
import service.impl.ScooterServiceImpl;
import view.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ScooterController implements Controller {
    private ScooterService scooterService = ScooterServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("scooters", scooterService.getScooters());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}

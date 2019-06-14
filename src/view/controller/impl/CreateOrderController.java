package view.controller.impl;

import model.Bunch;

import model.Order;
import model.User;
import service.BunchService;
import service.OrderService;
import service.impl.BunchServiceImpl;
import service.impl.OrderServiceImpl;
import view.controller.Controller;
import view.vo.BasketVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderController implements Controller {
    private OrderService orderService = OrderServiceImpl.getInstance();
    private BunchService bunchService = BunchServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Order order = null;
        if (user != null) {
            BasketVO basket = (BasketVO) req.getSession().getAttribute("basket");
            final List<Bunch> bunches = basket.getBasket().entrySet().stream().map(entry -> new Bunch(entry.getKey(), entry.getValue().get())).collect(Collectors.toList());
            order = orderService.createOrder(user.getId(), bunches);
        }
        req.setAttribute("order", order);
        req.getSession().setAttribute("basket", null);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
package view.controller.impl;


import com.google.gson.Gson;
import model.User;
import view.controller.Controller;
import view.vo.BasketVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class AddToBasketController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int currentCount = 0;
        if (user != null) {
            BasketVO basket = (BasketVO) req.getSession().getAttribute("basket");
            if (basket == null) {
                basket = new BasketVO(new HashMap<>());
                req.getSession().setAttribute("basket", basket);
            }
            long scooterId = Long.parseLong(req.getParameter("scooter_id"));
            AtomicInteger count = basket.getBasket().get(scooterId);
            if (count == null) {
                count = new AtomicInteger();
                count.set(1);
                currentCount = 1;
            } else {
                currentCount = count.incrementAndGet();
            }
            basket.getBasket().put(scooterId, count);
        }
        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }
}

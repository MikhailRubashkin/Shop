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
import java.util.concurrent.atomic.AtomicInteger;

public class RemoveFromBasketController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        int currentCount = 0;
        if (user != null) {
            BasketVO basket = (BasketVO) req.getSession().getAttribute("basket");
            if (basket != null) {
                long scooterId = Long.parseLong(req.getParameter("scooter_id"));
                AtomicInteger count = basket.getBasket().get(scooterId);
                if (count != null && count.get() > 0) {
                    currentCount = count.decrementAndGet();
                }
            }
        }
        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(currentCount));
    }
}

package service;


import model.Bunch;
import model.Order;

import java.util.List;

public interface OrderService {

    Order getOrder(int id);

    List<Order> getOrders();

    Order addOrder(Order order);

    void deleteOrder(int id);

    void updateOrder(Order order);

    List<model.dto.Order> getByUserId(Integer user_id);

    Order createOrder(Integer userId, List<Bunch> bunches);
}

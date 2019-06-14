package service.impl;

import dao.repository.OrderRepository;
import dao.repository.impl.OrderRepositoryImpl;
import model.Bunch;
import model.Order;
import model.Scooter;
import service.BunchService;
import service.OrderService;
import service.ScooterService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderServiceImpl extends AbstractService implements OrderService {

    private static OrderServiceImpl instance;
    private OrderRepository orderRepository = OrderRepositoryImpl.getInstance();
    private BunchService bunchService = BunchServiceImpl.getInstance();
    private ScooterService scooterService = ScooterServiceImpl.getInstance();

    private OrderServiceImpl() {
    }

    @Override
    public List<model.dto.Order> getByUserId(Integer user_id) {
        try {
            final List<Order> orders = orderRepository.getByUserId(user_id);
            List<model.dto.Order> result = new ArrayList<>();
            orders.forEach(order -> {
                final List<Bunch> bunches = bunchService.getByOrderId(order.getId());
                final Map<Scooter, Integer> scooters = bunches.stream().collect(Collectors.toMap(bunch -> scooterService.getScooter(bunch.getScooter_id()), Bunch::getQuantity));
                model.dto.Order orderDto = new model.dto.Order(order.getId(), scooters);
                final double price = orderDto.getScooters().entrySet().stream().mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
                orderDto.setPrice(price);
                result.add(orderDto);
            });
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order createOrder(Integer userId, List<Bunch> bunches) {
        Order order = new Order(userId);
        try {
            startTransaction();
            order = addOrder(order);
            final Integer orderId = order.getId();
            bunches.forEach(bunch -> {
                bunch.setOrder_id(orderId);
                bunchService.addBunch(bunch);
            });
            commit();
        } catch (SQLException e) {
            rollback();
        }
        return order;
    }

    public static OrderServiceImpl getInstanse() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public Order getOrder(int id) {
        try {
            return orderRepository.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        try {
            final List<Order> result = orderRepository.getAll();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Order addOrder(Order order) {
        try {
            return orderRepository.save(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return null;
    }


    @Override
    public void deleteOrder(int id) {
        try {
            orderRepository.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(Order order) {
        try {
            orderRepository.update(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

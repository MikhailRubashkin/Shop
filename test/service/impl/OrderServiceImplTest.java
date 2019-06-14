package service.impl;

import model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderServiceImplTest {

    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        orderService = OrderServiceImpl.getInstanse();
    }

    @Test
    public void getInstanse() {
        Assert.assertNotNull(orderService);
    }

    @Test
    public void getOrder() {
        Order order = new Order();
        final Order newOrder = orderService.addOrder(order);
        Assert.assertNotNull(newOrder);
        final Order foundOrder = orderService.getOrder(newOrder.getId());
        Assert.assertNotNull(foundOrder);
        Assert.assertEquals(foundOrder, newOrder);
        orderService.deleteOrder(newOrder.getId());
    }

    @Test
    public void getOrders() {
        Order order = new Order();
        final Order newOrder = orderService.addOrder(order);
        Assert.assertNotNull(newOrder);
        final List<Order> orders = orderService.getOrders();
        Assert.assertTrue(orders.size() > 0);
        orderService.deleteOrder(newOrder.getId());
    }

    @Test
    public void addOrder() {
        Order order = new Order();
        final Order newOrder = orderService.addOrder(order);
        Assert.assertNotNull(newOrder);
        Assert.assertEquals(order, newOrder);
        orderService.deleteOrder(newOrder.getId());
    }

    @Test
    public void deleteOrder() {
        Order order = new Order();
        final Order newOrder = orderService.addOrder(order);
        Assert.assertNotNull(newOrder);
        orderService.deleteOrder(newOrder.getId());
        final Order foundOrder = orderService.getOrder(newOrder.getId());
        Assert.assertNull(foundOrder);
    }

    @Test
    public void updateOrder() {
        Order order = new Order();
        final Order newOrder = orderService.addOrder(order);
        Assert.assertNotNull(newOrder);
        order.setId(newOrder.getId());
        orderService.updateOrder(order);
        final Order foundOrder = orderService.getOrder(newOrder.getId());
        Assert.assertNotNull(foundOrder);
        Assert.assertEquals(foundOrder, order);
        orderService.deleteOrder(order.getId());
    }
}


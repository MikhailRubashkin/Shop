package dao.repository.impl;

import model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImplTest {

    private OrderRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = OrderRepositoryImpl.getInstance();
    }

    @Test
    public void getInstance() {
        Assert.assertNotNull(repository);
    }

    @Test
    public void save() {
        Order order = new Order();
        try {
            Order orderEntity = repository.save(order);
            Assert.assertNotNull(orderEntity);
            Assert.assertEquals(order, orderEntity);
            repository.remove(orderEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        Order order = new Order();
        try {
            Order orderEntity = repository.save(order);
            orderEntity = repository.get(orderEntity.getId());
            Assert.assertNotNull(orderEntity);
            repository.remove(orderEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll() {
        Order order = new Order();
        try {
            Order orderEntity = repository.save(order);
            List<Order> all = repository.getAll();
            Assert.assertNotNull(all);
            Assert.assertTrue(all.size() > 0);
            repository.remove(orderEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Order order = new Order();
        try {
            Order orderEntity = repository.save(order);
            repository.update(order);
            order = repository.get(orderEntity.getId());
            Assert.assertNotNull(orderEntity);
            Assert.assertEquals(order, orderEntity);
            repository.remove(orderEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        Order order = new Order();
        try {
            order = repository.save(order);
            int remove = repository.remove(order.getId());
            Order orderEntity = repository.get(remove);
            Assert.assertNull(orderEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

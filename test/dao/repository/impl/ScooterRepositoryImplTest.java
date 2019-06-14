package dao.repository.impl;

import model.Scooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ScooterRepositoryImplTest {

    private ScooterRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = ScooterRepositoryImpl.getInstance();
    }

    @Test
    public void getInstance() {
        Assert.assertNotNull(repository);
    }

    @Test
    public void save() {
        Scooter scooter = new Scooter();
        scooter.setModel("save");
        scooter.setPrice(100D);
        try {
            Scooter scooterEntity = repository.save(scooter);
            Assert.assertNotNull(scooterEntity);
            Assert.assertEquals(scooter, scooterEntity);
            repository.remove(scooterEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        Scooter scooter = new Scooter();
        scooter.setModel("get");
        scooter.setPrice(100D);
        try {
            Scooter scooterEntity = repository.save(scooter);
            scooterEntity = repository.get(scooterEntity.getId());
            Assert.assertNotNull(scooterEntity);
            repository.remove(scooterEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll() {
        Scooter scooter = new Scooter();
        scooter.setModel("save");
        scooter.setPrice(100D);
        try {
            Scooter scooterEntity = repository.save(scooter);
            List<Scooter> all = repository.getAll();
            Assert.assertNotNull(all);
            Assert.assertTrue(all.size() > 0);
            repository.remove(scooterEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Scooter scooter = new Scooter();
        scooter.setModel("update");
        scooter.setPrice(100D);
        try {
            Scooter scooterEntity = repository.save(scooter);
            scooterEntity.setModel("update_test");
            scooterEntity.setPrice(1000D);
            repository.update(scooter);
            scooter = repository.get(scooterEntity.getId());
            Assert.assertNotNull(scooterEntity);
            Assert.assertEquals(scooter, scooterEntity);
            repository.remove(scooterEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        Scooter scooter = new Scooter();
        scooter.setModel("remove");
        scooter.setPrice(100D);
        try {
            scooter = repository.save(scooter);
            int remove = repository.remove(scooter.getId());
            Scooter scooterEntity = repository.get(remove);
            Assert.assertNull(scooterEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
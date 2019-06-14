package dao.repository.impl;

import model.Bunch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class BunchRepositoryImplTest {

    private BunchRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = BunchRepositoryImpl.getInstance();
    }

    @Test
    public void getInstance() {
        Assert.assertNotNull(repository);
    }

    @Test
    public void save() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        try {
            Bunch bunchEntity = repository.save(bunch);
            Assert.assertNotNull(bunchEntity);
            Assert.assertEquals(bunch, bunchEntity);
            repository.remove(bunchEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        try {
            Bunch bunchEntity = repository.save(bunch);
            bunchEntity = repository.get(bunchEntity.getId());
            Assert.assertNotNull(bunchEntity);
            repository.remove(bunchEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAll() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        try {
            Bunch bunchEntity = repository.save(bunch);
            List<Bunch> all = repository.getAll();
            Assert.assertNotNull(all);
            Assert.assertTrue(all.size() > 0);
            repository.remove(bunchEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        try {
            Bunch bunchEntity = repository.save(bunch);
            bunchEntity.setScooter_id(1);
            bunchEntity.setOrder_id(1);
            bunchEntity.setQuantity(1);
            repository.update(bunch);
            bunch = repository.get(bunchEntity.getId());
            Assert.assertNotNull(bunchEntity);
            Assert.assertEquals(bunch, bunchEntity);
            repository.remove(bunchEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        try {
            bunch = repository.save(bunch);
            int remove = repository.remove(bunch.getId());
            Bunch bunchEntity = repository.get(remove);
            Assert.assertNull(bunchEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


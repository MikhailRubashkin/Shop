package service.impl;

import model.Scooter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.ScooterService;

import java.util.List;

public class ScooterServiceImplTest {

    private ScooterService scooterService;

    @Before
    public void setUp() {
        scooterService = ScooterServiceImpl.getInstance();
    }

    @Test
    public void getInstanse() {
        Assert.assertNotNull(scooterService);
    }

    @Test
    public void getScooter() {
        Scooter scooter = new Scooter();
        scooter.setModel("get");
        scooter.setPrice(100D);
        final Scooter newScooter = scooterService.addScooter(scooter);
        Assert.assertNotNull(newScooter);
        final Scooter foundScooter = scooterService.getScooter(newScooter.getId());
        Assert.assertNotNull(foundScooter);
        Assert.assertEquals(foundScooter, newScooter);
        scooterService.deleteScooter(newScooter.getId());
    }

    @Test
    public void getScooters() {
        Scooter scooter = new Scooter();
        scooter.setModel("getAll");
        scooter.setPrice(100D);
        final Scooter newScooter = scooterService.addScooter(scooter);
        Assert.assertNotNull(newScooter);
        final List<Scooter> scoters = scooterService.getScooters();
        Assert.assertTrue(scoters.size() > 0);
        scooterService.deleteScooter(newScooter.getId());
    }

    @Test
    public void addScooter() {
        Scooter scooter = new Scooter();
        scooter.setModel("add");
        scooter.setPrice(100D);
        final Scooter newScooter = scooterService.addScooter(scooter);
        Assert.assertNotNull(newScooter);
        Assert.assertEquals(scooter, newScooter);
        scooterService.deleteScooter(newScooter.getId());
    }

    @Test
    public void deleteScooter() {
        Scooter scooter = new Scooter();
        scooter.setModel("get");
        scooter.setPrice(100D);
        final Scooter newScooter = scooterService.addScooter(scooter);
        Assert.assertNotNull(newScooter);
        scooterService.deleteScooter(newScooter.getId());
        final Scooter foundScooter = scooterService.getScooter(newScooter.getId());
        Assert.assertNull(foundScooter);
    }

    @Test
    public void updateScooter() {
        Scooter scooter = new Scooter();
        scooter.setModel("update");
        scooter.setPrice(100D);
        final Scooter newScooter = scooterService.addScooter(scooter);
        Assert.assertNotNull(newScooter);
        scooter.setId(newScooter.getId());
        scooter.setModel("updateTest");
        scooter.setPrice(1000D);
        scooterService.updateScooter(scooter);
        final Scooter foundScooter = scooterService.getScooter(newScooter.getId());
        Assert.assertNotNull(foundScooter);
        Assert.assertEquals(foundScooter, scooter);
        scooterService.deleteScooter(scooter.getId());
    }
}
package service.impl;

import model.Bunch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BunchServiceImplTest {

    private BunchServiceImpl bunchService;

    @Before
    public void setUp() {
        bunchService = BunchServiceImpl.getInstance();
    }

    @Test
    public void getInstanse() {
        Assert.assertNotNull(bunchService);
    }

    @Test
    public void getBunch() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        final Bunch newBunch = bunchService.addBunch(bunch);
        Assert.assertNotNull(newBunch);
        final Bunch foundBunch = bunchService.getBunch(newBunch.getId());
        Assert.assertNotNull(foundBunch);
        Assert.assertEquals(foundBunch, newBunch);
        bunchService.deleteBunch(newBunch.getId());
    }

    @Test
    public void getBunchs() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        final Bunch newBunch = bunchService.addBunch(bunch);
        Assert.assertNotNull(newBunch);
        final List<Bunch> bunches = bunchService.getBunchs();
        Assert.assertTrue(bunches.size() > 0);
        bunchService.deleteBunch(newBunch.getId());
    }

    @Test
    public void addBunch() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        final Bunch newBunch = bunchService.addBunch(bunch);
        Assert.assertNotNull(newBunch);
        Assert.assertEquals(bunch, newBunch);
        bunchService.deleteBunch(newBunch.getId());
    }

    @Test
    public void deleteBunch() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);;
        final Bunch newBunch = bunchService.addBunch(bunch);
        Assert.assertNotNull(newBunch);
        bunchService.deleteBunch(newBunch.getId());
        final Bunch foundBunch = bunchService.getBunch(newBunch.getId());
        Assert.assertNull(foundBunch);
    }

    @Test
    public void updateBunch() {
        Bunch bunch = new Bunch();
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        final Bunch newBunch = bunchService.addBunch(bunch);
        Assert.assertNotNull(newBunch);
        bunch.setId(newBunch.getId());
        bunch.setScooter_id(1);
        bunch.setOrder_id(1);
        bunch.setQuantity(1);
        bunchService.updateBunch(bunch);
        final Bunch foundBunch = bunchService.getBunch(newBunch.getId());
        Assert.assertNotNull(foundBunch);
        Assert.assertEquals(foundBunch, bunch);
        bunchService.deleteBunch(bunch.getId());
    }

    @Test
    public void getByOrderId() {
        int orderId = 999999;
        for (int i = 1; i < 10; i++) {
            Bunch bunch = new Bunch();
            bunch.setScooter_id(i);
            bunch.setOrder_id(orderId);
            bunch.setQuantity(i);
            final Bunch newBunch = bunchService.addBunch(bunch);
            Assert.assertNotNull(newBunch);
        }
        final List<Bunch> bunches = bunchService.getByOrderId(orderId);
        Assert.assertEquals(9, bunches.size());
        bunches.forEach(bunch -> bunchService.deleteBunch(bunch.getId()));
    }
}

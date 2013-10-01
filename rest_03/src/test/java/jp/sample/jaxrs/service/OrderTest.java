package jp.sample.jaxrs.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/applicationContext.xml" })
@Transactional
public class OrderTest {

    @Resource
    private OrderDao orderDao;

    @Test
    public void testOrderItem01() {

        Order order = new Order();
        order.setUserName("Tarou");
        order.setItemName("item01");

        Order actual = orderDao.create(order);

        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getOrderNo());
        Assert.assertEquals(order.getUserName(), actual.getUserName());
        Assert.assertEquals(order.getItemName(), actual.getItemName());
    }

    @Test
    public void testGetOrderItem01() {
        Order actual = orderDao.findById(1L);

        Assert.assertNotNull(actual);
        Assert.assertEquals(Long.valueOf(1L), actual.getOrderNo());
        Assert.assertEquals("Tarou", actual.getUserName());
        Assert.assertEquals("item01", actual.getItemName());

    }

    @Test
    public void testUpdateOrderItem01() {
        Order order = new Order();
        order.setOrderNo(1L);
        order.setUserName("Tarou Yamada");
        order.setItemName("item01-1");

        Order result = orderDao.update(order);
        Order actual = orderDao.findById(result.getOrderNo());

        Assert.assertNotNull(actual);
        Assert.assertEquals(Long.valueOf(1L), actual.getOrderNo());
        Assert.assertEquals("Tarou Yamada", actual.getUserName());
        Assert.assertEquals("item01-1", actual.getItemName());

    }

    @Test
    public void testCancelOrderItem01() {
        Date orderDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Order order = new Order();
        order.setOrderNo(1L);
        order.setOrderDate(orderDate);

        Order result = orderDao.update(order);
        Order actual = orderDao.findById(result.getOrderNo());

        Assert.assertNotNull(actual);
        Assert.assertEquals(Long.valueOf(1L), actual.getOrderNo());
        Assert.assertEquals("Tarou", actual.getUserName());
        Assert.assertEquals("item01", actual.getItemName());
        Assert.assertEquals(format.format(orderDate), format.format(actual.getOrderDate()));
    }

    @Test
    public void testShowOrderHistory() {
        List<Order> actuals = orderDao.findAll();

        Assert.assertFalse(actuals.isEmpty());
    }
}

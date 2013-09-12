package jp.sample.jaxrs.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ItemOrderResource implements OrderResource {

    /** logger. */
    private static Logger logger = LoggerFactory.getLogger(ItemOrderResource.class);

    @Resource
    private Dao<Order> orderDao;

    /**
     * {@inheritDoc}
     */

    public Order create(Order order) {
        return orderDao.create(order);
    }

    /**
     * {@inheritDoc}
     */

    public Order getOrder(Long orderNo) {
        logger.info(String.format("orderNo=%d", orderNo));
        return orderDao.findById(orderNo);
    }

    /**
     * {@inheritDoc}
     */

    public Order update(Order order) {
        Order result = orderDao.update(order);
        return orderDao.findById(result.getOrderNo());
    }

    /**
     * {@inheritDoc}
     */

    public Order cancel(Long orderNo) {
        logger.info(String.format("orderNo=%d", orderNo));

        Date orderDate = new Date();

        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setOrderDate(orderDate); 
        Order result = orderDao.update(order);
        return orderDao.findById(result.getOrderNo());
    }

    /**
     * {@inheritDoc}
     */

    public List<Order> history() {
        return orderDao.findAll();
    }

}

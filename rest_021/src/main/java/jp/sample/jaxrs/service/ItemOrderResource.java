package jp.sample.jaxrs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ItemOrderResource implements OrderResource {

 
    public Order create(Order order) {
        return order;
    }


    public Order getOrder(Long orderNo) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserName("Tarou");
        order.setItemName("item01");
        return order;
    }


    public Order update(Order order) {
        return order;
    }


    public Order cancel(Long orderNo) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserName("Tarou");
        order.setItemName("item01");
        return order;
    }


    public List history() {
        Order order1 = new Order();
        order1.setOrderNo(1L);
        order1.setUserName("Tarou");
        order1.setItemName("item01");

        Order order2 = new Order();
        order2.setOrderNo(2L);
        order2.setUserName("Tarou");
        order2.setItemName("item01");

        List orders = new ArrayList();
        orders.add(order1);
        orders.add(order2);
        return orders;
    }


}
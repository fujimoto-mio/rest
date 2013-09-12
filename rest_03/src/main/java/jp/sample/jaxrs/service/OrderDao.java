package jp.sample.jaxrs.service;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends SqlSessionDaoSupport implements Dao<Order> {


    public Order create(Order order) {
        getSqlSession().insert("order.create", order);
        return order;
    }


    public Order findById(Long id) {
        return (Order) getSqlSession().selectOne("order.findById", id);
    }


    public Order update(Order order) {
        getSqlSession().update("order.update", order);
        return order;
    }

 
    public List<Order> findAll() {
        return getSqlSession().selectList("order.findAll");
    }
}

package jp.sample.jaxrs.service;

import java.util.List;

public interface Dao<T> {

    T create(T entity);

    T findById(Long id);

    T update(T entity);

    List<T> findAll();
}

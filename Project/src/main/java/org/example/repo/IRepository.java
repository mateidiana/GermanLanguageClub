package org.example.repo;

import java.util.List;
public interface IRepository<T> {

    List<T> getObjects();

    void save(T entity);

    void update(T entity, T action);

    void delete(T object);
}

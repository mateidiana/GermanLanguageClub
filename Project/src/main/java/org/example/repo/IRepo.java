package org.example.repo;

import java.util.List;
public interface IRepo<T> {

    List<T> getObjects();

    void save(T entity);

    void delete(T object);
}

package de.ait.bootapp.repositories;

import java.util.List;

public interface CrudRepository <T> {
    List<T> findAll();

    void save(T model);

    void update(T model);
}

package de.ait.repositories;

import java.util.List;

public interface CrudRepository <T> {
    List<T> findAll();

    void save(T model);

    void deleteById(Long id);

    void update(T model);
}

package com.company.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();
    void save(T model);
    void delete(Long id);
    void update(T model);
}

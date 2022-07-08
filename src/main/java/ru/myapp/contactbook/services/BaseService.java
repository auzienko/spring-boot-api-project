package ru.myapp.contactbook.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    T save(T entity);

    void deleteById(Long id);
}

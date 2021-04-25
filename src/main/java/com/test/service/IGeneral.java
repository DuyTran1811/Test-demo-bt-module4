package com.test.service;

import java.util.Optional;

public interface IGeneral<T> {
    Iterable<T> findAll();

    T save(T t);

    T findById(Integer id);

    void delete(Integer id);

//    Page<T> findAll(Pageable pageable);
}

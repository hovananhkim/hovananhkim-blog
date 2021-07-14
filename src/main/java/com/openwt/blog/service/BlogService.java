package com.openwt.blog.service;

import java.util.List;

public interface BlogService<T> {
    T findById(long id);

    List<T> findAll();

    List<T> findByNameContaining(String keyword);

    T findByName(String keyword);

    T save(T t);

    T update(T t, long id);

    void deleteAt(long id);
}

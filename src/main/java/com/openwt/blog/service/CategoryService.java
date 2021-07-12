package com.openwt.blog.service;

import com.openwt.blog.model.blog.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(long id);
    List<Category> findAll();
}

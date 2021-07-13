package com.openwt.blog.service;

import com.openwt.blog.model.blog.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findById(long id);

    List<Category> findAll();

    Category findByName(String keyword);

    Category post(Category category);

    Category put(Category category, long id);

    void delete(long id);
}

package com.openwt.blog.repository;

import com.openwt.blog.model.blog.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContaining(String name);
    Category findByName(String name);
}

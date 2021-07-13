package com.openwt.blog.repository;

import com.openwt.blog.model.blog.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameContaining(String name);
}

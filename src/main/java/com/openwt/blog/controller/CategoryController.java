package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Category;
import com.openwt.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/{id}")
    public Optional<Category> get(@PathVariable long id){
        return categoryService.findById(id);
    }

}

package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Category;
import com.openwt.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public Category get(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @RequestMapping
    public List<Category> get(){
        return categoryService.findAll();
    }
    @RequestMapping("/search")
    public Category search(@RequestParam String keyword){
        return categoryService.findByName(keyword);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public Category post(@RequestBody Category category) {
        return categoryService.post(category);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Category put(@RequestBody Category category, @PathVariable long id) {
        return categoryService.put(category, id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        categoryService.delete(id);
        return String.format("Delete category id: %d success",id);
    }
}
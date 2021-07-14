package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Category;
import com.openwt.blog.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping("/{id}")
    public Category get(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @RequestMapping
    public List<Category> get(){
        return categoryService.findAll();
    }
    @RequestMapping("/search")
    public List<Category> search(@RequestParam String keyword){
        return categoryService.findByNameContaining(keyword);
    }
    @Secured("ROLE_ADMIN")
    @PostMapping
    public Category post(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Category put(@Valid @RequestBody Category category, @PathVariable long id) {
        return categoryService.update(category, id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        categoryService.deleteAt(id);
        return String.format("Delete category id: %d success",id);
    }
}
package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Category;
import com.openwt.blog.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/{id}")
    public Category get(@PathVariable long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/search")
    public List<Category> search(@RequestParam String keyword) {
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
    public void delete(@PathVariable long id) {
        categoryService.delete(id);
    }
}
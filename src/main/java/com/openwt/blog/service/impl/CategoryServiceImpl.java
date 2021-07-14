package com.openwt.blog.service.impl;

import com.openwt.blog.exception.BadRequestException;
import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.repository.CategoryRepository;
import com.openwt.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CategoryServiceImpl implements BlogService<Category> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(long id) {
        isExist(id);
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByNameContaining(String keyword) {
        return categoryRepository.findByNameContaining(keyword);
    }

    @Override
    public Category findByName(String keyword) {
        isExist(keyword);
        return categoryRepository.findByName(keyword);
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.findByName(category.getName()) != null) {
            throw new BadRequestException("Category exist");
        }
        category.setId(0);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, long id) {
        isExist(id);
        category.setId(id);
        category.setUpdateDate(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteAt(long id) {
        isExist(id);
        categoryRepository.deleteById(id);
    }

    @Override
    public void isExist(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(String.format("Category id: %d not found", id));
        }
    }

    @Override
    public void isExist(String keyword) {
        if (categoryRepository.findByNameContaining(keyword) == null) {
            throw new NotFoundException(String.format("Category name: %s not found", keyword));
        }
    }
}

package com.openwt.blog.service.impl;

import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.repository.CategoryRepository;
import com.openwt.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
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
    public Category findByName(String keyword) {
        isExist(keyword);
        return categoryRepository.findByNameContaining(keyword).get();
    }

    @Override
    public Category post(Category category) {
        category.setId(0);
        return categoryRepository.save(category);
    }

    @Override
    public Category put(Category category, long id) {
        isExist(id);
        category.setId(id);
        category.setUpdateDate(new Date());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(long id) {
        isExist(id);
        categoryRepository.deleteById(id);
    }

    public void isExist(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(String.format("Category id: %d not found", id));
        }
    }

    private void isExist(String keyword) {
        if (categoryRepository.findByNameContaining(keyword) == null) {
            throw new NotFoundException(String.format("Category id: %s not found", keyword));
        }
    }
}

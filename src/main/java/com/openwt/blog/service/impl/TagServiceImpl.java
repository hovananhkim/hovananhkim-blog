package com.openwt.blog.service.impl;

import com.openwt.blog.model.blog.Tag;
import com.openwt.blog.repository.TagRepository;
import com.openwt.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements BlogService<Tag> {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public Tag findById(long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }

    @Override
    public List<Tag> findByNameContaining(String keyword) {
        return null;
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public Tag update(Tag tag, long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}

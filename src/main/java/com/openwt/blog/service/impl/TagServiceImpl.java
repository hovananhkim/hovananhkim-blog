package com.openwt.blog.service.impl;

import com.openwt.blog.model.blog.Tag;
import com.openwt.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl {
    @Autowired
    private TagRepository tagRepository;

    public Tag findById(long id) {
        return tagRepository.findById(id).get();
    }
}

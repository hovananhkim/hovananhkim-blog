package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Tag;
import com.openwt.blog.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "http://localhost:3000")
public class TagController {
    @Autowired
    private TagServiceImpl tagService;
    @GetMapping("/{id}")
    public Tag get(@PathVariable long id){
        return tagService.findById(id);
    }
}

package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostServiceImpl postService;

    @RequestMapping("/{id}")
    public Post get(@PathVariable long id) {
        return postService.findById(id);
    }

    @RequestMapping
    public List<Post> getAll() {
        return postService.findAll();
    }

    @RequestMapping("/search")
    public List<Post> search(@RequestParam String keyword) {
        return postService.findByContentContaining(keyword);
    }

    @Secured("ROLE_USER")
    @PostMapping
    public Post post(@Valid @RequestBody PostDTO post) {
        return postService.save(post);
    }

    @Secured("ROLE_USER")
    @PutMapping("/{id}")
    public Post put(@Valid @RequestBody PostDTO post, @PathVariable long id) {
        return postService.update(post, id);
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        postService.delete(id);
    }
}
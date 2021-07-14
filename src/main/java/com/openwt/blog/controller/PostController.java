package com.openwt.blog.controller;

import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Post> get() {
        return postService.findAll();
    }

    @RequestMapping("/search")
    public List<Post> search(@RequestParam String keyword) {
        return postService.findByNameContaining(keyword);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping
    public Post post(@RequestBody PostDTO post) {
        return postService.save(post);
    }

//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
//    @PutMapping("/{id}")
//    public Post put(@RequestBody Post post, @PathVariable long id) {
//        return postService.put(post, id);
//    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        postService.deleteAt(id);
        return String.format("Delete post id: %d success", id);
    }
}
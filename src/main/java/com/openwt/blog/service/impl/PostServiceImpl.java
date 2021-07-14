package com.openwt.blog.service.impl;

import com.openwt.blog.converter.PostDtoToPost;
import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.repository.PostRepository;
import com.openwt.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements BlogService<Post> {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostDtoToPost postDtoToPost;
    @Autowired
    private UserServiceImpl userService;
    @Override
    public Post findById(long id) {
        isExist(id);
        return postRepository .findById(id).get();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findByNameContaining(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    @Override
    public Post findByName(String keyword) {
        isExist(keyword);
        return postRepository.findByTitle(keyword);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post save(PostDTO postDTO){
        return postDtoToPost.convert(postDTO);
    }

    @Override
    public Post update(Post post, long id) {
        isExist(id);
        post.setId(id);
        post.setUpdateDate(new Date());
        return postRepository.save(post);
    }

    @Override
    public void deleteAt(long id) {
        isExist(id);
        postRepository.deleteById(id);
    }

    @Override
    public void isExist(long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException(String.format("Post id: %d not found", id));
        }
    }

    @Override
    public void isExist(String keyword) {
        if (postRepository.findByTitleContaining(keyword) == null) {
            throw new NotFoundException(String.format("Post title: %s not found", keyword));
        }
    }
}
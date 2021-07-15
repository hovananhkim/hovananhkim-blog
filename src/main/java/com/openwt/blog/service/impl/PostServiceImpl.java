package com.openwt.blog.service.impl;

import com.openwt.blog.converter.PostDtoToPost;
import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.exception.UnauthorizedException;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.model.user.User;
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
        verifyPostIsExist(id);
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
        verifyPostIsExist(keyword);
        return postRepository.findByTitle(keyword);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post save(PostDTO postDTO){
        return save(postDtoToPost.convert(postDTO));
    }

    @Override
    public Post update(Post post, long id) {
        return null;
    }
    public Post update(PostDTO postDto, long id) {
        verifyPostIsExist(id);
        checkAuthorized(postRepository.findById(id).get().getUser().getId());
        Post post = postDtoToPost.convert(postDto);
        post.setId(id);
        post.setUpdateDate(new Date());
        return postRepository.save(post);
    }

    @Override
    public void deleteAt(long id) {
        verifyPostIsExist(id);
        Post post = findById(id);
        checkAuthorized(post.getUser().getId());
        User user = userService.getMyUser();
        user.getPosts().remove(post);
        Category category = post.getCategory();
        category.getPosts().remove(post);
        postRepository.deleteById(id);
    }

    public void checkAuthorized(long id) {
        User user = userService.getMyUser();
        if (user.getId() != id && user.getRoles().size()!=2){
            throw new UnauthorizedException("Unauthorized");
        }
    }

    private void verifyPostIsExist(long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException(String.format("Post id: %d not found", id));
        }
    }

    private void verifyPostIsExist(String keyword) {
        if (postRepository.findByTitleContaining(keyword) == null) {
            throw new NotFoundException(String.format("Post title: %s not found", keyword));
        }
    }
}

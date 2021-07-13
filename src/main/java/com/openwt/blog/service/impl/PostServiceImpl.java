package com.openwt.blog.service.impl;

import com.openwt.blog.config.JwtTokenProvider;
import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.model.Constants;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.blog.Tag;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.model.dto.UserDTO;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.CategoryRepository;
import com.openwt.blog.repository.PostRepository;
import com.openwt.blog.repository.TagRepository;
import com.openwt.blog.repository.UserRepository;
import com.openwt.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;
    @Override
    public Optional<Post> findById(long id) {
        isExist(id);
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findByName(String keyword) {
        isExist(keyword);
        return postRepository.findByTitleContaining(keyword);
    }

    @Override
    public Post post(PostDTO postDTO) {
        System.out.println(postDTO.getContent());
        Post post = new Post();
        post.setId(0);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        Category category = categoryRepository.getById(postDTO.getCategoryId());
        post.setCategory(category);
        User user = userRepository.findByEmail(userService.getUsernameFromAuthentication());
        post.setUser(user);

        category.getPosts().add(post);
        categoryRepository.save(category);

        user.getPosts().add(post);
        userRepository.save(user);
        return postRepository.save(post);
    }

//    @Override
//    public Post put(PostDTO postDTO, long id) {
//        isExist(id);
//        postDTO.setId(id);
//        postDTO.setUpdateDate(new Date());
//        return postRepository.save(postDTO);
//    }

    @Override
    public void delete(long id) {
        isExist(id);
        postRepository.deleteById(id);
    }

    private void isExist(long id) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException(String.format("Post id: %d not found", id));
        }
    }

    private void isExist(String keyword) {
        if (postRepository.findByTitleContaining(keyword) == null) {
            throw new NotFoundException(String.format("Post id: %s not found", keyword));
        }
    }
}

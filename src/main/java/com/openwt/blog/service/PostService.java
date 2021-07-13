package com.openwt.blog.service;

import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(long id);

    List<Post> findAll();

    Optional<Post> findByName(String keyword);

    Post post(PostDTO postDTO);

//    Post put(PostDTO postDTO, long id);

    void delete(long id);
}

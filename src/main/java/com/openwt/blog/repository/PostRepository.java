package com.openwt.blog.repository;

import com.openwt.blog.model.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyword);
    Post findByTitle(String keyword);
}

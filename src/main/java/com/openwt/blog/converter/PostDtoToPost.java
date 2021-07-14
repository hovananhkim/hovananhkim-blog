package com.openwt.blog.converter;

import com.openwt.blog.converter.base.Converter;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.CategoryRepository;
import com.openwt.blog.service.impl.CategoryServiceImpl;
import com.openwt.blog.service.impl.PostServiceImpl;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostDtoToPost extends Converter<PostDTO, Post> {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private PostServiceImpl postService;

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setId(0);
        post.setTitle(source.getTitle());
        post.setContent(source.getContent());
        User user = userService.getMyUser();
        post.setUser(user);

        Category category = categoryService.findByName(source.getCategory());
        post.setCategory(category);



        user.getPosts().add(post);
        category.getPosts().add(post);
        return postService.save(post);
    }
}

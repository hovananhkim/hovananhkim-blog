package com.openwt.blog.converter;

import com.openwt.blog.converter.base.Converter;
import com.openwt.blog.model.blog.Category;
import com.openwt.blog.model.blog.Post;
import com.openwt.blog.model.blog.Tag;
import com.openwt.blog.model.dto.PostDTO;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.CategoryRepository;
import com.openwt.blog.repository.TagRepository;
import com.openwt.blog.service.impl.CategoryServiceImpl;
import com.openwt.blog.service.impl.PostServiceImpl;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PostDtoToPost extends Converter<PostDTO, Post> {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Post convert(PostDTO source) {
        Post post = new Post();
        post.setId(0);
        post.setTitle(source.getTitle());
        post.setContent(source.getContent());
        User user = userService.getMyUser();
        post.setUser(user);

        Category category = categoryService.findById(source.getCategory());
        post.setCategory(category);

        Set<Tag> tags = new HashSet<>();
        for(String tagName:source.getTags()){
            if (tagRepository.findByName(tagName)==null){
                Tag tag = new Tag();
                tag.setId(0);
                tag.setName(tagName);
                tagRepository.save(tag);
            }
            Tag tag = tagRepository.findByName(tagName);
            tags.add(tag);
        }
        post.setTags(tags);

        return post;
    }
}

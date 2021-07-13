package com.openwt.blog.controller;

import com.openwt.blog.model.Constants;
import com.openwt.blog.model.dto.UserDTO;
import com.openwt.blog.model.user.User;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    public Optional<User> get(@PathVariable long id) {
        return userService.get(id);
    }
    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<User> get() {
        return userService.get();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }
}

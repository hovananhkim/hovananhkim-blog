package com.openwt.blog.controller;

import com.openwt.blog.model.user.User;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users/{id}")
    public User get(@PathVariable long id) {
        return userService.findById(id);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/users")
    public List<User> get() {
        return userService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable long id) {
        userService.deleteAt(id);
    }

    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping("users/{id}")
    public User put(@Valid @RequestBody User user, @PathVariable long id) {
        return userService.update(user, id);
    }
}

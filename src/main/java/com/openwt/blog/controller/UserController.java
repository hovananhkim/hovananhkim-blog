package com.openwt.blog.controller;

import com.openwt.blog.model.user.User;
import com.openwt.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        return userService.findById(id);
    }

    @GetMapping("/find")
    public User getByEmail(@RequestParam String email) {
        return userService.findByName(email);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<User> getAll() {
        return userService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/block")
    public User block(@PathVariable long id) {
        return userService.block(id);
    }
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}/unblock")
    public User unBlock(@PathVariable long id) {
        return userService.unBlock(id);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{id}")
    public User put(@Valid @RequestBody User user, @PathVariable long id) {
        return userService.update(user, id);
    }
}

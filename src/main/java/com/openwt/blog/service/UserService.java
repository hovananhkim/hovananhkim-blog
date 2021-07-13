package com.openwt.blog.service;

import com.openwt.blog.model.dto.UserDTO;
import com.openwt.blog.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(UserDTO userDTO);
    List<User> get();
    Optional<User> get(long id);
    String getUsernameFromAuthentication();
    void delete(long id);
    User put(UserDTO userDTO, long id);
    User put(User user, long id);
}

package com.openwt.blog.service.impl;

import com.openwt.blog.exception.BadRequestException;
import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.exception.UnauthorizedException;
import com.openwt.blog.model.user.Role;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.RoleRepository;
import com.openwt.blog.repository.UserRepository;
import com.openwt.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements BlogService<User> {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findById(long id) {
        verifyUserIsExist(id);
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByNameContaining(String keyword) {
        return userRepository.findByFirstnameContainsOrLastnameContains(keyword, keyword);
    }

    public User findByName(String keyword) {
        verifyUserIsExist(keyword);
        return userRepository.findByEmail(keyword);
    }

    @Override
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email is exist");
        }
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user, long id) {
        checkAuthorized(id);
        User u = findById(id);
        if (!user.getEmail().equals(u.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new BadRequestException("Email is exist");
            }
        }
        u.setEmail(user.getEmail());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public void delete(long id) {
        verifyUserIsExist(id);
        userRepository.deleteById(id);
    }

    private void verifyUserIsExist(long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(String.format("User id: %d not found", id));
        }

    }

    private void verifyUserIsExist(String email) {
        if (userRepository.findByEmail(email) == null) {
            throw new NotFoundException(String.format("User email: %s not found", email));
        }
    }

    public User getMyUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findByName(userDetails.getUsername());
    }

    public void checkAuthorized(long id) {
        User user = getMyUser();
        if (user.getId() != id && user.getRoles().size() != 2) {
            throw new UnauthorizedException("Unauthorized");
        }
    }
}

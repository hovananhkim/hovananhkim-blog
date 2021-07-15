package com.openwt.blog.service.impl;

import com.openwt.blog.exception.BadRequestException;
import com.openwt.blog.exception.NotFoundException;
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

    @Override
    public User findByName(String keyword) {
        verifyUserIsExist(keyword);
        return userRepository.findByEmail(keyword);
    }

    @Override
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email is exist");
        }
        user.getRoles().add(roleRepository.findByName("ROLE_USER"));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user, long id) {
        User u = findById(id);
        User userLogin = getMyUser();
        if (userLogin.getId() == id || userLoginIsAdmin()) {
            if (!user.getEmail().equals(u.getEmail())){
                if (userRepository.findByEmail(user.getEmail())!=null){
                    throw new BadRequestException("Email is exist");
                }
            }
            u.setEmail(user.getEmail());
            u.setFirstname(user.getFirstname());
            u.setLastname(user.getLastname());
            u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.save(u);
        }
        throw new BadRequestException("Unauthorized");
    }

    @Override
    public void deleteAt(long id) {
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
    private boolean userLoginIsAdmin(){
        User user = getMyUser();
        Set<Role> roles = user.getRoles();
        for (Role role:roles){
            if (role.getName().equals("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
}

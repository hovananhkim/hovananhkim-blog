package com.openwt.blog.service.impl;

import com.openwt.blog.exception.NotFoundException;
import com.openwt.blog.model.dto.UserDTO;
import com.openwt.blog.model.user.User;
import com.openwt.blog.repository.RoleRepository;
import com.openwt.blog.repository.UserRepository;
import com.openwt.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User register(UserDTO userDTO) {
        return addUser(userDTO, "ROLE_USER");
    }

    @Override
    public List<User> get() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> get(long id) {
        isExist(id);
        return userRepository.findById(id);
    }

    @Override
    public void delete(long id) {
        System.out.println(id);
        isExist(id);
        userRepository.deleteById(id);
    }

    @Override
    public User put(UserDTO userDTO, long id) {
        return null;
    }

    @Override
    public User put(User user, long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    private User addUser(UserDTO userDTO, String role) {
        if (userRepository.findByEmail(userDTO.getEmail()) == null) {
            User user = new User(
                    userDTO.getEmail(),
                    userDTO.getFirstname(),
                    userDTO.getLastname(),
                    new BCryptPasswordEncoder().encode(userDTO.getPassword()));
            user.setId(0);
            user.setRole(roleRepository.findByName(role));
            return userRepository.save(user);
        }
        return null;
    }
    @Override
    public String getUsernameFromAuthentication(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    public void isExist(long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(String.format("User id:%d not found", id));
        }
    }
    private void isExist(String username){
        if(userRepository.findByEmail(username)==null){
            throw  new NotFoundException(String.format("User username:%s not found", username));
        }
    }
}

package com.openwt.blog.repository;

import com.openwt.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByFirstnameContainsOrLastnameContains(String firstname, String lastname);
}

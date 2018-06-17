package com.example.mytter.model.service;

import com.example.mytter.model.domain.User;
import com.example.mytter.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}

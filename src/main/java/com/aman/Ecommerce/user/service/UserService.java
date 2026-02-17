package com.aman.Ecommerce.user.service;

import com.aman.Ecommerce.user.dto.mapper.UserMapper;
import com.aman.Ecommerce.user.dto.request.UserRegisterRequest;
import com.aman.Ecommerce.user.dto.response.UserResponse;
import com.aman.Ecommerce.user.entity.User;
import com.aman.Ecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UserResponse saveUser(UserRegisterRequest userRegisterRequest) {
        if(userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(encodedPassword);
        User user = UserMapper.toUser(userRegisterRequest);
        User savedUser = userRepository.save(user);
        return UserMapper.toUserResponse(savedUser);
    }

    public UserResponse getUser(String email) {
        User user =  userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User Not Found")
        );
        return UserMapper.toUserResponse(user);
    }
}

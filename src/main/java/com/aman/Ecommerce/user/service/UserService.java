package com.aman.Ecommerce.user.service;

import com.aman.Ecommerce.user.dto.mapper.UserMapper;
import com.aman.Ecommerce.user.dto.request.UserRegisterRequest;
import com.aman.Ecommerce.user.dto.response.UserResponse;
import com.aman.Ecommerce.user.entity.User;
import com.aman.Ecommerce.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public final UserRepository userRepository;


    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserResponse saveUser(UserRegisterRequest userRegisterRequest) {
        if(userRepository.existsByEmail(userRegisterRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
       User user = UserMapper.toUser(userRegisterRequest);
       User savedUser = userRepository.save(user);
       return UserMapper.toUserResponse(savedUser);
    }
}

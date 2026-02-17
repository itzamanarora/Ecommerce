package com.aman.Ecommerce.auth.service;

import com.aman.Ecommerce.auth.dto.mapper.AuthMapper;
import com.aman.Ecommerce.auth.dto.request.SignUpRequest;
import com.aman.Ecommerce.user.entity.RoleType;
import com.aman.Ecommerce.user.entity.User;
import com.aman.Ecommerce.user.entity.UserStatus;
import com.aman.Ecommerce.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User saveUser(SignUpRequest signUpRequest){
        String email = signUpRequest.getEmail().toLowerCase().trim();

        if(userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        User user =  User.builder()
                .email(email)
                .password(encodedPassword)
                .status(UserStatus.ACTIVE)
                .roles(Set.of(RoleType.USER))
                .build();

        return userRepository.save(user);
    }
}

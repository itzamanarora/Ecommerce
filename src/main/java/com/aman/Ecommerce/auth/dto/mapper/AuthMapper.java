package com.aman.Ecommerce.auth.dto.mapper;

import com.aman.Ecommerce.auth.dto.request.SignUpRequest;
import com.aman.Ecommerce.user.entity.User;

public class AuthMapper {

    public static User toUser(SignUpRequest signUpRequest){
        if(signUpRequest == null) {
            throw new RuntimeException("SignUpRequest cannot be null");
        }
        return User.builder()
                .email(signUpRequest.getEmail())
                .password(signUpRequest.getPassword())
                .build();
    }

}

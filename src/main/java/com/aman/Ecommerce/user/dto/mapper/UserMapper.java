package com.aman.Ecommerce.user.dto.mapper;

import com.aman.Ecommerce.user.dto.request.UserRegisterRequest;
import com.aman.Ecommerce.user.dto.response.UserResponse;
import com.aman.Ecommerce.user.entity.RoleType;
import com.aman.Ecommerce.user.entity.User;

import java.util.List;

public class UserMapper {

    public static User toUser(UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(userRegisterRequest.getPassword());
        user.getRoles().add(RoleType.ADMIN);

        return user;
    }

    public static UserResponse toUserResponse(User user){
        if(user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

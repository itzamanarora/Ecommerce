package com.aman.Ecommerce.user.controller;

import com.aman.Ecommerce.user.dto.request.UserRegisterRequest;
import com.aman.Ecommerce.user.dto.response.UserResponse;
import com.aman.Ecommerce.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class UserController {

    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest){
        return new ResponseEntity<>(
                userService.saveUser(userRegisterRequest), HttpStatus.CREATED
        );
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }
}

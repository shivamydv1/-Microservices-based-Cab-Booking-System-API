package com.user_service.UserService.controller;

import com.user_service.UserService.dto.UserDto;
import com.user_service.UserService.entity.User;
import com.user_service.UserService.service.UserServiceImp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;

    @PostMapping
    public ResponseEntity<UserDto> createUser (@RequestBody UserDto userDto){
        return ResponseEntity.ok(userServiceImp.createUser(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser () {
        return ResponseEntity.ok(userServiceImp.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById (@PathVariable String id){
        return ResponseEntity.ok(userServiceImp.getUserById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName (@PathVariable String name){
        return ResponseEntity.ok(userServiceImp.getUserByName(name));
    }
}

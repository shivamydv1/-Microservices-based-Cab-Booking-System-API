package com.user_service.UserService.service;

import com.user_service.UserService.dto.UserDto;
import com.user_service.UserService.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    User getUserByName(String name);
    UserDto getUserById(String id);
    List<UserDto> getAllUsers();
}

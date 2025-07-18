package com.user_service.UserService.service.UserServiceImp;

import com.user_service.UserService.dto.UserDto;
import com.user_service.UserService.entity.User;
import com.user_service.UserService.repository.UserRepository;
import com.user_service.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {


    private final UserRepository userRepository;


    private UserDto mapToDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .location(user.getLocation())
                .build();
    }

    private User mapToEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .location(userDto.getLocation())
                .build();
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(mapToEntity(userDto));
        return mapToDto(user);
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByName(name);
        return userRepository.findByName(String.valueOf(user));
    }

    @Override
    public UserDto getUserById(String id) {
        return userRepository.findById(id).map(this::mapToDto).orElse(null);
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDto).toList();
    }

}

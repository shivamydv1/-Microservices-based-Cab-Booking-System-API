package com.user_service.UserService.repository;


import com.user_service.UserService.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, String >{
    User findByName(String name);

}

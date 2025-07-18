package com.user_service.UserService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    private String id;
    private String name;
    private String email;
    private String location;
}

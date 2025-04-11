package com.maintrot.basya.mappers.impl;

import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.enums.Role;
import com.maintrot.basya.mappers.UserMapper;
import com.maintrot.basya.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole().name());
        response.setPhoto(user.getPhoto());
        return response;
    }

    @Override
    public User toEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(Role.valueOf(request.getRole()));
        user.setPhoto(request.getPhoto());
        return user;
    }
}

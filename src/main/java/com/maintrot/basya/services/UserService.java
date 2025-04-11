package com.maintrot.basya.services;

import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.models.User;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUser(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
    UserResponse getUserByName(String name);
    UserResponse registerUser(UserRequest userRequest);
}

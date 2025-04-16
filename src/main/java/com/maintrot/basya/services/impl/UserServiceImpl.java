package com.maintrot.basya.services.impl;

import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.enums.Role;
import com.maintrot.basya.mappers.UserMapper;
import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.UserRepository;
import com.maintrot.basya.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(Role.valueOf(request.getRole()));
        user.setPhoto(request.getPhoto());
        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserByName(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("User with this name not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse registerUserClient(UserRequest userRequest) {
        if (userRepository.existsByPhone(userRequest.getPhone())) {
            throw new RuntimeException("User with this phone already exist");
        }
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER_CLIENT);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse registerUserMaster(UserRequest userRequest) {
        if (userRepository.existsByPhone(userRequest.getPhone())) {
            throw new RuntimeException("User with this phone already exist");
        }
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER_MASTER);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse registerUserAdmin(UserRequest userRequest) {
        if (userRepository.existsByPhone(userRequest.getPhone())) {
            throw new RuntimeException("User with this phone already exist");
        }
        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(Role.USER_ADMIN);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserByPhone(String phone) {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toResponse(user);
    }

}

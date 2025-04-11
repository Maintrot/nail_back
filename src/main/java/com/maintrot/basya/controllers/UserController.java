package com.maintrot.basya.controllers;

import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.enums.Role;
import com.maintrot.basya.models.User;
import com.maintrot.basya.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "CRUD и не только операции для управления пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> responses = userService.getAllUsers();
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Обновить данные пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить пользователя по имени")
    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponse> getUserByName(@PathVariable String name) {
        UserResponse userResponse = userService.getUserByName(name);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Получить список пользователей по роли")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> getUsersByrole(@PathVariable String role) {
        Role userRole = Role.valueOf(role);
        List<UserResponse> userResponses = userService.getUsersByRole(userRole);
        return ResponseEntity.ok(userResponses);
    }

    @Operation(summary = "Найти пользоватееля по номеру телефона")
    @GetMapping("/phone/{phone}")
    public ResponseEntity<UserResponse> getUserByPhone(@PathVariable String phone) {
        UserResponse userResponse = userService.getUserByPhone(phone);
        return ResponseEntity.ok(userResponse);
    }
}

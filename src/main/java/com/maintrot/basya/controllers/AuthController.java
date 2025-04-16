package com.maintrot.basya.controllers;

import com.maintrot.basya.components.CustomAuthenticationToken;
import com.maintrot.basya.components.JwtTokenProvider;
import com.maintrot.basya.dtoes.UserRequest;
import com.maintrot.basya.dtoes.UserResponse;
import com.maintrot.basya.dtoes.auth.JwtAuthenticationResponse;
import com.maintrot.basya.dtoes.auth.LoginRequest;
import com.maintrot.basya.mappers.UserMapper;
import com.maintrot.basya.models.User;
import com.maintrot.basya.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth API", description = "Регистрация и авторизация")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Operation(summary = "Регистрация нового пользователя клиента")
    @PostMapping("/registerClient")
    public ResponseEntity<UserResponse> registerUserClient(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUserClient(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Регистрация нового пользователя мастера")
    @PostMapping("/registerMaster")
    @PreAuthorize("hasRole('USER_ADMIN')")
    public ResponseEntity<UserResponse> registerUserMaster(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUserMaster(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Регистрация нового пользователя админа")
    @PostMapping("/registerAdmin")
    public ResponseEntity<UserResponse> registerUserAdmin(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUserAdmin(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(
                loginRequest.getName(),
                loginRequest.getPassword(),
                loginRequest.getPhone()
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        String token = tokenProvider.generatetoken(user.getUsername(), user.getPhone(), user.getRole().name());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}

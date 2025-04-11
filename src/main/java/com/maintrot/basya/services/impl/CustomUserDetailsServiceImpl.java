package com.maintrot.basya.services.impl;

import com.maintrot.basya.models.User;
import com.maintrot.basya.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Предполагается, что у вас используется поле name как username
        User user = userRepository.findByName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Пользователь с именем " + username + " не найден")
                );
        // Поскольку ваш класс User реализует UserDetails, его можно вернуть напрямую.
        return user;
    }
}

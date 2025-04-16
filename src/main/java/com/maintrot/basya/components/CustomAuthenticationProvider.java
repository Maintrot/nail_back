package com.maintrot.basya.components;

import com.maintrot.basya.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof CustomAuthenticationToken)) {
            throw new BadCredentialsException("Incorrect type of authentication token");
        }
        CustomAuthenticationToken authToken = (CustomAuthenticationToken) authentication;

        String username = authToken.getPrincipal().toString();
        String rawPassword = authToken.getCredentials().toString();
        String providedPhone = authToken.getPhone();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("User not found");
        }

        if (!passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        User user = (User) userDetails;
        if (user.getPhone() == null || !user.getPhone().equals(providedPhone)) {
            throw new BadCredentialsException("Incorrect phone number");
        }

        return new CustomAuthenticationToken(userDetails, rawPassword, providedPhone, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

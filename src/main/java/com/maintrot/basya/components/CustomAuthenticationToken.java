package com.maintrot.basya.components;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String phone;

    public CustomAuthenticationToken(Object principal, Object credentials, String phone) {
        super(principal, credentials);
        this.phone = phone;
    }

    public CustomAuthenticationToken(Object principal, Object credentials, String phone,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}

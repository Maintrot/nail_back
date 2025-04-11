package com.maintrot.basya.models;

import com.maintrot.basya.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "Имя пользователя")
    private String name;

    @Column(nullable = false, length = 255, name = "Пароль пользователя")
    private String password;

    @Column(length = 20, name = "Номер телефона пользователя")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "Роль пользователя")
    private Role role;

    @Column(length = 255, name = "Фото пользователя")
    private String photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Преобразуем роль в объект GrantedAuthority.
        // Обычно роль записывают с префиксом "ROLE_"
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Можно добавить логику просроченности аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Можно добавить логику блокировки аккаунта
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Можно добавить логику истечения срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Если необходимо, можно добавить флаг активности пользователя
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
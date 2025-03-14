package com.meuDiario.diary.infra.Security.UserAuthentication;

import com.meuDiario.diary.model.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class UserAuthentication implements UserDetails {
    public final User USER;
    public UserAuthentication(User USER) {
        this.USER = USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "admin");
    }

    @Override
    public String getPassword() {
        return USER.getPassword();
    }

    @Override
    public String getUsername() {
        return USER.getNickname();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

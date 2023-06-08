package com.spring.security.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER_ROLE,
    ADMIN_Role;

    @Override
    public String getAuthority() {
        return name();
    }
}

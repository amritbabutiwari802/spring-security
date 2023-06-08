package com.spring.security.websecurity;

import com.spring.security.dao.AuthRepository;
import com.spring.security.entity.Auth;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;

    public UserDetailServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Auth> authOptional = this.authRepository.findById(username);
        if(authOptional.isEmpty()){
            throw new UsernameNotFoundException("username is not found");
        }
        Auth auth = authOptional.get();
        return new User(auth.getUsername(),auth.getPassword(), auth.getUserRole());
    }
}

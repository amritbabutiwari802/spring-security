package com.spring.security.controller;

import com.spring.security.dao.AuthRepository;
import com.spring.security.dto.Signup;
import com.spring.security.entity.Auth;
import com.spring.security.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SignupController {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String doSignup(Model model){
        model.addAttribute("signup", new Signup());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("signup") Signup signup, Model model) throws Exception {
        System.out.println("hitted");
            Optional<Auth> hasAuth = this.authRepository.findById(signup.getUsername());
            if (hasAuth.isPresent()) {
                throw new Exception("username already exists");
            }
            Auth auth = new Auth();
            auth.setUsername(signup.getUsername());
            auth.setPassword(passwordEncoder.encode(signup.getPassword()));
            List<UserRole> userRoleList = new ArrayList<>();
            userRoleList.add(UserRole.USER_ROLE);
            auth.setUserRole(userRoleList);
            this.authRepository.save(auth);
            Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getUsername(), null, userRoleList);
            authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        return "welcome";

    }
}

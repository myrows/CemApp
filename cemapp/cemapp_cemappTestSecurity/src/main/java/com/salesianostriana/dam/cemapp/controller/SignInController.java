package com.salesianostriana.dam.cemapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/signin")
public class SignInController {

	@Autowired
    private final AppUserRepository repository;

    public SignInController(AppUserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public AppUser registrarUsuario(@RequestBody AppUser appUser) {
        
        return repository.save(appUser);
    }

    @PostMapping("/validateEmail")
    public Boolean emailExists(@RequestParam String email) {
        return repository.existsByEmail(email);
    }

}
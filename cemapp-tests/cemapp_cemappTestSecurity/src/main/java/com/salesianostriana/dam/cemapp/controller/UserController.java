package com.salesianostriana.dam.cemapp.controller;

import java.util.HashSet;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cemapp.errors.EntityNotFoundException;
import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
class UserController {

	@Autowired
	private final AppUserRepository repository;

    UserController(AppUserRepository repository) {
        this.repository = repository;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    Page<AppUser> all(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(AppUser.Role.USER.name())) {
            return repository.findAllByEmail(auth, pageable);
        }
        return repository.findAll(pageable);
    }

    @GetMapping("/search")
    Page<AppUser> search(@RequestParam String email, Pageable pageable, OAuth2Authentication authentication) {
        String auth = (String) authentication.getUserAuthentication().getPrincipal();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        if (role.equals(AppUser.Role.USER.name())) {
            return repository.findAllByEmailContainsAndEmail(email, auth, pageable);
        }
        return repository.findByEmailContains(email, pageable);
    }

    @GetMapping("/findByEmail")
    @PreAuthorize("!hasAuthority('USER') || (authentication.principal == #email)")
    AppUser findByEmail(@RequestParam String email, OAuth2Authentication authentication) {
        return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(AppUser.class, "email", email));
    }

    @GetMapping("/{id}")
    @PostAuthorize("!hasAuthority('USER') || (returnObject != null && returnObject.email == authentication.principal)")
    AppUser one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AppUser.class, "id", id.toString()));
    }

    
    @PutMapping("/{id}")
    //@PreAuthorize("!hasAuthority('USER') || (authentication.principal == @appUserRepository.findById(#id)")
    void update(@PathVariable Long id, @Valid @RequestBody AppUser res) {
        AppUser u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AppUser.class, "id", id.toString()));
        res.setPassword(u.getPassword());
        repository.save(res);
    }

    @PostMapping
    @PreAuthorize("!hasAuthority('USER')")
    AppUser create(@Valid @RequestBody AppUser res) {
        return repository.save(res);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("!hasAuthority('USER')")
    void delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(AppUser.class, "id", id.toString());
        }
    }

    //Para cambiar la contraseña a un usuario deberia coger el token para ese usuario y cambiar la contraseña
    @PutMapping("/{id}/changePassword")
    //@PreAuthorize("!hasAuthority('USER') || (#oldPassword != null && !#oldPassword.isEmpty() && authentication.principal == @appUserRepository.findById(#id).orElse(new com.salesianostriana.dam.cemapp.models.AppUser()).email)")
    void changePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        AppUser user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(AppUser.class, "id", id.toString()));
        if (oldPassword == null || oldPassword.isEmpty() || passwordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder().encode(newPassword));
            repository.save(user);
        } else {
            throw new ConstraintViolationException("old password doesn't match", new HashSet<>());
        }
    }

}
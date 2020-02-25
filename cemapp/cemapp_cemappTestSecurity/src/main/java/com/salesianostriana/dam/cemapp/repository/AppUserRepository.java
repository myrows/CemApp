package com.salesianostriana.dam.cemapp.repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.salesianostriana.dam.cemapp.models.AppUser;


public interface AppUserRepository extends PagingAndSortingRepository<AppUser, Long> {

	Optional<AppUser> findByEmail(String email);
    Page<AppUser> findByEmailContains(String email, Pageable  pageable);
    Page<AppUser> findAllByEmail(String email, Pageable pageable);
    Page<AppUser> findAllByEmailContainsAndEmail(String email, String auth, Pageable pageable);

    Boolean existsByEmail(String email);
}

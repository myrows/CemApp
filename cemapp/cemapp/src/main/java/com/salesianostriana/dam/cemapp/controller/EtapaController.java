/**
 * 
 */
package com.salesianostriana.dam.cemapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cemapp.dto.AppUserDto;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.service.EtapaService;

import lombok.RequiredArgsConstructor;

/**
 * @author jceacero
 *
 */
@RestController
@RequestMapping("/Etapa")
@RequiredArgsConstructor
public class EtapaController {
	
	private final EtapaService etapaService;
	
	@GetMapping("/")
	public List<Etapa> createAdmin() {
		

		return etapaService.findAll();

	}

}

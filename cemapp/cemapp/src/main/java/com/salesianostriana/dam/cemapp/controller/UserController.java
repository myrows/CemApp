/**
 * 
 */
package com.salesianostriana.dam.cemapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cemapp.service.AppUserService;

import lombok.RequiredArgsConstructor;

/**
 * @author jceacero
 *
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	
	private AppUserService appUserService;

}

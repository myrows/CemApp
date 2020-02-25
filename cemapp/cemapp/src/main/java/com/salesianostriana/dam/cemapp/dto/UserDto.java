package com.salesianostriana.dam.cemapp.dto;

import com.salesianostriana.dam.cemapp.dto.AppUserDto.AppUserDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author jmrlaguna
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Long id;
	private String email;
	private String roles;
	private String colegio;
}

/**
 * 
 */
package com.salesianostriana.dam.cemapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author danib
 *
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
	
	private Long id;
	private String email;
	private String password;
	private String roles;
	private String colegio;
}

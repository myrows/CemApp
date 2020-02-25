package com.salesianostriana.dam.cemapp.error;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4676608987857164835L;

	public UserNotFoundException() {
		super("Usuario no encontrado");
	}
	
	public UserNotFoundException(String username) {
		super(String.format("Usuario %s no encontrado", username));
	}
	
}
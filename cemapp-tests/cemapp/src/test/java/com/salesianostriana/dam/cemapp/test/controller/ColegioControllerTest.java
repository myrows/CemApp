package com.salesianostriana.dam.cemapp.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cemapp.dto.ColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAnyoEscolarToAnyoEscolarDTO;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAppUserToAppUserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertColegioToColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertUnidadToUnidadDto;
import com.salesianostriana.dam.cemapp.dto.convert.convertEtapaDtoToEtapa;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.AppUserService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/colegio")
@RequiredArgsConstructor
public class ColegioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserDetailsService userDetailsService;
	
	@MockBean
	private ConvertAppUserToAppUserDto ConvertAppUserToAppUserDto;
	
	@MockBean
	private ConvertColegioToColegioDto ConvertColegioToColegioDto;
	
	@MockBean
	private ColegioService colegioService;
	
	@MockBean
	private AppUserService appUserService;
	
	@MockBean
	private convertEtapaDtoToEtapa convertEtapaDtoToEtapa;
	
	@MockBean
	private ConvertUnidadToUnidadDto ConvertUnidadToUnidadDto;
	
	@MockBean
	private AppUserRepository appUserRepository;
	
	@MockBean
	private UnidadService unidadService;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AnyoEscolarService anyoEscolarService;
	
	@MockBean
	private ConvertAnyoEscolarToAnyoEscolarDTO convertAnyoEscolarToAnyoEscolarDTO;
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarGet_devuelve200() throws Exception {
	
		
		Colegio c = Colegio.builder().id(1L).nombre("SalesianosTriana").build();
		
		List<Colegio> lista = new ArrayList<>();
		lista.add(c);
		
		System.out.println(lista.get(0));
		when(colegioService.findAll()).thenReturn(lista);
		
		mockMvc.perform(get("/colegio/")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
			
		
	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarPost_devuelve202() throws Exception {
		
		ColegioDto preSaved = ColegioDto.builder().nombre("Salesianos Triana").build();
		Colegio postSaved = Colegio.builder().id(1L).nombre("Salesianos Triana").build();
		
		when(ConvertColegioToColegioDto.newColegio(preSaved)).thenReturn(postSaved);
		
		MvcResult result = mockMvc
				.perform(post("/colegio/createColegio").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();
		
		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(postSaved)).isEqualToIgnoringCase(resultAsString);
		
	}
}

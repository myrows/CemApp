/**
 * 
 */
package com.salesianostriana.dam.cemapp.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cemapp.controller.AnyoEscolarControler;
import com.salesianostriana.dam.cemapp.dto.AnyoEscolarListarDTO;
import com.salesianostriana.dam.cemapp.dto.AnyoEscolarNewDTO;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAnyoEscolarToAnyoEscolarDTO;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAppUserToAppUserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertColegioToColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertUnidadToUnidadDto;
import com.salesianostriana.dam.cemapp.dto.convert.convertEtapaDtoToEtapa;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.AppUserService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

@WebMvcTest(controllers = AnyoEscolarControler.class)
public class AnyoEscolarControllerTest {
	
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
	
		
		AnyoEscolarListarDTO a = AnyoEscolarListarDTO.builder().id(1L).nombreAnyoEscolar("2018-2019").build();
				
				
		
		List<AnyoEscolarListarDTO> lista = new ArrayList<>();
		lista.add(a);
		
		
		when(convertAnyoEscolarToAnyoEscolarDTO.listAnyos(anyoEscolarService.findAll())).thenReturn(lista);

		mockMvc.perform(get("/anyo/")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
			
		
	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarGet_devuelve404() throws Exception {
		when(anyoEscolarService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/anyo/")).andExpect(status().isNoContent());

	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarPost_devuelve202() throws Exception {
		
		AnyoEscolarNewDTO preSaved = AnyoEscolarNewDTO.builder().nombreAnyoEscolar("NombreTest").build();
		AnyoEscolar postSaved = AnyoEscolar.builder().id(1L)
				.nombreAnyoEscolar("NombreTest")
				.fechaAnyoEscolar(LocalDate.now()).build();
		
		List<Colegio> lista = colegioService.findAll();
		for (Colegio c : lista) {
			postSaved.addColegio(c);
		}
		
		when(convertAnyoEscolarToAnyoEscolarDTO.addAnyo(preSaved)).thenReturn(postSaved);
		
		MvcResult result = mockMvc
				.perform(post("/anyo").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();
		
		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(postSaved)).isEqualToIgnoringCase(resultAsString);
		
	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarPut_devuelve200() throws Exception {
		AnyoEscolar oldAnyo = AnyoEscolar.builder()
				.id(1L)
				.nombreAnyoEscolar("Anyo prueva").build();
		AnyoEscolar newAnyo = AnyoEscolar.builder()
				.id(1L)
				.nombreAnyoEscolar("Nuevo Anyo").build();
		
		when(anyoEscolarService.findById(1L)).thenReturn(oldAnyo);
		when(anyoEscolarService.save(newAnyo)).thenReturn(newAnyo);
		

		MvcResult result = mockMvc

				.perform(put("/anyo/edit/{id}", 1L).contentType("application/json;charset=UTF-8")

						.content(objectMapper.writeValueAsString(newAnyo)))
				.andExpect(status().isAccepted()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(newAnyo)).isEqualToIgnoringCase(resultAsString);
	}
	
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarDelete_devuelve200() throws Exception {
		AnyoEscolar a = AnyoEscolar.builder()
				.id(1L)
				.nombreAnyoEscolar("Anyo prueva")
				.fechaAnyoEscolar(LocalDate.now()).build();
		List<AnyoEscolar> lista = new ArrayList<AnyoEscolar>();
		lista.add(a);
		
		when(anyoEscolarService.findAll()).thenReturn(lista);
		
		
		
	}
	

}

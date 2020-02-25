package com.salesianostriana.dam.cemapp.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cemapp.controller.SuperAdminController;
import com.salesianostriana.dam.cemapp.dto.AppUserDto;
import com.salesianostriana.dam.cemapp.dto.CursoDto;
import com.salesianostriana.dam.cemapp.dto.UnidadListDto;
import com.salesianostriana.dam.cemapp.dto.UserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAppUserToAppUserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertColegioToColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertCursoDtoToCurso;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertUnidadToUnidadDto;
import com.salesianostriana.dam.cemapp.dto.convert.convertEtapaDtoToEtapa;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.repository.AppUserRepository;
import com.salesianostriana.dam.cemapp.service.AppUserService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.EtapaService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

/**
 * 
 * @author danib
 *
 */

@WebMvcTest(controllers = SuperAdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

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
	private CursoService cursoService;
	
	@MockBean
	private EtapaService etapaService;
	
	@MockBean
	private ConvertCursoDtoToCurso convertToCurso;

	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void whenGetWithData_thenReturn200() throws Exception {
	
		
		UserDto u = UserDto.builder()
				.id(new Long(50))
				.email("salesianos@salesianos.edu")
				.roles("ADMIN")
				.colegio("2")
				.build();

		List<UserDto> lista = new ArrayList<>();
		lista.add(u);
		
		when(ConvertAppUserToAppUserDto.listUsers(appUserService.findAll())).thenReturn(lista);
		
		mockMvc.perform(get("/colegio/usuarios")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
			
	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void whenGetWithoutData_thenReturns404() throws Exception {
		when(appUserService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/colegio/usuarios")).andExpect(status().isNoContent());

	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void whenPost_thenReturns201() throws Exception {

		Set<String> s = new HashSet<String>();
		s.add("ADMIN");
		Colegio c = new Colegio();
		c.setNombre("Salesianos");
		
		
		AppUserDto preSaved = AppUserDto.builder()
				.email("cemapp@cemapp.com")
				.password("1234")
				.roles("ADMIN")
				.colegio("Salesianos")
				.build();
		
		AppUser userNuevo = AppUser.builder()
				.id(1L)
				.email("cemapp@cemapp.com")
				.password("1234")
				.roles(s)
				.colegio(c)
				.build();

		when(ConvertAppUserToAppUserDto.newAppUser(preSaved)).thenReturn(userNuevo);

		MvcResult result = mockMvc
				.perform(post("/colegio/createAdmin").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(userNuevo)).isEqualToIgnoringCase(resultAsString);

	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void realizarPut_devuelve200() throws Exception {
		
		Set<String> r;
		
		AppUser oldUser = AppUser.builder()
				.id(1L)
				.email("test@test.com")
				.roles(new HashSet<String>())
				.colegio(new Colegio())
				.build();
		AppUser newUser = AppUser.builder()
				.id(1L)
				.email("test1@test.com")
				.roles(new HashSet<String>())
				.colegio(new Colegio())
				.build();
		
		when(appUserService.findById(1L)).thenReturn(oldUser);
		when(appUserService.save(newUser)).thenReturn(newUser);
		

		MvcResult result = mockMvc
				.perform(put("/colegio/usuario/edit/{id}", 1L).contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(newUser)))
				.andExpect(status().isAccepted()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(newUser)).isEqualToIgnoringCase(resultAsString);
	}

}

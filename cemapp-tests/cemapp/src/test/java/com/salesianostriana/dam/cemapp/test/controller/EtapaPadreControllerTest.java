package com.salesianostriana.dam.cemapp.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.salesianostriana.dam.cemapp.dto.CursoListDto;
import com.salesianostriana.dam.cemapp.dto.EtapaDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAppUserToAppUserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertColegioToColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertCursoDtoToCurso;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertUnidadToUnidadDto;
import com.salesianostriana.dam.cemapp.dto.convert.convertEtapaDtoToEtapa;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
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
class EtapaPadreControllerTest {

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
	@AutoConfigureMockMvc(addFilters = false)
	public void whenGetWithData_thenReturn200() throws Exception {
	
		
		Etapa e = Etapa.builder()
				.id(60)
				.nombre("Formación")
				.peso(10)
				.cursos(new ArrayList<Curso>())
				.padre(null)
				.anyoEscolar(new AnyoEscolar())
				.build();
				
				
		
		List<Etapa> lista = new ArrayList<>();
		lista.add(e);
		
		when(etapaService.searchByPadreNull()).thenReturn(lista);
		
		mockMvc.perform(get("/colegio/etapasPadre")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"));
			
		
	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void whenGetWithoutData_thenReturns404() throws Exception {
		when(etapaService.findAll()).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/colegio/etapasPadre")).andExpect(status().isNoContent());

	}
	
	@Test
	@WithMockUser(username =  "admin@admin.com",password= "admin", roles = "ADMIN")
	public void whenPost_thenReturns201() throws Exception {
		
		EtapaDto preSaved = EtapaDto.builder()
				.nombre("Formación Profesional Open Webinars")
				.padre(null)
				.build();
		Etapa postSaved = Etapa.builder()
				.id(1L)
				.nombre("Formación Profesional Open Webinars")
				.padre(null)
				.build();
		when(convertEtapaDtoToEtapa.newEtapa(preSaved)).thenReturn(postSaved);

		MvcResult result = mockMvc
				.perform(post("/colegio/createEtapa").contentType("application/json;charset=UTF-8")
						.content(objectMapper.writeValueAsString(preSaved)))
				.andExpect(status().isCreated()).andReturn();

		String resultAsString = result.getResponse().getContentAsString();
		assertThat(objectMapper.writeValueAsString(postSaved)).isEqualToIgnoringCase(resultAsString);

	}

}

package com.salesianostriana.dam.cemapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.dam.cemapp.dto.AppUserDto;
import com.salesianostriana.dam.cemapp.dto.ColegioDto;
import com.salesianostriana.dam.cemapp.dto.CursoDto;
import com.salesianostriana.dam.cemapp.dto.CursoListDto;
import com.salesianostriana.dam.cemapp.dto.EtapaDto;
import com.salesianostriana.dam.cemapp.dto.UnidadDto;
import com.salesianostriana.dam.cemapp.dto.UnidadListDto;
import com.salesianostriana.dam.cemapp.dto.UserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAppUserToAppUserDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertColegioToColegioDto;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertCursoDtoToCurso;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertUnidadToUnidadDto;
import com.salesianostriana.dam.cemapp.dto.convert.convertEtapaDtoToEtapa;
import com.salesianostriana.dam.cemapp.models.AppUser;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.service.AppUserService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.EtapaService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/colegio")
@RequiredArgsConstructor
public class SuperAdminController {

	@Autowired
	private ConvertAppUserToAppUserDto convertAppUserToAppUserDto;

	@Autowired
	private ConvertColegioToColegioDto convertToColegio;

	@Autowired
	private ColegioService colegioService;
	
	@Autowired
	private EtapaService etapaService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private UnidadService unidadService;
	
	@Autowired
	private convertEtapaDtoToEtapa convertToEtapa;
	
	@Autowired
	private ConvertCursoDtoToCurso convertToCurso;
	
	@Autowired
	private ConvertUnidadToUnidadDto convertUnidad;

	@GetMapping("/")
	public ResponseEntity<?> getColegios() {
		
		List<Colegio> colegio = colegioService.findAll();
		
		if(colegio.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(colegio);

	}
	
	@GetMapping("/etapasPadre")
	public ResponseEntity<?> getEtapasPadre() {
		
		List<Etapa> etapasPadre = etapaService.searchByPadreNull();
		
		if(etapasPadre.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(etapasPadre);

	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<?> getAllUsuarios() {
		
		//Hago el converter y muestro una Lista dto
		List<UserDto> usuariosAMostrar = convertAppUserToAppUserDto.listUsers(appUserService.findAll());
		
		
		//Si no hay usuarios se devuelve el noContent
		if(usuariosAMostrar.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(usuariosAMostrar);
		
	}
	
	@GetMapping("/etapas")
	public ResponseEntity<?> getEtapas() {
		
		List<Etapa> etapas = etapaService.findAll();
		
		if(etapas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(etapas);

	}
	
//	@GetMapping("/cursos")
//	public ResponseEntity<?> getAllCursos(){
//		List<Curso> cursos = cursoService.findAll();
//		
//		if(cursos.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.ok(cursos);
//	}
	
	@GetMapping("/cursos")
	public ResponseEntity<?> getAllCursos(){
		List<CursoListDto> cursos = convertToCurso.listCurso(cursoService.findAll());
		
		if(cursos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(cursos);
	}
	
//	@GetMapping("/unidades")
//	public ResponseEntity<?> getAllUnidades(){
//		List<Unidad> unidades = unidadService.findAll();
//		
//		if(unidades.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
//		return ResponseEntity.ok(unidades);
//	}
	
	@GetMapping("/unidades")
	public ResponseEntity<?> getAllUnidades(){
		List<UnidadListDto> unidades = convertUnidad.listUnidad(unidadService.findAll());
		
		if(unidades.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(unidades);
	}

	@PostMapping("/createAdmin")
	public ResponseEntity<AppUser> createAdmin(@RequestBody AppUserDto appUserDto) {
		AppUser appUser = convertAppUserToAppUserDto.newAppUser(appUserDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(appUser);

	}

	@PostMapping("/createColegio")
	public ResponseEntity<Colegio> createColegio(@RequestBody ColegioDto colegioDto) {

		Colegio c = convertToColegio.newColegio(colegioDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(c);

	}
	
	@PostMapping("/createEtapa")
	public ResponseEntity<Etapa> createEtapa(@RequestBody EtapaDto etapaDto){
		
		Etapa e =  convertToEtapa.newEtapa(etapaDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(e);
	}
	
	@PostMapping("/createCurso")
	public ResponseEntity<Curso> createCurso(@RequestBody CursoDto cursoDto){
		
		Curso curso =  convertToCurso.newCurso(cursoDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(curso);
	}
	
	@PostMapping("/createUnidad")
	public ResponseEntity<Unidad> createUnidad(@RequestBody UnidadDto unidadDto){
		
		Unidad unidad =  convertUnidad.newUnidad(unidadDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(unidad);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<AppUser> delete(@PathVariable Long id) {
		AppUser a = appUserService.findById(id);

		if (a != null) {
			appUserService.deleteById(id);
			return ResponseEntity.accepted().body(a);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("/usuario/edit/{id}")
	public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody AppUserDto dto) {
		AppUser a = appUserService.findById(id);
		if (a != null) {
			Set<String> rol = new HashSet<String>();
			rol.add(dto.getRoles());
			a.setColegio(colegioService.findById(Long.valueOf(dto.getColegio())));
			a.setEmail(dto.getEmail());
			a.setPassword(dto.getPassword());
			a.setRoles(rol);
			appUserService.save(a);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
		}else{
			return ResponseEntity.notFound().build();
		}
	}
	
	
	

}

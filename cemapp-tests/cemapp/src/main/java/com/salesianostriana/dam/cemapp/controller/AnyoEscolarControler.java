/**
 * 
 */
package com.salesianostriana.dam.cemapp.controller;

import java.util.List;

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

import com.salesianostriana.dam.cemapp.dto.AnyoEscolarListarDTO;
import com.salesianostriana.dam.cemapp.dto.AnyoEscolarNewDTO;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertAnyoEscolarToAnyoEscolarDTO;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;

import lombok.RequiredArgsConstructor;

/**
 * @author cads9
 *
 */

@RestController
@RequestMapping("/anyo")
@RequiredArgsConstructor
public class AnyoEscolarControler {

	private final AnyoEscolarService anyoEscolarService;

	private final ConvertAnyoEscolarToAnyoEscolarDTO convertAnyoEscolarToAnyoEscolarDTO;

	@GetMapping("/")
	public List<AnyoEscolarListarDTO> anyosEscolares() {
		return convertAnyoEscolarToAnyoEscolarDTO.listAnyos(anyoEscolarService.findAll());
	}

	@PostMapping("/add/")
	public ResponseEntity<AnyoEscolar> anyoEscolarAdd(@RequestBody AnyoEscolarNewDTO dto) {

		AnyoEscolar a = convertAnyoEscolarToAnyoEscolarDTO.addAnyo(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(a);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AnyoEscolar> delete(@PathVariable Long id) {
		AnyoEscolar a = anyoEscolarService.findById(id);

		if (a != null) {
			anyoEscolarService.deleteById(id);
			return ResponseEntity.accepted().body(a);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<?> editarProducto(@PathVariable Long id, @RequestBody AnyoEscolarNewDTO anyo) {
		AnyoEscolar a = convertAnyoEscolarToAnyoEscolarDTO.editAnyo(anyo, id);
		if (a != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);
		}else{
			return ResponseEntity.notFound().build();
		}
	}

}

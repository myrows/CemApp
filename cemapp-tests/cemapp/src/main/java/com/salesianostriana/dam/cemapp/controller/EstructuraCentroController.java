package com.salesianostriana.dam.cemapp.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.cemapp.dto.convert.ConvertCsvDTOEstructuraCentro;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.FileStorageService;

@RestController
public class EstructuraCentroController {

	private static final Logger logger = LoggerFactory.getLogger(PsmController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ConvertCsvDTOEstructuraCentro estructuraConverter;

	@Autowired
	private ColegioService colegioService;

	@Autowired
	private AnyoEscolarService anyoEscolarService;

	@PostMapping(path = "/uploadEstructure")
	public void uploadEstructure(@RequestParam("file") MultipartFile file,
			@RequestParam("idAnyoEscolar") String idAnyoEscolar, @RequestParam("idColegio") String idColegio)
			throws IOException {

		String fileName = fileStorageService.store(file);

		estructuraConverter.insertCsvDTOToDatabase(fileName, true, colegioService.findById(Long.valueOf(idColegio)),
				anyoEscolarService.findById(Long.valueOf(idAnyoEscolar)));

	}

}

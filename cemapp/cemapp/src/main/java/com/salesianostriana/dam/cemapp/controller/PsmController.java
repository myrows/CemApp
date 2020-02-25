package com.salesianostriana.dam.cemapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.cemapp.dto.PsmDTO;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertCsvDTOEstructuraCentro;
import com.salesianostriana.dam.cemapp.dto.convert.ConvertCsvDTOProcesosEIndicadores;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.FileStorageService;
import com.salesianostriana.dam.cemapp.service.PuntoControlService;

@RestController
public class PsmController {

	private static final Logger logger = LoggerFactory.getLogger(PsmController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ConvertCsvDTOProcesosEIndicadores psmConverter;

	@Autowired
	private ConvertCsvDTOEstructuraCentro estructuraConverter;

	@Autowired
	private ColegioService colegioService;

	@Autowired
	private AnyoEscolarService anyoEscolarService;

	@Autowired
	private PuntoControlService puntoControlService;

	// Gestionar la subida del archivo

	AnyoEscolar anyoEscolarPrueba = new AnyoEscolar();

	@PostMapping("/uploadPsm")
	public PsmDTO uploadPsm(@RequestParam("file") MultipartFile file,
			@RequestParam("idAnyoEscolar") String idAnyoEscolar, @RequestParam("idColegio") String idColegio,
			@RequestParam("evaluacion") String evaluacion, @RequestParam("idPuntoControl") String idPuntoControl)
			throws IOException {
		String fileName = fileStorageService.store(file);
//		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//				.path("/files/")
//				.path(fileName)
//				.toUriString();

		int peso = 0;
		
		return psmConverter.insertCsvDTOToDatabase("test", anyoEscolarService.findById(Long.valueOf(idAnyoEscolar)),
				colegioService.findById(Long.valueOf(idColegio)), evaluacion, peso,
				null, fileName, true);

	}

	@PostMapping("/uploadMultipleFiles")
	public void uploadMultipleFiles(@RequestPart("files") MultipartFile[] files) {

	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}

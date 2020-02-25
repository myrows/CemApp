package com.salesianostriana.dam.cemapp.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CsvDTOEstructuraCentro;

@Component
public class UploadedCsvImporter {
	private static final  String DELIMITADOR = ";";
	
	
	public  List<CsvDTOEstructuraCentro> procesaCsvDTOProcesosEIndicadores(String fichero, boolean skipHeaders) throws IOException {
		return Files
			.lines(Paths.get(fichero))
			.skip(skipHeaders ? 1 : 0)
			.map(this::mapToCsvDTOEstructuraCentro)
			.collect(Collectors.toList());
	}
	
	public  CsvDTOEstructuraCentro mapToCsvDTOEstructuraCentro(String linea) {
		List<String> datos =  Arrays.asList(linea.split(DELIMITADOR));
		List<String> etapasList = datos.subList(0, datos.size()-2);
		String [] arrayEtapas = new String[datos.size()-2];
		arrayEtapas = etapasList.toArray(arrayEtapas);
		
		return CsvDTOEstructuraCentro.builder()
				.unidad(datos.get(datos.size()))
				.curso(datos.get(datos.size()-1))
				.etapas(arrayEtapas)
				.build();

	}
	
	
}

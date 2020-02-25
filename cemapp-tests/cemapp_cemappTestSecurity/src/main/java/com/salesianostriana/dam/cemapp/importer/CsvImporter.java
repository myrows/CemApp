package com.salesianostriana.dam.cemapp.importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CsvDTOProcesosEIndicadores;



@Component
public class CsvImporter {
	
	private static final  String DELIMITADOR = ";";
	private static final  int C_PROCESO = 0;
	private static final  int C_INDICADOR = 1;
	private static final  int C_ETAPA = 2;
	private static final  int C_CICLO = 3;
	private static final  int C_CURSO = 4;
	private static final  int C_UNIDAD = 5;
	private static final  int C_VALORR = 6;
	private static final  int C_VALORC = 7;
	
		
	
	public  CsvDTOProcesosEIndicadores mapToCsvDTOProcesosEIndicadores(String linea) {
		String[] datos = linea.split(DELIMITADOR);
		
		return CsvDTOProcesosEIndicadores
				.builder()
				.nombreProceso(datos[C_PROCESO])
				.nombreIndicador(datos[C_INDICADOR])
				.etapa(datos[C_ETAPA])
				.ciclo(datos[C_CICLO])
				.curso(datos[C_CURSO])
				.unidad(datos[C_UNIDAD])
				.valorR(datos[C_VALORR])
				.valorC(datos[C_VALORC])
				.build();

	}
	
	public  List<CsvDTOProcesosEIndicadores> procesaCsvDTOProcesosEIndicadores(String fichero, boolean skipHeaders) throws IOException {
		return Files
			.lines(Paths.get(fichero))
			.skip(skipHeaders ? 1 : 0)
			.map(this::mapToCsvDTOProcesosEIndicadores)
			.collect(Collectors.toList());
	}

}

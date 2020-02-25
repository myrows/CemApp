package com.salesianostriana.dam.cemapp.dto.convert;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CsvDTOEstructuraCentro;

import com.salesianostriana.dam.cemapp.importer.UploadedCsvImporter;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.EtapaService;
import com.salesianostriana.dam.cemapp.service.UnidadService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ConvertCsvDTOEstructuraCentro {

	UploadedCsvImporter uploadedCsvImporter;

	private EtapaService etapaService;
	private CursoService cursoService;
	private UnidadService unidadService;

	public void insertCsvDTOToDatabase(String archivo, boolean saltarCabecera) throws IOException {
		List<CsvDTOEstructuraCentro> lista = uploadedCsvImporter.procesaCsvDTOProcesosEIndicadores(archivo,
				saltarCabecera);
		

		for (CsvDTOEstructuraCentro linea : lista) {

			Etapa etapaPadre = null;

			// Convierte array de String etapas a arrayList de strings etapas
			List<String> listaEtapas = Arrays.asList(linea.getEtapas());
			

			// Crea unidad
			Unidad unidad = new Unidad();
			unidad.setNombre(linea.getUnidad());
			unidadService.save(unidad);

			// Crea curso
			Curso curso = new Curso();
			curso.setNombre(linea.getCurso());
			cursoService.save(curso);

			// Crea etapas
			for (String etapaSeleccionada : listaEtapas) {

				Etapa etapa = new Etapa();
				etapa.setNombre(etapaSeleccionada);
				// Si tiene una etapa padre, se la setea
				if (etapaPadre != null) {
					etapa.setPadre(etapaPadre);
				}
				etapaPadre = etapa;
				etapaService.save(etapa);
			}
		}

	}

}

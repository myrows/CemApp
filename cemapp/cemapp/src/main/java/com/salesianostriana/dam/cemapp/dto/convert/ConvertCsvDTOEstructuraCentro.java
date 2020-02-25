package com.salesianostriana.dam.cemapp.dto.convert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CsvDTOEstructuraCentro;
import com.salesianostriana.dam.cemapp.importer.UploadedCsvImporter;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.models.ValorIndicadorUnidad;
import com.salesianostriana.dam.cemapp.service.ColegioService;
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

	private ColegioService colegioService;

	public void insertCsvDTOToDatabase(String archivo, boolean saltarCabecera, Colegio colegio, AnyoEscolar anyoEscolar)
			throws IOException {
		String ruta = "files/" + archivo;

		List<CsvDTOEstructuraCentro> lista = uploadedCsvImporter.procesaCsvDTOProcesosEIndicadores(ruta,
				saltarCabecera);

		for (CsvDTOEstructuraCentro linea : lista) {

			Etapa etapaPadre = null;
			Etapa etapaHija = null;

			// Convierte array de String etapas a arrayList de strings etapas
			List<String> listaEtapas = Arrays.asList(linea.getEtapas());

			// Crea unidad
			Unidad unidad = new Unidad();
			unidad.setNombre(linea.getUnidad());

			// Crea curso
			boolean cursoExistente = comprobarCursoEsExistenteYConColegioYEnAnyo(linea.getCurso(), colegio,
					anyoEscolar);

			Curso curso = cursoExistente
					? buscarCursoEsExistenteYConColegioYEnAnyo(linea.getCurso(), colegio, anyoEscolar)
					: new Curso();
			if (!cursoExistente) {
				curso.setNombre(linea.getCurso());
			}

			curso.getUnidades().add(unidad);
			cursoService.save(curso);
			unidad.setCurso(curso);
			List<ValorIndicadorUnidad> listaValorIndicadorUnidad = new ArrayList<>();
			unidad.setVIndicadoresUnidades(listaValorIndicadorUnidad);
			unidadService.save(unidad);

			// Crea etapas
			for (String etapaSeleccionada : listaEtapas) {
				boolean etapaExistente = comprobarEtapaEsExistenteYConColegioYEnAnyo(etapaSeleccionada, colegio,
						anyoEscolar);
				List<Curso> cursos = new ArrayList<>();
				Etapa etapa = etapaExistente
						? buscarEtapaEsExistenteYConColegioYEnAnyo(etapaSeleccionada, colegio, anyoEscolar)
						: Etapa.builder().nombre(etapaSeleccionada).cursos(cursos).anyoEscolar(anyoEscolar).build();

				etapa.getCursos().add(curso);
				// Si tiene una etapa padre, se la setea
				if (etapaPadre != null) {
					etapa.setPadre(etapaPadre);
				}
				etapaPadre = etapa;
				etapaService.save(etapa);

				if (!etapaExistente) {
					colegio.getEtapas().add(etapa);
					colegioService.edit(colegio);
				}
			}

			curso.setEtapa(etapaPadre);
			cursoService.edit(curso);
		}

	}

	public boolean comprobarEtapaEsExistenteYConColegioYEnAnyo(String etapa, Colegio colegio, AnyoEscolar anyoEscolar) {
		return colegio.getEtapas().contains(etapaService.encontrarPorNombre(etapa))
				&& etapaService.encontrarPorNombre(etapa).getAnyoEscolar().equals(anyoEscolar);

	}

	public Etapa buscarEtapaEsExistenteYConColegioYEnAnyo(String etapa, Colegio colegio, AnyoEscolar anyoEscolar) {
		Etapa result = null;

		for (Etapa etapaEntity : colegio.getEtapas()) {
			if (etapaEntity.getNombre().equals(etapa) && etapaEntity.getAnyoEscolar().equals(anyoEscolar)) {
				result = etapaEntity;
			}
		}
		return result;

	}

	public boolean comprobarCursoEsExistenteYConColegioYEnAnyo(String curso, Colegio colegio, AnyoEscolar anyoEscolar) {

		boolean result = false;

		for (Etapa etapaEntity : colegio.getEtapas()) {
			if (etapaEntity.getAnyoEscolar().equals(anyoEscolar)) {
				for (Curso cursoEntity : etapaEntity.getCursos()) {
					if (cursoEntity.getNombre().equals(curso)) {
						result = cursoEntity.getNombre().equals(curso);
					}
				}
			}
		}
		return result;
	}

	public Curso buscarCursoEsExistenteYConColegioYEnAnyo(String curso, Colegio colegio, AnyoEscolar anyoEscolar) {
		Curso result = null;
		for (Etapa etapaEntity : colegio.getEtapas()) {
			if (etapaEntity.getAnyoEscolar().equals(anyoEscolar)) {
				for (Curso cursoEntity : etapaEntity.getCursos()) {
					if (cursoEntity.getNombre().equals(curso)) {
						result = cursoEntity;
					}

				}
			}

		}

		return result;
	}

}

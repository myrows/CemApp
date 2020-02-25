package com.salesianostriana.dam.cemapp.dto.convert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.cemapp.dto.CsvDTOProcesosEIndicadores;
import com.salesianostriana.dam.cemapp.dto.PsmDTO;
import com.salesianostriana.dam.cemapp.importer.CsvImporter;
import com.salesianostriana.dam.cemapp.models.AnyoEscolar;
import com.salesianostriana.dam.cemapp.models.Colegio;
import com.salesianostriana.dam.cemapp.models.Curso;
import com.salesianostriana.dam.cemapp.models.Etapa;
import com.salesianostriana.dam.cemapp.models.Indicador;
import com.salesianostriana.dam.cemapp.models.PSM;
import com.salesianostriana.dam.cemapp.models.Proceso;
import com.salesianostriana.dam.cemapp.models.PuntoControl;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.models.ValorIndicador;
import com.salesianostriana.dam.cemapp.service.AnyoEscolarService;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.CursoService;
import com.salesianostriana.dam.cemapp.service.EtapaService;
import com.salesianostriana.dam.cemapp.service.IndicadorService;
import com.salesianostriana.dam.cemapp.service.PSMService;
import com.salesianostriana.dam.cemapp.service.ProcesoService;
import com.salesianostriana.dam.cemapp.service.PuntoControlService;
import com.salesianostriana.dam.cemapp.service.UnidadService;
import com.salesianostriana.dam.cemapp.service.ValorIndicadorService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ConvertCsvDTOProcesosEIndicadores {

	private CsvImporter csvImporter;
	private ProcesoService procesoService;
	private EtapaService etapaService;
	private CursoService cursoService;
	private UnidadService unidadService;
	private IndicadorService indicadorService;
	private PSMService psmService;
	private AnyoEscolarService anyoEscolarService;
	private PuntoControlService puntoControlService;
	private ColegioService colegioService;
	private ValorIndicadorService valorIndicadorService;

// Atributos del dto
//	private String nombreProceso;
//	private String nombreIndicador;
//	private String etapa;
//	private String ciclo;
//	private String curso;
//	private String unidad;
//	private String valorR;
//	private String valorC;
//  Fijarse bien en lo que hay que insertar, faltan campos pero no se
// hacerlo bien por la estructura y funcionamiento del app.

	public PsmDTO insertCsvDTOToDatabase(String nombrePsm, AnyoEscolar anyoEscolar, Colegio colegio, String evaluacion, int peso,PuntoControl puntoControl, String archivo, boolean saltarCabecera) throws IOException {

		List<CsvDTOProcesosEIndicadores> lista = csvImporter.procesaCsvDTOProcesosEIndicadores(archivo, saltarCabecera);
		List <ValorIndicador> valoresIndicadores = new ArrayList<>();
		PSM psm = new PSM();

		for (CsvDTOProcesosEIndicadores lineaDTO : lista) {

			//Comprueba si existe o no
			boolean procesoExistente = procesoService.esExistente(lineaDTO.getNombreProceso());
			boolean indicadorExistente = indicadorService.esExistente(lineaDTO.getNombreIndicador());
			boolean etapaExistente = etapaService.esExistente(lineaDTO.getCiclo());
			boolean cursoExistente = cursoService.esExistente(lineaDTO.getCurso());
			boolean unidadExistente = unidadService.esExistente(lineaDTO.getUnidad());
			
			

			//Si existe lo busca, si no, lo crea
			Proceso proceso = procesoExistente ? procesoService.encontrarPorNombre(lineaDTO.getNombreProceso())
					: new Proceso();
			Indicador indicador = indicadorExistente
					? indicadorService.encontrarPorNombre(lineaDTO.getNombreIndicador())
					: new Indicador();
			Etapa etapa = etapaExistente ? etapaService.encontrarPorNombre(lineaDTO.getEtapa()) : new Etapa();
			Curso curso = cursoExistente ? cursoService.encontrarPorNombre(lineaDTO.getCurso()) : new Curso();
			Unidad unidad = unidadExistente ? unidadService.encontrarPorNombre(lineaDTO.getUnidad()) : new Unidad();

			
			Double valorReal = lineaDTO.getValorR().equals("NA")  ? null : Double.parseDouble(lineaDTO.getValorR());
			Double valorConforme = lineaDTO.getValorC().equals("NaN") ? null : Double.parseDouble(lineaDTO.getValorC());
			boolean valorRealNoAplica = valorReal == null ? true : false;
			boolean valorConformeNoAplica = valorConforme == null ? true : false;
			
			ValorIndicador valorIndicador = ValorIndicador.builder().valorReal(valorReal).valorConforme(valorConforme).psm(psm).valorRealNoAplica(valorRealNoAplica).valorConformeNoAplica(valorConformeNoAplica).build();
			valorIndicadorService.save(valorIndicador);
			valoresIndicadores.add(valorIndicador);
			
			if (!procesoExistente) {
				proceso.setNombre(lineaDTO.getNombreProceso());
				if (indicadorExistente) {
					proceso.getIndicadores().add(indicador);
				} else {
					indicador.setNombre(lineaDTO.getNombreIndicador());
					indicadorService.save(indicador);
				}
				
				procesoService.save(proceso);
			}
	
			

			if (!cursoExistente) {
				curso.setNombre(lineaDTO.getCurso());
				if (!etapaExistente) {
					etapa.setNombre(lineaDTO.getEtapa());
				}
				curso.setEtapa(etapa);
				etapa.getCursos().add(curso);
				etapaService.edit(etapa);
				cursoService.save(curso);
				etapa.getCursos().add(curso);
			}

			if (!unidadExistente) {
				unidad.setNombre(lineaDTO.getUnidad());
				unidad.setCurso(curso);
				unidadService.save(unidad);
				curso.getUnidades().add(unidad);
				cursoService.edit(curso);
				

			}

			

		}
		
		
		
		
		
		psm.setNombre(nombrePsm);
		psm.setEvaluacion(evaluacion);
		psm.setPeso(peso);
		psm.setPuntoControl(puntoControl);
		psm.setColegio(colegio);
		psm.setAnyoEscolar(anyoEscolar);
		psm.setValoresIndicadores(valoresIndicadores);
		psmService.save(psm);
		
		
		return PsmDTO.builder()
					 .nombre(nombrePsm)
					 .anyoEscolar(anyoEscolar)
					 .colegio(colegio)
					 .evaluacion(evaluacion)
					 .puntoControl(puntoControl)
					 .build();
					 

	}
}

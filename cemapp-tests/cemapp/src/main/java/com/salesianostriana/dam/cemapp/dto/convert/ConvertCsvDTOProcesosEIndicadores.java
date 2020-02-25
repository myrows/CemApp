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
import com.salesianostriana.dam.cemapp.models.IndicadorCentro;
import com.salesianostriana.dam.cemapp.models.IndicadorUnidad;
import com.salesianostriana.dam.cemapp.models.PSM;
import com.salesianostriana.dam.cemapp.models.Proceso;
import com.salesianostriana.dam.cemapp.models.PuntoControl;
import com.salesianostriana.dam.cemapp.models.Unidad;
import com.salesianostriana.dam.cemapp.models.ValorIndicador;
import com.salesianostriana.dam.cemapp.models.ValorIndicadorCentro;
import com.salesianostriana.dam.cemapp.models.ValorIndicadorUnidad;
import com.salesianostriana.dam.cemapp.service.ColegioService;
import com.salesianostriana.dam.cemapp.service.IndicadorCentroService;
import com.salesianostriana.dam.cemapp.service.IndicadorUnidadService;
import com.salesianostriana.dam.cemapp.service.PSMService;
import com.salesianostriana.dam.cemapp.service.ProcesoService;
import com.salesianostriana.dam.cemapp.service.UnidadService;
import com.salesianostriana.dam.cemapp.service.ValorIndicadorCentroService;
import com.salesianostriana.dam.cemapp.service.ValorIndicadorUnidadService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ConvertCsvDTOProcesosEIndicadores {

	private CsvImporter csvImporter;
	private ProcesoService procesoService;
//	private EtapaService etapaService;
//	private CursoService cursoService;
	private UnidadService unidadService;
	private PSMService psmService;
//	private AnyoEscolarService anyoEscolarService;
//	private PuntoControlService puntoControlService;
	private ColegioService colegioService;
	private ValorIndicadorCentroService valorIndicadorCentroService;
	private ValorIndicadorUnidadService valorIndicadorUnidadService;
	private IndicadorCentroService indicadorCentroService;
	private IndicadorUnidadService indicadorUnidadService;

	public PsmDTO insertCsvDTOToDatabase(String nombrePsm, AnyoEscolar anyoEscolar, Colegio colegio, String evaluacion,
			int peso, PuntoControl puntoControl, String archivo, boolean saltarCabecera) throws IOException {

		String ruta = "files/" + archivo;
		List<CsvDTOProcesosEIndicadores> lista = csvImporter.procesaCsvDTOProcesosEIndicadores(ruta, saltarCabecera);
		List<ValorIndicador> valoresIndicadores = new ArrayList<>();
		PSM psm = new PSM();

		int pesoP = 1;

		for (CsvDTOProcesosEIndicadores lineaDTO : lista) {

			// Comprueba si existe o no
			boolean procesoExis = procesoService.esExistente(lineaDTO.getNombreProceso());
			boolean procesoExistente = procesoExis ? procesoService.encontrarPorNombre(lineaDTO.getNombreProceso()).getAnyoEscolar().equals(anyoEscolar) : false;
			// Si existe lo busca, si no, lo crea
			Proceso proceso = procesoExistente ? procesoService.encontrarPorNombre(lineaDTO.getNombreProceso())
					: new Proceso();

			boolean indicadorUExistente = indicadorUnidadService.esExistente(lineaDTO.getNombreIndicador(), proceso);
			boolean indicadorCExistente = indicadorCentroService.esExistente(lineaDTO.getNombreIndicador(), proceso);
			boolean esCentro = lineaDTO.getEtapa().equals("centro");

			IndicadorCentro indicadorC = new IndicadorCentro();
			IndicadorUnidad indicadorU = new IndicadorUnidad();

			if (esCentro) {

				indicadorC = indicadorCExistente
						? indicadorCentroService.encontrarPorNombreYProceso(lineaDTO.getNombreIndicador(), proceso)
						: new IndicadorCentro();
			} else {

				indicadorU = indicadorUExistente
						? indicadorUnidadService.encontrarPorNombreYProceso(lineaDTO.getNombreIndicador(), proceso)
						: new IndicadorUnidad();
			}

			Unidad unidad = unidadService.encontrarPorNombre(lineaDTO.getUnidad());

			Double valorReal = lineaDTO.getValorR().equals("NA") ? null : Double.valueOf(lineaDTO.getValorR());
			Double valorConforme = lineaDTO.getValorC().equals("NaN") ? null : Double.valueOf(lineaDTO.getValorC());
			boolean valorRealNoAplica = valorReal == null;
			boolean valorConformeNoAplica = valorConforme == null;

			if (!procesoExistente) {
				proceso.setNombre(lineaDTO.getNombreProceso());
				proceso.setPeso(pesoP);
				proceso.setAnyoEscolar(anyoEscolar);

				pesoP++;
				proceso.setTipo(verTipo(lineaDTO.getNombreProceso()));
				if (esCentro) {
					if (indicadorCExistente) {
						proceso.getIndicadores().add(indicadorC);
					} else {
						indicadorC.setNombre(lineaDTO.getNombreIndicador());
						indicadorC.setEsPorcentaje(verEsPorcentaje(lineaDTO.getNombreIndicador()));
						List<ValorIndicadorCentro> listaValorIndicadorCentro = new ArrayList<>();
						indicadorC.setListaValorIndicadorCentro(listaValorIndicadorCentro);
						indicadorC.getColegios().add(colegio);
						indicadorCentroService.save(indicadorC);
						colegio.getIndicadoresCentro().add(indicadorC);
						colegioService.edit(colegio);
						proceso.getIndicadores().add(indicadorC);
					}

					indicadorC.setProceso(proceso);
				} else {
					if (indicadorUExistente) {
						proceso.getIndicadores().add(indicadorU);
					} else {
						indicadorU.setNombre(lineaDTO.getNombreIndicador());
						indicadorU.setEsPorcentaje(verEsPorcentaje(lineaDTO.getNombreIndicador()));

						List<ValorIndicadorUnidad> listaValorIndicadorUnidad = new ArrayList<>();
						indicadorU.setListaValorIndicadorUnidad(listaValorIndicadorUnidad);
						indicadorU.getUnidad().add(unidad);
						indicadorUnidadService.save(indicadorU);
						unidad.getIndicadoresUnidad().add(indicadorU);
						unidadService.edit(unidad);
						proceso.getIndicadores().add(indicadorU);
					}
					indicadorU.setProceso(proceso);
				}
				procesoService.save(proceso);

			} else {

				if (esCentro) {
					if (indicadorCExistente) {
						proceso.getIndicadores().add(indicadorC);
					} else {
						indicadorC.setNombre(lineaDTO.getNombreIndicador());
						indicadorC.setEsPorcentaje(verEsPorcentaje(lineaDTO.getNombreIndicador()));
						List<ValorIndicadorCentro> listaValorIndicadorCentro = new ArrayList<>();
						indicadorC.setListaValorIndicadorCentro(listaValorIndicadorCentro);
						indicadorC.getColegios().add(colegio);
						indicadorCentroService.save(indicadorC);
						colegioService.findById(colegio.getId()).getIndicadoresCentro().add(indicadorC);
						colegioService.edit(colegio);
						proceso.getIndicadores().add(indicadorC);
					}

					indicadorC.setProceso(proceso);
				} else {
					if (indicadorUExistente) {
						proceso.getIndicadores().add(indicadorU);
					} else {
						indicadorU.setNombre(lineaDTO.getNombreIndicador());
						indicadorU.setEsPorcentaje(verEsPorcentaje(lineaDTO.getNombreIndicador()));
						List<ValorIndicadorUnidad> listaValorIndicadorUnidad = new ArrayList<>();
						indicadorU.setListaValorIndicadorUnidad(listaValorIndicadorUnidad);
						indicadorU.getUnidad().add(unidad);
						indicadorUnidadService.save(indicadorU);
						unidad.getIndicadoresUnidad().add(indicadorU);
						unidadService.edit(unidad);
						proceso.getIndicadores().add(indicadorU);
					}
					indicadorU.setProceso(proceso);
				}
				procesoService.edit(proceso);
			}

			if (lineaDTO.getEtapa().equals("centro")) {

				ValorIndicadorCentro valorIndicadorCentro = valorIndicadorCentroService.save(new ValorIndicadorCentro(
						valorReal, valorConforme, valorRealNoAplica, valorConformeNoAplica, colegio, indicadorC));
				valoresIndicadores.add(valorIndicadorCentro);
				colegio.getVIndicadorCentro().add(valorIndicadorCentro);

				indicadorC.getListaValorIndicadorCentro().add(valorIndicadorCentro);
				indicadorCentroService.edit(indicadorC);

				colegioService.edit(colegio);

			} else {
				ValorIndicadorUnidad valorIndicadorUnidad = valorIndicadorUnidadService.save(new ValorIndicadorUnidad(
						valorReal, valorConforme, valorRealNoAplica, valorConformeNoAplica, unidad, indicadorU));
				valoresIndicadores.add(valorIndicadorUnidad);

				unidad.getVIndicadoresUnidades().add(valorIndicadorUnidad);

				indicadorU.getListaValorIndicadorUnidad().add(valorIndicadorUnidad);
				indicadorUnidadService.edit(indicadorU);

				unidadService.edit(unidad);
			}

		}

		psm.setNombre(nombrePsm);
		psm.setEvaluacion(evaluacion);
		psm.setPeso(peso);
		psm.setPuntoControl(puntoControl);
		psm.setColegio(colegio);
		psm.setAnyoEscolar(anyoEscolar);
		psm.setValoresIndicadores(valoresIndicadores);
		psmService.edit(psm);

		return PsmDTO.builder().nombre(nombrePsm).anyoEscolar(anyoEscolar).colegio(colegio).evaluacion(evaluacion)
				.puntoControl(puntoControl).fileName(archivo).build();

	}

	public String verTipo(String nombreProceso) {

		return nombreProceso.contains("P.E.") ? "P.E." : "P.C.";

	}

	// TODO Comprobar
	public boolean verEsPorcentaje(String nombreIndicador) {

		// nombreIndicador = nombreIndicador.toLowerCase();

		return nombreIndicador.contains("%") || nombreIndicador.contains("Porcentaje") ? true : false;
	}

}

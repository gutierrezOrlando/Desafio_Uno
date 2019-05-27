package com.desafio.uno.client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.desafio.uno.model.Periodos;

@Component
@PropertySource("classpath:application.properties")
public class CallRestServices implements CommandLineRunner {

	final static Logger LOGGER = LoggerFactory.getLogger(CallRestServices.class);

	// lista para dias del servicio
	List<Periodos> listaServicio = new ArrayList<>();

	// listas para dias calculados entre intervalos
	static List<LocalDate> listaCompleta = new ArrayList<>();

	public void callService() {

		CallRestServices service = new CallRestServices();

		RestTemplate template = new RestTemplate();
		Periodos periodos = template.getForObject("http://127.0.0.1:8080/periodos/api", Periodos.class);

//		LOGGER.info("\nLos datos son : \nID: [{}]  \nFecha Inicio: [{}] \nFechaFin: [{}] \nCantidad De fechas: [{}]", periodos.getId() ,periodos.getFechaCreacion(),periodos.getFechaFin(), periodos.getFechas().size());

		// lista de días que deberian ser el primero de cada mes desde el intervalo
		listaCompleta = service.crearListaFechas(periodos.getFechaCreacion(), periodos.getFechaFin());

		List<LocalDate> fechasFaltantes = fechasFaltantes(listaCompleta, periodos.getFechas());

		LOGGER.info(
				"\nCantidad de fechas Total : [{}] \nCantidad de fechas recibidas: [{}]\nCantidad Fechas Faltantes [{}]\nSuma faltantes mas recibidas : [{}]",
				listaCompleta.size(), periodos.getFechas().size(), fechasFaltantes.size(),
				(periodos.getFechas().size() + fechasFaltantes.size()));
		service.crearArchivo(periodos.getFechaCreacion(), periodos.getFechaFin(), periodos.getFechas(),
				fechasFaltantes);

	}

	public List<LocalDate> fechasFaltantes(List<LocalDate> listaCompleta, List<LocalDate> listaEntregada) {
		List<LocalDate> fechasFaltantes = new ArrayList<>();

		listaCompleta.forEach(fecha -> {
			if (!listaEntregada.contains(fecha)) {
				fechasFaltantes.add(fecha);
			}
		});

		return fechasFaltantes;

	}

	@Override
	public void run(String... args) {
		callService();
	}

	public List<LocalDate> crearListaFechas(@Valid LocalDate fechaInicio, @Valid LocalDate fechaFin) {

		List<LocalDate> listaFechas = new ArrayList<>();

		while (!fechaInicio.isAfter(fechaFin)) {
			listaFechas.add(fechaInicio);
			fechaInicio = fechaInicio.plusMonths(1L);
		}
		return listaFechas;

	}

	// creo directorio y archivo con la comparación
	public void crearArchivo(LocalDate fchIni, LocalDate fchFin, List<LocalDate> getFechas,
			List<LocalDate> fechasFaltantes) {

		FileWriter flwriter = null;
		try {
			File directorio = new File("c:\\Desafio\\");

			if (!directorio.mkdir()) {
				LOGGER.info("Existe Directorio");

			} else {
				LOGGER.info("No Existe Directorio");
			}

			// crea el flujo para escribir en el archivo
			flwriter = new FileWriter("C:\\Desafio\\periodos.txt");

			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			//escribe los datos en el archivo
			bfwriter.write("fecha creación:" + fchIni);
			bfwriter.newLine();
			bfwriter.write("fecha fin:" + fchFin);
			bfwriter.newLine();
			bfwriter.write("fechas recibidas:" + getFechas);
			bfwriter.newLine();
			bfwriter.write("fechas faltantes:" + fechasFaltantes);

			// cierro el buffer
			bfwriter.close();
			LOGGER.info("Archivo creado satisfactoriamente..");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {// cierro el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

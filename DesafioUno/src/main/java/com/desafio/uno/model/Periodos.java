package com.desafio.uno.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Periodo
 */
@Validated

public class Periodos   {

	@JsonProperty("id")
	private Long id = null;

	@JsonProperty("fechaCreacion")
	private LocalDate fechaCreacion;

	@JsonProperty("fechaFin")
	private LocalDate fechaFin;

	@JsonProperty("fechas")
	@Valid
	private List<LocalDate> fechas ;

	public Periodos id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * @return id
	 **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Periodos fechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
		return this;
	}

	/**
	 * Get fechaCreacion
	 * @return fechaCreacion
	 **/


	@Valid

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Periodos fechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
		return this;
	}

	/**
	 * Get fechaFin
	 * @return fechaFin
	 **/

	@Valid

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Periodos fechas(List<LocalDate> fechas) {
		this.fechas = fechas;
		return this;
	}

	public Periodos addFechasItem(LocalDate fechasItem) {
		if (this.fechas == null) {
			this.fechas = new ArrayList<>();
		}
		this.fechas.add(fechasItem);
		return this;
	}

	/**
	 * Get fechas
	 * @return fechas
	 **/

	@Valid

	public List<LocalDate> getFechas() {
		return fechas;
	}

	public void setFechas(List<LocalDate> fechas) {
		this.fechas = fechas;
	}


	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Periodos periodo = (Periodos) o;
		return Objects.equals(this.id, periodo.id) &&
				Objects.equals(this.fechaCreacion, periodo.fechaCreacion) &&
				Objects.equals(this.fechaFin, periodo.fechaFin) &&
				Objects.equals(this.fechas, periodo.fechas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, fechaCreacion, fechaFin, fechas);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Periodo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    fechaCreacion: ").append(toIndentedString(fechaCreacion)).append("\n");
		sb.append("    fechaFin: ").append(toIndentedString(fechaFin)).append("\n");
		sb.append("    fechas: ").append(toIndentedString(fechas)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}


package entidades;

import java.time.LocalDate;

public class TareaDeMantenimiento 
{
	private Integer id;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private String observaciones;
	
	public TareaDeMantenimiento() {}
	
	public TareaDeMantenimiento(LocalDate fechaInicio, LocalDate fechaFin, String observaciones) 
	{
		// El id lo asigna la DB
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observaciones = observaciones;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
}

package entidades;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Estacion 
{
	public enum Estado
	{
		OPERATIVA,
		EN_MANTENIMIENTO
	};
	
	private Integer id;
	private String nombre;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private Estado estado;
	private List <Integer> idsMantenimientosRealizados;
	
	public Estacion() {
		idsMantenimientosRealizados = new ArrayList<Integer>();
	}
	
	public Estacion(String nombre, LocalTime horaApertura, LocalTime horaCierre, Estado estado)
	{	
		//El id lo asigna la DB
		
		this.nombre = nombre;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.estado = estado;
		
		idsMantenimientosRealizados = new ArrayList<Integer>();
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalTime getHoraApertura() {
		return horaApertura;
	}
	public void setHoraApertura(LocalTime horaApertura) {
		this.horaApertura = horaApertura;
	}
	public LocalTime getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Integer> getIdsMantenimientosRealizados() {
		return idsMantenimientosRealizados;
	}
	public void setIdsMantenimientosRealizados(List<Integer> idsMantenimientosRealizados) {
		this.idsMantenimientosRealizados = idsMantenimientosRealizados;
	}
}

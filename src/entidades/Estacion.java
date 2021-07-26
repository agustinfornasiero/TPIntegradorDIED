package entidades;

import java.time.LocalTime;
import java.util.LinkedList;
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
	private List <TareaDeMantenimiento> mantenimientosRealizados;
	
	public Estacion()
	{
		mantenimientosRealizados = new LinkedList<TareaDeMantenimiento>();
	}
	
	public Estacion(String nombre, LocalTime horaApertura, LocalTime horaCierre, Estado estado)
	{	
		//El id lo asigna la DB
		
		this.nombre = nombre;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.estado = estado;
		
		mantenimientosRealizados = new LinkedList<TareaDeMantenimiento>();
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
	public List<TareaDeMantenimiento> getMantenimientosRealizados() {
		return mantenimientosRealizados;
	}
	public void setMantenimientosRealizados(List<TareaDeMantenimiento> mantenimientosRealizados) {
		this.mantenimientosRealizados = mantenimientosRealizados;
	}
}

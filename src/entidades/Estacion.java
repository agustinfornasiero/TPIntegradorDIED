package entidades;

import java.time.LocalTime;
import java.util.List;

public class Estacion 
{
	public enum EstadoEstacion
	{
		OPERATIVA,
		EN_MANTENIMIENTO
	}
	
	private Integer id;
	private String nombre;
	private LocalTime horaApertura;
	private LocalTime horaCierre;
	private EstadoEstacion estado;
	private List <TareaDeMantenimiento> mantenimientosRealizados;
	
	
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
	public EstadoEstacion getEstado() {
		return estado;
	}
	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}
	public List<TareaDeMantenimiento> getMantenimientosRealizados() {
		return mantenimientosRealizados;
	}
	public void setMantenimientosRealizados(List<TareaDeMantenimiento> mantenimientosRealizados) {
		this.mantenimientosRealizados = mantenimientosRealizados;
	}
}

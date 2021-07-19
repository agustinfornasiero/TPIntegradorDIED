package entidades;

import java.util.List;

public class LineaDeTransporte 
{
	public enum EstadoLineaDeTransporte
	{
		ACTIVA,
		INACTIVA
	}
	
	private Integer id;
	private String nombre;
	private String color;
	private EstadoLineaDeTransporte estado;
	private List<Estacion> estacionesLinea;
	
	
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public EstadoLineaDeTransporte getEstado() {
		return estado;
	}
	public void setEstado(EstadoLineaDeTransporte estado) {
		this.estado = estado;
	}
	public List<Estacion> getEstacionesLinea() {
		return estacionesLinea;
	}
	public void setEstacionesLinea(List<Estacion> estacionesLinea) {
		this.estacionesLinea = estacionesLinea;
	}
}

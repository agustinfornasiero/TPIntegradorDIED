package entidades;

import entidades.LineaDeTransporte.Estado;

public class Tramo 
{
	public enum Estado 
	{
		ACTIVO,
		INACTIVO
	}
	
	private Integer id;
	private Double distanciaEnKm;
	private Integer duracionViajeEnMin;
	private Integer cantidadMaximaPasajeros;
	private Estado estado;
	private Double costo;
	private Integer idOrigen;
	private Integer idDestino;
	
	public Tramo() {}
	
	public Tramo(
		Double distanciaEnKm, Integer duracionViajeEnMin, Integer cantidadMaximaPasajeros, 
		Estado estado, Double costo, Integer idOrigen, Integer idDestino
	) 
	{	
		// El id lo asigna la DB
		
		this.distanciaEnKm = distanciaEnKm;
		this.duracionViajeEnMin = duracionViajeEnMin;
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
		this.estado = estado;
		this.costo = costo;
		this.idOrigen = idOrigen;
		this.idDestino = idDestino;
	}
	
	public String toString()
	{
		return "[" + id + ", " + distanciaEnKm + ", " + duracionViajeEnMin + ", " + cantidadMaximaPasajeros + ", " +
				((estado == Estado.ACTIVO)? "ACTIVO" : "INACTIVO") + ", " + costo + ", " + idOrigen + ", " + idDestino + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getDistanciaEnKm() {
		return distanciaEnKm;
	}
	public void setDistanciaEnKm(Double distanciaEnKm) {
		this.distanciaEnKm = distanciaEnKm;
	}
	public Integer getDuracionViajeEnMin() {
		return duracionViajeEnMin;
	}
	public void setDuracionViajeEnMin(Integer duracionViajeEnMin) {
		this.duracionViajeEnMin = duracionViajeEnMin;
	}
	public Integer getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public void setCantidadMaximaPasajeros(Integer cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Integer getIdOrigen() {
		return idOrigen;
	}
	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
	}
	public Integer getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(Integer idDestino) {
		this.idDestino = idDestino;
	}
}



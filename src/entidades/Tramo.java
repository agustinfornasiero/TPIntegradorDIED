package entidades;

public class Tramo 
{
	public enum EstadoTramo 
	{
		ACTIVO,
		INACTIVO
	}
	
	private Integer id;
	private Double distanciaEnKm;
	private Integer duracionViajeEnMin;
	private Integer cantidadMaximaPasajeros;
	private EstadoTramo estado;
	private Double costo;
	private Estacion origen;
	private Estacion destino;
	
	
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
	public EstadoTramo getEstado() {
		return estado;
	}
	public void setEstado(EstadoTramo estado) {
		this.estado = estado;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public Estacion getOrigen() {
		return origen;
	}
	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}
	public Estacion getDestino() {
		return destino;
	}
	public void setDestino(Estacion destino) {
		this.destino = destino;
	}
}



package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Boleto 
{
	private Integer id;
	private String correoElectronicoCliente;
	private String nombreCliente;
	private LocalDate fechaVenta;
	private String nombreEstacionOrigen;
	private String nombreEstacionDestino;
	private Double costo;
	private List<String> camino;
	
	public Boleto() 
	{
		camino = new ArrayList<String>();
	}
	
	public Boleto
	(
		String correoElectronicoCliente, String nombreCliente, LocalDate fechaVenta,
		String nombreEstacionOrigen, String nombreEstacionDestino, Double costo, List<String> camino
	) 
	{
		// El id lo asigna la DB
		
		this.correoElectronicoCliente = correoElectronicoCliente;
		this.nombreCliente = nombreCliente;
		this.fechaVenta = fechaVenta;
		this.nombreEstacionOrigen = nombreEstacionOrigen;
		this.nombreEstacionDestino = nombreEstacionDestino;
		this.costo = costo;
		this.camino = camino;
	}
	
	public String toString()
	{
		return "[" + id + ", " + correoElectronicoCliente + ", " + nombreCliente + ", " + fechaVenta.toString()	+ ", " +
				nombreEstacionOrigen + ", " + nombreEstacionDestino + ", " + camino.toString() + "]"; 
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCorreoElectronicoCliente() {
		return correoElectronicoCliente;
	}
	public void setCorreoElectronicoCliente(String correoElectronicoCliente) {
		this.correoElectronicoCliente = correoElectronicoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public String getNombreEstacionOrigen() {
		return nombreEstacionOrigen;
	}
	public void setNombreEstacionOrigen(String nombreEstacionOrigen) {
		this.nombreEstacionOrigen = nombreEstacionOrigen;
	}
	public String getNombreEstacionDestino() {
		return nombreEstacionDestino;
	}
	public void setNombreEstacionDestino(String nombreEstacionDestino) {
		this.nombreEstacionDestino = nombreEstacionDestino;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public List<String> getCamino() {
		return camino;
	}
	public void setCamino(List<String> camino) {
		this.camino = camino;
	}
	
	public void addEstacionEnCamino(String nombreEstacion) {
		camino.add(nombreEstacion);
	}
	public void removeEstacionEnCamino(String nombreEstacion) {
		camino.remove(nombreEstacion);
	}
}

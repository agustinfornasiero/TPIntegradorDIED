package entidades;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entidades.Estacion.Estado;

public class LineaDeTransporte 
{
	public enum Estado
	{
		ACTIVA,
		INACTIVA
	}
	
	private Integer id;
	private String nombre;
	private String color;
	private Estado estado;
	private List<Integer> idsEstaciones;
	
	public LineaDeTransporte() 
	{
		idsEstaciones = new ArrayList<Integer>();
	}
	
	public LineaDeTransporte(String nombre, String color, Estado estado) 
	{
		// El id lo asigna la DB
		
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		
		idsEstaciones = new ArrayList<Integer>();
	}
	
	public String toString()
	{
		return "[" + id + ", " + nombre + ", " + color + ", " + 
				((estado == Estado.ACTIVA)? "ACTIVA" : "INACTIVA") + ", " + idsEstaciones.toString() + "]";
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Integer> getIdsEstaciones() {
		return idsEstaciones;
	}
	public void setIdsEstaciones(List<Integer> idsEstaciones) {
		this.idsEstaciones = idsEstaciones;
	}
	
	// Solo para mantener consistente este objeto sin ir a la DB: 
	public void addEstacion(Integer idEstacion) {
		idsEstaciones.add(idEstacion);
	}
	public void removeEstacion(Integer idEstacion) {
		idsEstaciones.remove(idEstacion);
	}
}

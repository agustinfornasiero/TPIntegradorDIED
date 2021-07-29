package grafo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;

import baseDeDatos.BoletoDB;
import baseDeDatos.EstacionDB;
import baseDeDatos.LineaDeTransporteDB;
import baseDeDatos.TareaDeMantenimientoDB;
import baseDeDatos.TramoDB;
import entidades.Boleto;
import entidades.Estacion;
import entidades.LineaDeTransporte;
import entidades.TareaDeMantenimiento;
import entidades.Tramo;

import org.apache.commons.collections.BinaryHeap;

// https://www.youtube.com/watch?v=XB4MIexjvY0
// https://stackoverflow.com/questions/28998597/how-to-save-shortest-path-in-dijkstra-algorithm
// https://stackoverflow.com/questions/1074781/double-in-hashmap
// https://stackoverflow.com/questions/62056713/error-the-package-org-apache-commons-is-not-accessible
// https://commons.apache.org/proper/commons-collections/javadocs/api-3.2.2/org/apache/commons/collections/BinaryHeap.html


public class RedDeTransporte             // i.e. un digrafo
{
	private List<Estacion> estaciones;   // Nodos
	private List<Tramo> tramos;		     // Aristas
	private List<LineaDeTransporte> lineasDeTransporte;
	private List<TareaDeMantenimiento> tareasDeMantenimiento;
	private List<Boleto> boletos;
	
	private EstacionDB estacionDB;
	private TramoDB tramoDB;
	private LineaDeTransporteDB lineaDeTransporteDB;
	private TareaDeMantenimientoDB tareaDeMantenimientoDB;
	private BoletoDB boletoDB;
	
	public RedDeTransporte() throws ClassNotFoundException, SQLException
	{
		estacionDB = new EstacionDB();
		tramoDB = new TramoDB();
		lineaDeTransporteDB = new LineaDeTransporteDB();
		tareaDeMantenimientoDB = new TareaDeMantenimientoDB();
		boletoDB = new BoletoDB();
		
		estaciones = estacionDB.getAllEstaciones();
		tramos = tramoDB.getAllTramos();
		lineasDeTransporte = lineaDeTransporteDB.getAllLineasDeTransporte();
		tareasDeMantenimiento = tareaDeMantenimientoDB.getAllTareasDeMantenimiento();
		boletos = boletoDB.getAllBoletos();
	}
	
	public void addEstacion(Estacion estacion) throws SQLException 
	{
		estaciones.add(estacion);
		estacionDB.createEstacion(estacion);
	}
	public void updateEstacion(Estacion estacion) throws ClassNotFoundException, SQLException
	{
		estacionDB.updateEstacion(estacion);
	}
	public void deleteEstacion(Estacion estacion) throws ClassNotFoundException, SQLException
	{
		estaciones.remove(estacion);
		estacionDB.deleteEstacion(estacion.getId());
	}
	
	public void addTramo(Tramo tramo) throws SQLException 
	{
		tramos.add(tramo);
		tramoDB.createTramo(tramo);
	}
	public void updateTramo(Tramo tramo) throws ClassNotFoundException, SQLException
	{
		tramoDB.updateTramo(tramo);
	}
	public void deleteTramo(Tramo tramo) throws ClassNotFoundException, SQLException
	{
		tramos.remove(tramo);
		tramoDB.deleteTramo(tramo.getId());
	}
	
	public void addLineaDeTransporte(LineaDeTransporte lineaDeTransporte) throws SQLException, ClassNotFoundException 
	{
		lineasDeTransporte.add(lineaDeTransporte);
		lineaDeTransporteDB.createLineaDeTransporte(lineaDeTransporte);
	}
	public void updateLineaDeTransporte(LineaDeTransporte lineaDeTransporte) throws ClassNotFoundException, SQLException
	{
		lineaDeTransporteDB.updateLineaDeTransporte(lineaDeTransporte);
	}
	public void deleteLineaDeTransporte(LineaDeTransporte lineaDeTransporte) throws ClassNotFoundException, SQLException
	{
		lineasDeTransporte.remove(lineaDeTransporte);
		lineaDeTransporteDB.deleteLineaDeTransporte(lineaDeTransporte.getId());
	}
	
	public void addTareaDeMantenimiento(TareaDeMantenimiento tareaDeMantenimiento, Estacion estacion) throws SQLException, ClassNotFoundException 
	{
		tareasDeMantenimiento.add(tareaDeMantenimiento);
		tareaDeMantenimientoDB.createTareaDeMantenimiento(tareaDeMantenimiento, estacion.getId());
	}
	public void updateTareaDeMantenimiento(TareaDeMantenimiento tareaDeMantenimiento, Estacion estacion) throws ClassNotFoundException, SQLException
	{
		tareaDeMantenimientoDB.updateTareaDeMantenimiento(tareaDeMantenimiento, estacion.getId());
	}
	public void deleteTareaDeMantenimiento(TareaDeMantenimiento tareaDeMantenimiento) throws ClassNotFoundException, SQLException
	{
		tareasDeMantenimiento.remove(tareaDeMantenimiento);
		tareaDeMantenimientoDB.deleteTareaDeMantenimiento(tareaDeMantenimiento.getId());
	}
	
	public void addBoleto(Boleto boleto) throws SQLException, ClassNotFoundException 
	{
		boletos.add(boleto);
		boletoDB.createBoleto(boleto);
	}
	public void updateBoleto(Boleto boleto) throws ClassNotFoundException, SQLException
	{
		boletoDB.updateBoleto(boleto);
	}
	public void deleteBoleto(Boleto boleto) throws ClassNotFoundException, SQLException
	{
		boletos.remove(boleto);
		boletoDB.deleteBoleto(boleto.getId());
	}
	
	// Metodos necesarios para las consultas en la interfaz grafica. 
	// *NO* agregar o quitar elementos a las listas directamente. Usar add...() o remove...(). 
	// Luego de hacer algun/os set...() sobre un objeto, usar update...()
	public List<Estacion> getEstaciones() 							{ return estaciones; 			}
	public List<Tramo> getTramos() 		 						 	{ return tramos; 				}
	public List<TareaDeMantenimiento> getTareasDeMantenimiento() 	{ return tareasDeMantenimiento; }
	public List<LineaDeTransporte> getLineasDeTransporte() 			{ return lineasDeTransporte; 	}
	public List<Boleto> getBoletos() 								{ return boletos; 				}
	
	/*
	// No necesarios:
	public void setEstaciones(List<Estacion> estaciones) 									{ this.estaciones = estaciones; 						}
	public void setTramos(List<Tramo> tramos) 												{ this.tramos = tramos; 								}
	public void setTareasDeMantenimiento(List<TareaDeMantenimiento> tareasDeMantenimiento) 	{ this.tareasDeMantenimiento = tareasDeMantenimiento; 	}
	public void setLineasDeTransporte(List<LineaDeTransporte> lineasDeTransporte) 			{ this.lineasDeTransporte = lineasDeTransporte; 		}
	public void setBoletos(List<Boleto> boletos)	 										{ this.boletos = boletos; 								}
	*/
	
	// -----------------------------------------------------------------------------------------------------------------------------------------------------
	
	private Estacion getEstacion(Integer idEstacion)
	{
		for (Estacion e: estaciones)
			if (e.getId().equals(idEstacion))
				return e;
		return null;
	}
	
	private List<Estacion> getEstacionesAdyacentes(Estacion estacion)
	{
		List<Estacion> estacionesAdyacentes = new ArrayList<Estacion>();
		Integer idEstacion;
		
		for(Tramo t : tramos)
		{
			idEstacion = t.getIdOrigen();
			
			if 	(
					idEstacion.equals(estacion.getId()) && 
					estacion.getEstado() == Estacion.Estado.OPERATIVA
				)
				estacionesAdyacentes.add(this.getEstacion(idEstacion));
		}
			
		return estacionesAdyacentes;
	}
	private List<Tramo> getTramosEntre(Estacion estacionOrigen, Estacion estacionDestino)
	{
		List<Tramo> tramosEntreEstaciones = new ArrayList<Tramo>();
		
		for (Tramo t : tramos)
			if	(
					t.getIdOrigen().equals(estacionOrigen.getId()) 		&& 
					t.getIdDestino().equals(estacionDestino.getId()) 	&&
					t.getEstado() == Tramo.Estado.ACTIVO
				)
				tramosEntreEstaciones.add(t);
		
		return tramosEntreEstaciones;
	}
	
	
	
	public List<Tramo> caminoMasRapido(Estacion estacionOrigen, Estacion estacionDestino) 
	{
		return null;
		//return this.Dijkstra(estacionOrigen, estacionDestino, );
	}
	public List<Tramo> caminoMasCorto(Estacion estacionOrigen, Estacion estacionDestino) 
	{
		return null;
		//return this.Dijkstra(estacionOrigen, estacionDestino, );
	}
	public List<Tramo> caminoMasBarato(Estacion estacionOrigen, Estacion estacionDestino) 
	{
		return null;
		//return this.Dijkstra(estacionOrigen, estacionDestino, );
	}
	
	// Implemento camino mas rapido por ahora
	@SuppressWarnings("unchecked")
	private Map<Estacion, Dupla<Integer, ArrayList<Tramo>>> Dijkstra(Estacion estacionOrigen) //Estacion estacionDestino, Predicate<Tramo> criterio)
	{
		Dupla<Integer, ArrayList<Tramo>> auxDupla1;
		Map<Estacion, Dupla<Integer, ArrayList<Tramo>>> costoYCaminoHastaCadaEstacion 
    		= new HashMap<Estacion, Dupla<Integer, ArrayList<Tramo>>>();  // Cuidado: falta definir equals() y hashCode()
    	
		// Inicializar las distancias a "infinito"
    	for (Estacion e : estaciones)
    	{
    		auxDupla1 = new Dupla<Integer, ArrayList<Tramo>>(Integer.MAX_VALUE, new ArrayList<Tramo>());
    		costoYCaminoHastaCadaEstacion.put(e, auxDupla1);
    	}
    	auxDupla1 = new Dupla<Integer, ArrayList<Tramo>>(0, new ArrayList<Tramo>());
    	costoYCaminoHastaCadaEstacion.put(estacionOrigen, auxDupla1);
    	
    	// Visitados y pendientes
    	BinaryHeap _estacionesVisitadas;
    	
    	
    	Set<Estacion> estacionesVisitadas = new HashSet<Estacion>();
    	TreeMap<Integer, Dupla<Estacion, ArrayList<Tramo>>> estacionesAVisitar 
    		= new TreeMap<Integer, Dupla<Estacion, ArrayList<Tramo>>>();         // Pseudo-monticulo

    	Dupla<Estacion, ArrayList<Tramo>> auxDupla2 
    		= new Dupla<Estacion, ArrayList<Tramo>>(estacionOrigen, new ArrayList<Tramo>());
    	estacionesAVisitar.put(0, auxDupla2);
    	
    	// Iterar mientras haya estaciones pendientes
    	Entry<Integer, Dupla<Estacion, ArrayList<Tramo>>> estacion;
    	ArrayList<Tramo> auxCamino;
    	Tramo tramoMenosCostoso;
    	Integer costoActualHastaEstacionAdyacente;
    	Integer costoDeEstacionActualAAdyacente;
    	Integer costoHastaEstacionActual;
    	Integer nuevoCostoTotal;
    	while(!estacionesAVisitar.isEmpty())
    	{
    		estacion = estacionesAVisitar.pollFirstEntry();  // Sacar el minimo (costo)
    		estacionesVisitadas.add(estacion.getValue().primero);
    		
    		for (Estacion estacionAdyacente : this.getEstacionesAdyacentes(estacion.getValue().primero))
    		{
    			if (!estacionesVisitadas.contains(estacionAdyacente))
    			{
    				tramoMenosCostoso 
    					= this.tramoDeMenorCosto(this.getTramosEntre(estacion.getValue().primero, estacionAdyacente));
    				
    			    costoDeEstacionActualAAdyacente = tramoMenosCostoso.getDuracionViajeEnMin();
    			    costoHastaEstacionActual = estacion.getKey();
    				costoActualHastaEstacionAdyacente = costoYCaminoHastaCadaEstacion.get(estacionAdyacente).primero;
    				nuevoCostoTotal = costoHastaEstacionActual + costoDeEstacionActualAAdyacente;
    				
    				// Si se encuentra un nuevo camino menos costoso, cambiar el "resultado final" (costoYCaminoHastaCadaEstacion)
    				if (nuevoCostoTotal < costoActualHastaEstacionAdyacente)
    				{
    					// Por si acaso se hacen copias de los caminos xD
    					
    					// Actualizar costo y camino hasta nodoAdyacente
    					auxCamino = (ArrayList<Tramo>) estacion.getValue().segundo.clone();
    					auxCamino.add(tramoMenosCostoso);
    					
    					auxDupla1 = new Dupla<Integer, ArrayList<Tramo>>(nuevoCostoTotal, auxCamino);
    					
    					costoYCaminoHastaCadaEstacion.put(estacionAdyacente, auxDupla1);
    					
    					// Agregar estacion adyacente a la lista de pendientes
    					auxCamino = (ArrayList<Tramo>) auxCamino.clone();
    					
    					auxDupla2 = new Dupla<Estacion, ArrayList<Tramo>>(estacionAdyacente, auxCamino);
    			
    					estacionesAVisitar.put(nuevoCostoTotal, auxDupla2);  					
    				}
    			}
    		}
    	}
    		
    	return costoYCaminoHastaCadaEstacion;
	}
	
	private Tramo tramoDeMenorCosto (List<Tramo> tramos)
	{
		Tramo tramoDeMenorCosto = null;
		Integer costo = Integer.MAX_VALUE;
		
		for (Tramo t : tramos)
			if(t.getDuracionViajeEnMin() < costo)
			{
				tramoDeMenorCosto = t;
				costo = t.getDuracionViajeEnMin();
			}
		
		return tramoDeMenorCosto;
	}
	
	
	
}
	
	


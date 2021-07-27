package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import entidades.Estacion;

// https://jdbc.postgresql.org/documentation/head/8-date-time.html

public class EstacionDB extends EntidadDB
{
	public EstacionDB() throws ClassNotFoundException, SQLException { super(); }
	
	public void createEstacion(Estacion estacion) throws SQLException 
	{
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO tp_died.estacion (id, nombre, hora_apertura, hora_cierre, estado) " +
				"VALUES (NEXTVAL('tp_died.estacion_seq'), ?, ?, ?, ?);"
		);
				
		completarDatosBasicosEstacion(ps, estacion);
		// Los mantenimientos realizados se ignoran porque deben tratarse aparte con la clase TareaDeMantenimientoDB
		// (el dato de la estación a la que se le hace mantenimiento esta en la tabla tarea_de_mantenimiento)
		ps.executeUpdate();
		
		ps.close();
	}
	
	public void updateEstacion(Estacion estacion) throws SQLException, ClassNotFoundException 
	{
		PreparedStatement ps = c.prepareStatement(
			"UPDATE tp_died.estacion " +
			"SET nombre = ?, hora_apertura = ?, hora_cierre = ?, estado = ? " +
			"WHERE id = ?;"
		);
		
		completarDatosBasicosEstacion(ps, estacion);
		// Los mantenimientos realizados se ignoran porque deben tratarse aparte con la clase TareaDeMantenimientoDB
		// (el dato de la estación a la que se le hace mantenimiento esta en la tabla tarea_de_mantenimiento)		
		ps.setInt(5, estacion.getId());
		ps.executeUpdate();
		
		ps.close();
	}

	public void deleteEstacion(Integer idEstacion) throws ClassNotFoundException, SQLException 
	{
		PreparedStatement ps1 = c.prepareStatement("DELETE FROM tp_died.estacion WHERE id = ?;");
		PreparedStatement ps2 = c.prepareStatement("DELETE FROM tp_died.tarea_de_mantenimiento WHERE id_estacion = ?;");
		
		ps1.setInt(1, idEstacion);
		ps2.setInt(1, idEstacion);
		ps1.executeUpdate();
		ps2.executeUpdate();
		
		ps1.close();
		ps2.close();
	}
	
	public Estacion getEstacion(Integer idEstacion) throws SQLException, ClassNotFoundException
	{
		Estacion estacion = null;
		
		TareaDeMantenimientoDB tareaDB = new TareaDeMantenimientoDB();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.estacion WHERE id = ?;");		
		ResultSet rs; 
		
		ps.setInt(1, idEstacion);
		rs = ps.executeQuery();
		estacion = recuperarEstacion(rs, tareaDB);
		
		tareaDB.close();
		rs.close();
		ps.close();
		
		return estacion;
	}

	public List<Estacion> getAllEstaciones() throws SQLException, ClassNotFoundException 
	{
		List<Estacion> estaciones = new ArrayList<Estacion>();
		
		TareaDeMantenimientoDB tareaDB = new TareaDeMantenimientoDB();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.estacion ORDER BY id;");		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
			estaciones.add(recuperarEstacion(rs, tareaDB));
		
		tareaDB.close();
		rs.close();
		ps.close();
		
		return estaciones;
	}

	private void completarDatosBasicosEstacion(PreparedStatement ps, Estacion estacion) throws SQLException
	{
		ps.setString(1, estacion.getNombre());
		ps.setObject(2, estacion.getHoraApertura());
		ps.setObject(3, estacion.getHoraCierre());
		if (estacion.getEstado() == Estacion.Estado.OPERATIVA)
			ps.setString(4, "OPERATIVA");
		else if (estacion.getEstado() == Estacion.Estado.EN_MANTENIMIENTO)
			ps.setString(4, "EN_MANTENIMIENTO");
	}
	
	private Estacion recuperarEstacion(ResultSet rs, TareaDeMantenimientoDB tareaDB) throws SQLException, ClassNotFoundException
	{
		Estacion estacionAux = new Estacion();
		String estadoEstacion;
		
		estacionAux.setId(rs.getInt("id"));
		estacionAux.setNombre(rs.getString("nombre"));
		estacionAux.setHoraApertura(rs.getObject("hora_apertura", LocalTime.class));
		estacionAux.setHoraCierre(rs.getObject("hora_cierre", LocalTime.class));
		
		estadoEstacion = rs.getString("estado");
		if(estadoEstacion.equals("OPERATIVA"))
			estacionAux.setEstado(Estacion.Estado.OPERATIVA);
		else if (estadoEstacion.equals("EN_MANTENIMIENTO"))
			estacionAux.setEstado(Estacion.Estado.EN_MANTENIMIENTO);
		
		estacionAux.setIdsMantenimientosRealizados(tareaDB.getAllTareasDeMantenimiento(rs.getInt("id")));
		
		return estacionAux;
	}
}

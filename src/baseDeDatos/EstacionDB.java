package baseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import entidades.Estacion;
import entidades.Estacion.Estado;

// https://jdbc.postgresql.org/documentation/head/8-date-time.html

public class EstacionDB
{
	private EstacionDB() { }
	
	public static void createEstacion(Estacion est) throws SQLException, ClassNotFoundException {
		Connection c = Conexion.establecer();
		PreparedStatement ps = c.prepareStatement(
			"INSERT INTO tp_died.estacion (id, nombre, hora_apertura, hora_cierre, estado) " +
			"VALUES (NEXTVAL('tp_died.estacion_seq'), ?, ?, ?, ?);"
		);
		
		ps.setString(1, est.getNombre());
		ps.setObject(2, est.getHoraApertura());
		ps.setObject(3, est.getHoraCierre());
		if (est.getEstado() == Estacion.Estado.OPERATIVA)
			ps.setString(4, "OPERATIVA");
		else if (est.getEstado() == Estacion.Estado.EN_MANTENIMIENTO)
			ps.setString(4, "EN_MANTENIMIENTO");
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public static void deleteEstacion(Integer id) throws ClassNotFoundException, SQLException 
	{
		Connection c = Conexion.establecer();
		PreparedStatement ps = c.prepareStatement("DELETE FROM tp_died.estacion WHERE id = " + id + ";");
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public static Estacion getEstacion(Integer id) throws SQLException, ClassNotFoundException
	{
		Estacion estacion = null;
		
		Connection c = Conexion.establecer();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.estacion WHERE id = ?;");		
		ResultSet rs; 
		
		ps.setInt(1, id);
		
		rs = ps.executeQuery();
		
		while(rs.next()) // No parece funcionar sin el while
			estacion = recuperarEstacion(rs);
		
		rs.close();
		ps.close();
		c.close();
		
		return estacion;
	}

	public static List<Estacion> getAllEstaciones() throws SQLException, ClassNotFoundException 
	{
		List<Estacion> estaciones = new ArrayList<Estacion>();
		
		Connection c = Conexion.establecer();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.estacion ORDER BY id;");		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			estaciones.add(recuperarEstacion(rs));
			rs.next();
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return estaciones;
	}

	private static Estacion recuperarEstacion(ResultSet rs) throws SQLException
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
		
		// Faltan los mantenimientos
		
		return estacionAux;
	}
	
	public static void updateEstacion(Estacion est) throws SQLException, ClassNotFoundException 
	{
		Connection c = Conexion.establecer();
		PreparedStatement ps = c.prepareStatement(
			"UPDATE tp_died.estacion " +
			"SET nombre = ?, hora_apertura = ?, hora_cierre = ?, estado = ? " +
			"WHERE id = ?;"
		);
		
		ps.setString(1, est.getNombre());
		ps.setObject(2, est.getHoraApertura());
		ps.setObject(3, est.getHoraCierre());
		if (est.getEstado() == Estacion.Estado.OPERATIVA)
			ps.setString(4, "OPERATIVA");
		else if (est.getEstado() == Estacion.Estado.EN_MANTENIMIENTO)
			ps.setString(4, "EN_MANTENIMIENTO");
		
		ps.setInt(5, est.getId());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
}

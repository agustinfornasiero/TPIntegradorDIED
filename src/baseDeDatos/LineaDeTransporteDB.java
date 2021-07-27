package baseDeDatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.LineaDeTransporte;

public class LineaDeTransporteDB extends EntidadDB
{
	public LineaDeTransporteDB() throws ClassNotFoundException, SQLException { super(); }
	
	public void createLineaDeTransporte(LineaDeTransporte linea) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = c.prepareStatement(
			"INSERT INTO tp_died.linea_de_transporte (id, nombre, color, estado) " +
			"VALUES (NEXTVAL('tp_died.linea_de_transporte_seq'), ?, ?, ?);"
		);
				
		completarDatosBasicosLineaDeTransporte(ps, linea);
		updateEstacionesLineaDeTransporte(linea);
		ps.executeUpdate();
		
		ps.close();
	}
	
	public void updateLineaDeTransporte(LineaDeTransporte linea) throws SQLException, ClassNotFoundException 
	{
		PreparedStatement ps = c.prepareStatement(
			"UPDATE tp_died.linea_de_transporte " +
			"SET nombre = ?, color = ?, estado = ? " +
			"WHERE id = ?;"
		);
		
		completarDatosBasicosLineaDeTransporte(ps, linea);
		updateEstacionesLineaDeTransporte(linea);
		ps.setInt(4, linea.getId());
		ps.executeUpdate();
		
		ps.close();
	}
	
	public void deleteLineaDeTransporte(Integer idLineaDeTransporte) throws ClassNotFoundException, SQLException 
	{
		PreparedStatement ps1 = c.prepareStatement("DELETE FROM tp_died.linea_de_transporte WHERE id = ?;");
		PreparedStatement ps2 = c.prepareStatement("DELETE FROM tp_died.estaciones_linea WHERE id_linea_de_transporte = ?;");
		
		ps1.setInt(1, idLineaDeTransporte);
		ps2.setInt(2, idLineaDeTransporte);
		
		ps1.executeUpdate();
		ps2.executeUpdate();
		
		ps1.close();
		ps2.close();
	}
	
	public LineaDeTransporte getLineaDeTransporte(Integer idLineaDeTransporte) throws SQLException, ClassNotFoundException
	{
		LineaDeTransporte linea = null;
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.linea_de_transporte WHERE id = ?;");		
		ResultSet rs; 
		
		ps.setInt(1, idLineaDeTransporte);
		rs = ps.executeQuery();
		linea = recuperarLineaDeTransporte(rs);
		
		rs.close();
		ps.close();
		
		return linea;
	}

	public List<LineaDeTransporte> getAllLineasDeTransporte() throws SQLException, ClassNotFoundException 
	{
		List<LineaDeTransporte> lineas = new ArrayList<LineaDeTransporte>();
		
		PreparedStatement ps = c.prepareStatement("SELECT * FROM tp_died.linea_de_transporte ORDER BY id;");		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
			lineas.add(recuperarLineaDeTransporte(rs));
		
		rs.close();
		ps.close();
		
		return lineas;
	}
	
	private void completarDatosBasicosLineaDeTransporte(PreparedStatement ps, LineaDeTransporte linea) throws SQLException
	{
		ps.setString(1, linea.getNombre());
		ps.setString(2, linea.getColor());
		if (linea.getEstado() == LineaDeTransporte.Estado.ACTIVA)
			ps.setString(3, "ACTIVA");
		else if (linea.getEstado() == LineaDeTransporte.Estado.INACTIVA)
			ps.setString(3, "INACTIVA");	
	}
	
	private void updateEstacionesLineaDeTransporte(LineaDeTransporte linea) throws SQLException, ClassNotFoundException
	{
		List<Integer> idsEstacionesLineaEnDB = getEstacionesLineaDeTransporte(linea.getId());
		
		for (Integer e: idsEstacionesLineaEnDB)
			if (!linea.getIdsEstaciones().contains(e))
				deleteEstacionLineaDeTransporte(e, linea.getId());
		
		for (Integer e: linea.getIdsEstaciones())
			if (!idsEstacionesLineaEnDB.contains(e))
				createEstacionLineaDeTransporte(e, linea.getId());
	}
	
	private void deleteEstacionLineaDeTransporte(Integer idEstacion, Integer idLinea) throws SQLException 
	{
		PreparedStatement ps = c.prepareStatement(
			"DELETE FROM tp_died.estaciones_linea WHERE id_estacion = ? AND id_linea_de_transporte = ?;"
		);
		
		ps.setInt(1, idEstacion);
		ps.setInt(2, idLinea);
		ps.executeUpdate();
		
		ps.close();
	}
	
	private void createEstacionLineaDeTransporte(Integer idEstacion, Integer idLinea) throws SQLException
	{
		PreparedStatement ps = c.prepareStatement(
			"INSERT INTO tp_died.estaciones_linea (id_estacion, id_linea_de_transporte) VALUES (?, ?);"
		);
		
		ps.setInt(1, idEstacion);
		ps.setInt(2, idLinea);
		ps.executeUpdate();
		
		ps.close();
	}

	private List<Integer> getEstacionesLineaDeTransporte(Integer idLinea) throws SQLException, ClassNotFoundException
	{
		List<Integer> idEstaciones = new ArrayList<Integer>();
		
		PreparedStatement ps = c.prepareStatement(
			"SELECT id_estacion" +
			"FROM tp_died.estaciones_linea " +
			"WHERE id_linea_de_transporte = ?;"
		);
		ResultSet rs;
		
		ps.setInt(0, idLinea);
		rs = ps.executeQuery();
		while(rs.next())
			idEstaciones.add(rs.getInt("id_estacion"));
		
		rs.close();
		ps.close();
		
		return idEstaciones;
	}

	private LineaDeTransporte recuperarLineaDeTransporte(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		LineaDeTransporte lineaDeTransporteAux = new LineaDeTransporte();
		String estadoLineaDeTransporte;
		
		lineaDeTransporteAux.setId(rs.getInt("id"));
		lineaDeTransporteAux.setNombre(rs.getString("nombre"));
		lineaDeTransporteAux.setColor(rs.getString("color"));
				
		estadoLineaDeTransporte = rs.getString("estado");
		if(estadoLineaDeTransporte.equals("ACTIVA"))
			lineaDeTransporteAux.setEstado(LineaDeTransporte.Estado.ACTIVA);
		else if (estadoLineaDeTransporte.equals("INACTIVA"))
			lineaDeTransporteAux.setEstado(LineaDeTransporte.Estado.INACTIVA);
		
		lineaDeTransporteAux.setIdsEstaciones(getEstacionesLineaDeTransporte(rs.getInt("id")));
		
		return lineaDeTransporteAux;
	}
}

package baseDeDatos;

public final class Conexion 
{
	private final String url = "jdbc::/postgresql://127.0.0.1:5432//postgres";
	private final String usuario = "postgres";
	private final String contrasenia = "postgres";
	
	private Conexion() {}
	
	public static void establecerConexion()
	{
		Connection conexion = null;
		
		Class.forName("org.postgresql.Driver");
		conexion = DriverManager.getConnection(url, usuario, contrasenia);
		
	}
	
}

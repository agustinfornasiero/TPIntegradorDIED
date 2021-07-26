package baseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// https://stackoverflow.com/questions/30964016/how-to-resolve-no-suitable-driver-found-error
// https://stackoverflow.com/questions/33350971/no-suitable-driver-for-postgres-even-though-class-forname-works
// https://www.youtube.com/watch?v=C4EUAfDg4Hc

public final class Conexion 
{
	private static final String url = "jdbc:postgresql://localhost:5432/postgres";
	private static final String user = "postgres";
	private static final String pass = "postgres";
	
	private Conexion() throws ClassNotFoundException { }
	
	public static Connection establecer() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(url, user, pass);
	}
}

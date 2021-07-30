package app;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import grafo.RedDeTransporte;
import interfazGrafica.MenuPrincipal;
import tests.TestDB;

public final class App 
{
	public static void main (String[] args)
	{
		//TestDB.testear();
		RedDeTransporte redDeTransporte = null;
		try { redDeTransporte = new RedDeTransporte(); } 
		catch (ClassNotFoundException | SQLException e) { System.exit(137); }
		
		JFrame ventana = new JFrame();
		ventana.setTitle("TP integrador - DIED - UTN FRSF"); 
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setContentPane(new MenuPrincipal(ventana, redDeTransporte));
		ventana.pack();
		ventana.setVisible(true);
		
		try { redDeTransporte.close(); }
		catch (SQLException e) { e.printStackTrace(); }
	}
}

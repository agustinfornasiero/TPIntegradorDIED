package app;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import baseDeDatos.EstacionDB;
import entidades.Estacion; // borrar despues

import interfazGrafica.MenuPrincipal;

public final class App 
{
	public static void main (String[] args)
	{
		Estacion est1 = new Estacion("Estacion A", LocalTime.now(), LocalTime.now().plusHours(8), Estacion.Estado.OPERATIVA);
		Estacion est2 = new Estacion("Estacion B", LocalTime.now(), LocalTime.now().plusHours(8), Estacion.Estado.EN_MANTENIMIENTO);
		List<Estacion> l;
		
		try 
		{
			EstacionDB.createEstacion(est1);
			EstacionDB.createEstacion(est2);
			
			//est1 = EstacionDB.getEstacion(1);
			//est1.setNombre("Estacion A yay!");
			//EstacionDB.updateEstacion(est1);
			
			l = EstacionDB.getAllEstaciones();
			
			EstacionDB.deleteEstacion(1);
			
			l = EstacionDB.getAllEstaciones();
			
			
			System.out.println("yay");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		/*
		JFrame ventana = new JFrame();
		
		ventana.setTitle("TP integrador - DIED - UTN FRSF"); 
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setContentPane(new MenuPrincipal(ventana));
		
		ventana.pack();
		ventana.setVisible(true);
		*/
	}
}

package app;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import interfazGrafica.MenuPrincipal;

public final class App 
{
	public static void main (String[] args)
	{
		JFrame ventana = new JFrame();
		
		ventana.setTitle("TP integrador - DIED - UTN FRSF"); 
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setContentPane(new MenuPrincipal(ventana));
		
		ventana.pack();
		ventana.setVisible(true);
	}
}

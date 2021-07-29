package app;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import interfazGrafica.MenuPrincipal;
import tests.TestDB;

public final class App 
{
	public static void main (String[] args)
	{
		
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		
		Double A = (double) a;
		Double B = (double) b;
		Double C = (double) c;
		
		System.out.println(new BigDecimal(A));
		System.out.println(new BigDecimal(B));
		System.out.println(new BigDecimal(C));
		System.out.println(new BigDecimal(A + B + C));
		
		
		//TestDB.testear();
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

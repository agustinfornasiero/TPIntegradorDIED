package interfazGrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuEstaciones extends JPanel
{
	private GridBagConstraints gbc;
	private JButton btn1, btn2, btn3, btn4;
	private JFrame ventana;
	private JPanel padre;
	
	public MenuEstaciones(JFrame ventana, JPanel padre)
	{
		this.ventana = ventana;
		this.padre = padre;
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	private void armarPanel() 
	{
		btn1 = new JButton("Agregar estacion");
		btn2 = new JButton("Consultar y/o modificar estaciones");
		btn3 = new JButton("Eliminar estacion");
		btn4 = new JButton("Volver");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 15;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 20, 5, 20);
		this.add(btn1, gbc);
		btn1.addActionListener(
			e -> { 
					ventana.setContentPane(new MenuAgregarEstacion(ventana, this));
					ventana.pack();
					ventana.setVisible(true);	
				 } 
		);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 15;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 5, 20);
		this.add(btn2, gbc);
		btn2.addActionListener(
			e -> { 
					ventana.setContentPane(new MenuConsultarYModificarEstaciones(ventana, this));
					ventana.pack();
					ventana.setVisible(true);	
				 }		
		); 
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipady = 15;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 5, 20);
		this.add(btn3, gbc);
		btn3.addActionListener(
			e -> { 
					ventana.setContentPane(new MenuEliminarEstacion(ventana, this));
					ventana.pack();
					ventana.setVisible(true);	
			 	 }
		); 
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.ipady = 15;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(30, 20, 10, 20);
		this.add(btn4, gbc);
		btn4.addActionListener(
			e -> {
					ventana.setContentPane(padre);
					ventana.pack();
					ventana.setVisible(true);
				 } 
		);
	}
}


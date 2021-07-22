package interfazGrafica;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfazGrafica.estacion.MenuEstaciones;
import interfazGrafica.lineaDeTransporte.MenuLineasDeTransporte;

@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel
{
	private GridBagConstraints gbc;
	private JButton btn1, btn2, btn3;
	private JLabel lbl1;
	private JFrame ventana;
	
	public MenuPrincipal(JFrame ventana)
	{
		this.ventana = ventana;
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armarPanel();
	}
	
	private void armarPanel() 
	{
		btn1 = new JButton("Estaciones");
		btn2 = new JButton("Lineas de transporte");
		btn3 = new JButton("Venta de boleto");
		
		lbl1 = new JLabel("Sistema de gestion de transporte multimodal");
		lbl1.setFont(new Font("Serif", Font.BOLD, 16));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 20, 30, 20);
		this.add(lbl1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 15;
		gbc.weightx = 1.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 5, 20);
		this.add(btn1, gbc);
		btn1.addActionListener(
			e -> { 
					ventana.setContentPane(new MenuEstaciones(ventana, this));
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
		this.add(btn2, gbc);
		btn2.addActionListener(
			e -> {
					ventana.setContentPane(new MenuLineasDeTransporte(ventana, this));
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
		gbc.insets = new Insets(5, 20, 10, 20);
		this.add(btn3, gbc);
		btn3.addActionListener(e -> {}); // Pendiente
	}
}

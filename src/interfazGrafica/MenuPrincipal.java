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

@SuppressWarnings("serial")
public class MenuPrincipal extends JPanel
{
	private GridBagConstraints gbc;
	private JButton btn1, btn2, btn3;
	private JLabel lbl1, lbl2;
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
		btn2 = new JButton("Líneas de transporte");
		btn3 = new JButton("Venta de boleto");
		
		lbl1 = new JLabel("Sistema de gestión de transporte multimodal");
		lbl1.setFont(new Font("Serif", Font.BOLD, 16));
		lbl2 = new JLabel("___________________________________________");
		lbl2.setFont(new Font("Serif", Font.BOLD, 16));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 20, 0, 20);
		this.add(lbl1, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 20, 10, 20);
		this.add(lbl2, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipady = 15;
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
		gbc.gridy = 3;
		gbc.ipady = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 5, 20);
		this.add(btn2, gbc);
		btn2.addActionListener(e -> {}); // Pendiente
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.ipady = 15;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 20, 10, 20);
		this.add(btn3, gbc);
		btn3.addActionListener(e -> {}); // Pendiente
	}
}
package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Emergente {

	private String mensaje;
	
	public Emergente(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public void mostrar() {
	 JFrame frame = new JFrame("Error");
        frame.setSize(350, 100);
        frame.setLocationRelativeTo(null);
        JLabel label = new JLabel(mensaje);
        frame.add(label);
        frame.setVisible(true);
	}
	
}

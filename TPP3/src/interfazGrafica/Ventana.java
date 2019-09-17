package interfazGrafica;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Ventana extends JFrame {
	private Pantalla pantalla = new Pantalla();

	public Ventana() {
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/Imagenes/IconoCalculadora.png")));
		setTitle("Calculadora");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 385);
		inicializar();
	}

	private void inicializar() {
		add(pantalla, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setVisible(true);
	}

}
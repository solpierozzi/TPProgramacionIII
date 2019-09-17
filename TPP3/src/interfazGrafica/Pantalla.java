package interfazGrafica;

import intermediario.CareTaker;
import intermediario.Estado;
import intermediario.Intermediario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

public class Pantalla extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton[] botones;
	private JTextField resultados;
	private JTextField calculoAnterior;
	private int indice_operando;
	private int indice_decimal;
	private boolean igual;
	private String operacionCopiada;
	private Intermediario intermediario;
	private CareTaker interRepo;
	private Estado estado;
	private int posMemoria;

	public Pantalla() {
		botones = new JButton[22];
		resultados = new JTextField();
		calculoAnterior = new JTextField();
		interRepo = new CareTaker();
		estado = new Estado();
		indice_operando = 0;
		indice_decimal = 0;
		igual = false;
		intermediario = new Intermediario();
		posMemoria = -1;
		setEnabled(false);
		setBorder(null);
		setLayout(null);
		agregarBotones();
		agregarImagenesBotones();
		agregarVista_resultados();
		agregarVista_calculoAnterior();
	}
	
	//Respuestas a eventos sobre los botones
	@Override
	public void actionPerformed(ActionEvent e) {
		int largoAnterior = resultados.getText().length();
		if (!e.getSource().equals(botones[11])) {
			igual = false;
			accionarSegunEvento(e);
			if (resultados.getText().length() > largoAnterior)
				modificarIndices();
		} else {
			if (igual)
				resetValores();
			else {
				try {
					setMemoria();
					calcularResultado();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			igual = true;
		}
	}
	
	//Tipo de respuesta segun el boton
	private void accionarSegunEvento(ActionEvent e) {
		agregarSiEsNumero(e);
		agregarSiEsOperando(e);
		borrarSiSeSolicita(e);
		copiarSiSeSolicita(e);
		pegarSiSeSolicita(e);
		deshacer(e);
		rehacer(e);
	}

	//Si el evento es un numero, lo debe agregar a la pantalla
	private void agregarSiEsNumero(ActionEvent e) {
		if (e.getSource().equals(botones[10]))
			resultados.setText(resultados.getText() + "1");
		if (e.getSource().equals(botones[6]))
			resultados.setText(resultados.getText() + "2");
		if (e.getSource().equals(botones[2]))
			resultados.setText(resultados.getText() + "3");
		if (e.getSource().equals(botones[9]))
			resultados.setText(resultados.getText() + "4");
		if (e.getSource().equals(botones[5]))
			resultados.setText(resultados.getText() + "5");
		if (e.getSource().equals(botones[1]))
			resultados.setText(resultados.getText() + "6");
		if (e.getSource().equals(botones[8]))
			resultados.setText(resultados.getText() + "7");
		if (e.getSource().equals(botones[4]))
			resultados.setText(resultados.getText() + "8");
		if (e.getSource().equals(botones[0]))
			resultados.setText(resultados.getText() + "9");
		if (e.getSource().equals(botones[3]))
			resultados.setText(resultados.getText() + "0");
	}

	//Si el evento es un operando, debe corroborar si es valido ubicarlo y en ese caso ubicarlo
	private void agregarSiEsOperando(ActionEvent e) {
		boolean hayCaracter = !resultados.getText().isEmpty();

		if (e.getSource().equals(botones[12]))
			if (hayCaracter && esNumero(resultados.getText().charAt(resultados.getText().length() - 1)))
				resultados.setText(resultados.getText() + "+");

		if (e.getSource().equals(botones[13]))
			if (puedeUbicarMenos())
				resultados.setText(resultados.getText() + "-");

		if (e.getSource().equals(botones[14]))
			if (hayCaracter && esNumero(resultados.getText().charAt(resultados.getText().length() - 1)))
				resultados.setText(resultados.getText() + "x");

		if (e.getSource().equals(botones[15]))
			if (hayCaracter && esNumero(resultados.getText().charAt(resultados.getText().length() - 1)))
				resultados.setText(resultados.getText() + "/");

		if (e.getSource().equals(botones[7]))
			if (puedeUbicarDecimal())
				resultados.setText(resultados.getText() + ".");

	}

	//Si se presiona borrar un elemento y hay algun elemento, lo elimina. Si el texto es SyntaxError: elimina todo
	//Si se presiona borrar todo, elimina todos los elementos en caso que haya alguno
	private void borrarSiSeSolicita(ActionEvent e) {
		if (e.getSource().equals(botones[16]) && resultados.getText().length() > 0)
			if (resultados.getText().equals("SyntaxError"))
				resetValores();
			else
				resultados.setText(resultados.getText().substring(0,resultados.getText().length() - 1));

		if (e.getSource().equals(botones[17]) && resultados.getText().length() > 0)
			resetValores();

	}
	
	//Copia unicamente numeros
	private void copiarSiSeSolicita(ActionEvent e) {
		if (e.getSource().equals(botones[18]))
			if (sePuedeCopiar(resultados.getText()))
				operacionCopiada = resultados.getText();

	}

	//Pega el texto copiado si es posible ubicarlo respetando la funcion de la calculadora
	private void pegarSiSeSolicita(ActionEvent e) {
		if (e.getSource().equals(botones[19]))
			if (sePuedePegar(resultados.getText()))
				resultados.setText(resultados.getText() + operacionCopiada);
	}
	
	//Si la operacion ingresada es valida, calcula el resultado y lo muestra en pantalla
	private void calcularResultado() throws IOException {
		if (indice_operando != resultados.getText().length() && indice_decimal != resultados.getText().length()) {
			calculoAnterior.setText(resultados.getText());
			intermediario.agregar(resultados.getText());
			try {
				resultados.setText(intermediario.resultadoOperacion());
			} catch (Exception e) {
				resultados.setText("SyntaxError");
			}
		} else
			resultados.setText("SyntaxError");
	}

	//Retorna true si se puede agregar un menos al final de los elementos actuales
	private boolean puedeUbicarMenos() {
		boolean ret = false;
		String textoPantalla = resultados.getText();
		if (textoPantalla.isEmpty())
			ret = true;
		else {
			if (textoPantalla.length() == 1)
				ret = textoPantalla.charAt(0) == '-'|| esNumero(textoPantalla.charAt(textoPantalla.length() - 1));
			else {
				ret = textoPantalla.endsWith("-")|| textoPantalla.endsWith("x")|| textoPantalla.endsWith("/");
				ret = ret && esNumero(textoPantalla.charAt(textoPantalla.length() - 2))|| esNumero(textoPantalla.charAt(textoPantalla.length() - 1));
			}
		}
		return ret;
	}

	//Retorna true si se puede ubicar una coma al final de los elementos actuales
	private boolean puedeUbicarDecimal() {
		String textoPantalla = resultados.getText();
		int desde = indice_operando;
		boolean ret = !textoPantalla.isEmpty() && desde != textoPantalla.length();
		while (desde < textoPantalla.length()) {
			ret = ret && esNumero(textoPantalla.charAt(desde));
			desde++;
		}
		return ret;
	}

	//Retorna true si el ultimo elemento a copiar es un numero
	public boolean sePuedeCopiar(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!esNumero(s.charAt(i))) {
				if (s.charAt(i) != '.')
					return false;
			}
		}
		return true;
	}

	//Retorna true si es posible pegar el numero copiado al final de los elementos actuales
	public boolean sePuedePegar(String s) {
		if (operacionCopiada==null)
			return false;
		if (!operacionCopiada.contains("."))
			return true;
		int puntos = 0;
		int operandos = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!esNumero(s.charAt(i))) {
				if (s.charAt(i) == '.')
					puntos++;
				else 
					operandos++;
			}
		}
		if (operandos == puntos || puntos == 0 && operandos == 0)
			return true;
		return false;
	}

	//Actualiza los indices de operandos y decimal luego de agregar alguno de ellos
	private void modificarIndices() {
		if (!esNumero(resultados.getText().charAt(resultados.getText().length() - 1))) {
			if (resultados.getText().charAt(resultados.getText().length() - 1) != '.')
				indice_operando = resultados.getText().length();
			else
				indice_decimal = resultados.getText().length();
		}
	}

	//Retorna true si el caracter se puede castear a Integer
	private boolean esNumero(char c) {
		try {
			Integer.parseInt(Character.toString(c));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//Modifica algunas caracteristicas del texto calculo anterior y lo agrega a la pantalla
	private void agregarVista_calculoAnterior() {
		calculoAnterior.setHorizontalAlignment(SwingConstants.RIGHT);
		calculoAnterior.setEditable(false);
		calculoAnterior.setBorder(null);
		calculoAnterior.setBackground(Color.WHITE);
		calculoAnterior.setBounds(10, 11, 282, 29);
		add(calculoAnterior);
	}

	//Muestra la operacion anterior
	private void deshacer(ActionEvent e) {
		if (e.getSource().equals(botones[20])) {
			if (posMemoria < interRepo.getEstadoOperandos().size()) {
				posMemoria += 1;
				volverAlEstadoAnterior(posMemoria);
			}
			if (posMemoria >= interRepo.getEstadoOperandos().size()	|| posMemoria < -1)
				posMemoria = -1;

		}
	}

	//Muestra la operacion posterior en caso de haber retrocedido antes
	private void rehacer(ActionEvent e) {
		if (e.getSource().equals(botones[21])) {
			if (posMemoria < interRepo.getEstadoOperandos().size()) {
				posMemoria -= 1;
				volverAlEstadoAnterior(posMemoria);
			}
			if (posMemoria >= interRepo.getEstadoOperandos().size() || posMemoria < -1)
				posMemoria = -1;
		}
	}

	//Modifica caracteristicas del texto de resultados y lo agrega a pantalla
	private void agregarVista_resultados() {
		resultados.setHorizontalAlignment(SwingConstants.RIGHT);
		resultados.setFont(new Font("Tahoma", Font.BOLD, 16));
		resultados.setBorder(null);
		resultados.setBackground(Color.WHITE);
		resultados.setEditable(false);
		resultados.setBounds(10, 38, 282, 50);
		add(resultados);
	}

	//Agrega los botones en sus respectivos lugares
	private void agregarBotones() {
		Font fuente=new Font("Calibri", 3, 20);
		String[] nombres = { "9", "6", "3", "0", "8", "5", "2", ".", "7", "4","1", "=", "+", "-", "x", "/", "c", "ce", "cy", "pe", "->", "<-" };
		int x = 10;
		int y = 99;
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JButton(nombres[i]);
			botones[i].addActionListener(this);
			botones[i].setBorderPainted(false);
			botones[i].setFont(fuente);
			add(botones[i]);
			if (i > 3 && i < 8)
				x = 68;
			if (i >= 8 && i < 12)
				x = 126;
			if (i >= 12 && i < 16)
				x = 186;
			if (i >= 16)
				x = 246;
			if (i == 4 || i == 8 || i == 12 || i == 16 || i == 0)
				y = 99;
			else
				y += 61;
			if (i != 20 && i != 21)
				botones[i].setBounds(x, y, 50, 50);
			
		}
		botones[20].setBounds(295, 11, 48, 35);
		botones[21].setBounds(295, 52, 48, 35);
	}

	//Agrega iconos a algunos botones
	private void agregarImagenesBotones() {
		try {
			Image copiar = ImageIO.read(getClass().getResource("/Imagenes/copy.png"));
			botones[18].setIcon(new ImageIcon(copiar));
			botones[18].setIconTextGap(-10);
			botones[18].setText("");

			Image pegar = ImageIO.read(getClass().getResource("/Imagenes/paste.png"));
			botones[19].setIcon(new ImageIcon(pegar));
			botones[19].setIconTextGap(-10);
			botones[19].setText("");

			Image deshacer = ImageIO.read(getClass().getResource("/Imagenes/deshacer.png"));
			botones[20].setIcon(new ImageIcon(deshacer));
			botones[20].setIconTextGap(-10);
			botones[20].setText("");

			Image rehacer = ImageIO.read(getClass().getResource("/Imagenes/rehacer.png"));
			botones[21].setIcon(new ImageIcon(rehacer));
			botones[21].setIconTextGap(-10);
			botones[21].setText("");
			
			Image borrarElemento = ImageIO.read(getClass().getResource("/Imagenes/borrarElemento.png"));
			botones[16].setIcon(new ImageIcon(borrarElemento));
			botones[16].setIconTextGap(-10);
			botones[16].setText("");

			Image borrarTodo = ImageIO.read(getClass().getResource("/Imagenes/borrarTodo.png"));
			botones[17].setIcon(new ImageIcon(borrarTodo));
			botones[17].setIconTextGap(-10);
			botones[17].setText("");
		} catch (Exception e) {
			throw new IllegalArgumentException("Imagen no encontrada");
		}
	}

	//Reinicia valores
	private void resetValores() {
		resultados.setText("");
		calculoAnterior.setText("");
		indice_decimal=0;
		indice_operando=0;
		intermediario.reiniciarValores();
	}

	//Cambia el estado por el texto actual
	public void setMemoria() throws IOException {
		estado.SetEstado(resultados.getText());
		interRepo.setMemoria(estado.getEstadoNums());

	}

	//Vuelve una operacion atras en la memoria
	public void volverAlEstadoAnterior(int index) {
		resetValores();
		if (index < interRepo.getEstadoOperandos().size()) {
			try {
				resultados.setText(interRepo.getEstadoOperandos().get(index));
				mantenerIndicesCorrectos();
			} catch (Exception e) {
				return;
			}
		}
	}
	
	//Actualiza los indices luego de haber modificado el texto de resultados
	private void mantenerIndicesCorrectos(){
		for(int i=0;i<resultados.getText().length();i++){
			if(! esNumero(resultados.getText().charAt(i)))
				if(resultados.getText().charAt(i)=='.')
					indice_decimal=i;
				else
					indice_operando=i;
		}
	}
}

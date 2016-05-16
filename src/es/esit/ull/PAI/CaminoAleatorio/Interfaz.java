package es.esit.ull.PAI.CaminoAleatorio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Interfaz extends JFrame {
	private JButton iniciar;
	private JButton pausar;
	private JButton cambiarColor;
	private JButton tamLaberinto;
	private final String INICIAR = "Iniciar";
	private final String PAUSAR = "Pausar/Reanudar";
	private final String CAMBIA_COLOR = "Cambiar Color";
	private final String TAM_LAB = "Cambiar Tamaño";
	private JPanel controles;
	private CaminoAleatorioGrafico caminoAleatorioGrafico;
	private int tamLab;
	private Timer timer;
	private int delay;
	private boolean pausado;
	
	public Interfaz(int tamLab) {
		setTamLab(tamLab);
		iniciar();
		iniciarComponentes();
		iniciarLayout();
		establecerEstilo();
		iniciarOyentes();
	}

	private void iniciarOyentes() {
		getIniciar().addActionListener(new IniciarListener());
		getPausar().addActionListener(new PausarListener());
		getCambiarColor().addActionListener(new CambiarColorListener());
		getTamLaberinto().addActionListener(new CambiarTamListener());
	}
	
	private void establecerEstilo() {
		getControles().setBorder(BorderFactory.createTitledBorder("Controles"));
		getControles().setBackground(Color.LIGHT_GRAY);
		getCaminoAleatorioGrafico().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	private void iniciarLayout() {
		// Controles
		getControles().setLayout(new GridLayout(1,4));
		getControles().add(getIniciar());
		getControles().add(getPausar());
		getControles().add(getCambiarColor());
		getControles().add(getTamLaberinto());
		
		
		// Interfaz
		setLayout(new BorderLayout());
		add(getCaminoAleatorioGrafico(), BorderLayout.CENTER);	
		add(getControles(), BorderLayout.PAGE_END);
	}

	private void iniciarComponentes() {
		setIniciar(new JButton(getINICIAR()));
		setPausar(new JButton(getPAUSAR()));
		setCambiarColor(new JButton(getCAMBIA_COLOR()));
		setTamLaberinto(new JButton(getTAM_LAB()));
		setCaminoAleatorioGrafico(new CaminoAleatorioGrafico(getTamLab()));
		setControles(new JPanel());
		setDelay(10);
		setPausado(false);
		setTimer(new Timer(delay, new TimerListener()));
	}

	private void iniciar() {
		setSize(new Dimension(super.getToolkit().getScreenSize()));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Generador de caminos aleatorios");
	}
	
	class IniciarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getCaminoAleatorioGrafico().setResuelto(true);
			getTimer().start();
			repaint();
		}	
	}
	
	class PausarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isPausado()) {
				getTimer().restart();
				setPausado(false);
			} else {
				getTimer().stop();
				setPausado(true);
			}
		}	
	}
	
	class CambiarColorListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			getCaminoAleatorioGrafico().setCambiarColor(true);
			repaint();
		}	
	}
	
	class CambiarTamListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String entrada = JOptionPane.showInputDialog("Introducir nuevo tamaño.");
			int tam = Integer.parseInt(entrada);
			getCaminoAleatorioGrafico().reiniciarCamino();
			getCaminoAleatorioGrafico().setTam(tam);
			repaint();
		}	
	}
	
	class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			getCaminoAleatorioGrafico().crearCamino();
			repaint();
		}
		
	}
	/**
	 * Getter & Setter
	 */
	
	public JButton getIniciar() {
		return iniciar;
	}

	public boolean isPausado() {
		return pausado;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JButton getTamLaberinto() {
		return tamLaberinto;
	}

	public void setTamLaberinto(JButton tamLaberinto) {
		this.tamLaberinto = tamLaberinto;
	}

	public String getTAM_LAB() {
		return TAM_LAB;
	}

	public int getTamLab() {
		return tamLab;
	}

	public void setTamLab(int tamLab) {
		this.tamLab = tamLab;
	}

	public JPanel getControles() {
		return controles;
	}

	public void setControles(JPanel controles) {
		this.controles = controles;
	}

	public void setIniciar(JButton iniciar) {
		this.iniciar = iniciar;
	}

	public JButton getPausar() {
		return pausar;
	}

	public void setPausar(JButton pausar) {
		this.pausar = pausar;
	}

	public JButton getCambiarColor() {
		return cambiarColor;
	}

	public void setCambiarColor(JButton cambiarColor) {
		this.cambiarColor = cambiarColor;
	}

	public CaminoAleatorioGrafico getCaminoAleatorioGrafico() {
		return caminoAleatorioGrafico;
	}

	public void setCaminoAleatorioGrafico(CaminoAleatorioGrafico caminoAleatorioGrafico) {
		this.caminoAleatorioGrafico = caminoAleatorioGrafico;
	}

	public String getINICIAR() {
		return INICIAR;
	}

	public String getPAUSAR() {
		return PAUSAR;
	}

	public String getCAMBIA_COLOR() {
		return CAMBIA_COLOR;
	}
}

package es.esit.ull.PAI.CaminoAleatorio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class CaminoAleatorioGrafico extends JPanel {
	private int anchoVentana;
	private int altoVentana;
	private int proporcionX;
	private int proporcionY;
	private int tam;
	private boolean iniciado;
	private boolean resuelto;
	private boolean pausado;
	private boolean cambiarColor;
	private Point puntoInicio;
	private Point puntoPartida;
	private ArrayList<Point> camino;
	private boolean terminado;
	private Color colorCamino;

	public CaminoAleatorioGrafico(int tam) {
		setPuntoPartida(null);
		setPuntoInicio(null);
		setCamino(new ArrayList<Point>());
		setAnchoVentana(0);
		setAltoVentana(0);
		setTam(tam);
		setIniciado(false);
		setResuelto(false);
		setPausado(false);
		setCambiarColor(false);
		setColorCamino(Color.RED);
	}

	public void reiniciarCamino() {
		reiniciar();
		setPuntoPartida(null);
		setPuntoInicio(null);
		setCamino(new ArrayList<Point>());
	}
	public void crearCamino() {
		// Generar camino.
		Random generarMovimiento = new Random();
		Point nuevoPunto;
		
		switch (generarMovimiento.nextInt(4)) {
		// arriba
		case 0:
			nuevoPunto = new Point((int) getPuntoInicio().getX(), (int) (getPuntoInicio().getY() - getProporcionY()));
			setPuntoInicio(nuevoPunto);
			break;
		// Abajo
		case 1:
			nuevoPunto = new Point((int) getPuntoInicio().getX(), (int) (getPuntoInicio().getY() + getProporcionY()));
			setPuntoInicio(nuevoPunto);
			break;
		// Derecha
		case 2:
			nuevoPunto = new Point((int) getPuntoInicio().getX() + getProporcionX(), (int) (getPuntoInicio().getY()));
			setPuntoInicio(nuevoPunto);
			break;
		// Izquierda
		case 3:
			nuevoPunto = new Point((int) getPuntoInicio().getX() - getProporcionX(), (int) (getPuntoInicio().getY()));
			setPuntoInicio(nuevoPunto);
			break;
			
		default:
			break;
		}
		if (getPuntoInicio().getX() < -getProporcionX()
				&& getPuntoInicio().getX() > getAnchoVentana()
				&& getPuntoInicio().getY() < -getProporcionY() 
				&& getPuntoInicio().getY() > getAltoVentana()) {
			setTerminado(true);
		} else {
			getCamino().add(getPuntoInicio());
		}
	}
	
	public void reiniciar() {
		setIniciado(false);
		setResuelto(false);
		setPausado(false);
		setCambiarColor(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isIniciado()) {
			obtenerDimensiones();
			dibujarLaberinto(g);
			setIniciado(true);
		} else {
			if (isResuelto()) {
				dibujarLaberinto(g);
				dibujarSolucion(g);
			} else {
				dibujarLaberinto(g);
			}
		}
	}

	private void dibujarLaberinto(Graphics g) {
		int incrementoX = this.getAnchoVentana() / 2;
		int incrementoY = this.getAltoVentana() / 2;
		for (int i = 0; i < getTam(); i++) {
			g.drawLine(incrementoX, 0, incrementoX, getAltoVentana());
			g.drawLine(0, incrementoY, getAnchoVentana(), incrementoY);
			incrementoX += getProporcionX();
			incrementoY += getProporcionY();
		}
		
		incrementoX = this.getAnchoVentana() / 2;
		incrementoY = this.getAltoVentana() / 2;
		for (int i = 0; i < getTam(); i++) {
			g.drawLine(incrementoX, 0, incrementoX, getAltoVentana());
			g.drawLine(0, incrementoY, getAnchoVentana(), incrementoY);
			incrementoX -= getProporcionX();
			incrementoY -= getProporcionY();
		}
	}

	private void obtenerDimensiones() {
		// Ajustando distancias.
		setAnchoVentana(this.getWidth());
		setAltoVentana(this.getHeight());
		setTam(tam);
		setProporcionX(getAnchoVentana() / getTam());
		setProporcionY(getAltoVentana() / getTam());
		setPuntoInicio(new Point(getAnchoVentana() / 2, getAltoVentana() / 2));
		setPuntoPartida(new Point(getAnchoVentana() / 2, getAltoVentana() / 2));
		getCamino().add(getPuntoInicio());
	}

	private void dibujarSolucion(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3.0F));
		//g2d.setColor(Color.RED);
		if (isCambiarColor()) {
			Random colorNuevo = new Random();
			Color colNuevo = new Color(colorNuevo.nextInt(256), colorNuevo.nextInt(256), colorNuevo.nextInt(256));
			setColorCamino(colNuevo);
			setCambiarColor(false);
		}
		g2d.setColor(getColorCamino());
		for (int i = 0; i < getCamino().size() - 1; i++) {
			//System.out.println("Pintando -> (" + (int) getCamino().get(i).getX() + "," + (int) getCamino().get(i).getY() + ") - (" 
			//		+ (int) getCamino().get(i + 1).getX() + "," + (int) getCamino().get(i + 1).getY() + ")");
			g2d.drawLine((int) getCamino().get(i).getX(),
					(int) getCamino().get(i).getY(),
					(int) getCamino().get(i + 1).getX(),
					(int) getCamino().get(i + 1).getY());
		}
		
	}

	/**
	 * Getter & Setter
	 * @return
	 */

	
	public boolean isResuelto() {
		return resuelto;
	}

	public Color getColorCamino() {
		return colorCamino;
	}

	public void setColorCamino(Color colorCamino) {
		this.colorCamino = colorCamino;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public Point getPuntoInicio() {
		return puntoInicio;
	}

	public void setPuntoInicio(Point puntoInicio) {
		this.puntoInicio = puntoInicio;
	}

	public Point getPuntoPartida() {
		return puntoPartida;
	}

	public void setPuntoPartida(Point puntoPartida) {
		this.puntoPartida = puntoPartida;
	}

	public ArrayList<Point> getCamino() {
		return camino;
	}

	public void setCamino(ArrayList<Point> camino) {
		this.camino = camino;
	}

	public void setResuelto(boolean resuelto) {
		this.resuelto = resuelto;
	}

	public boolean isPausado() {
		return pausado;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	public boolean isCambiarColor() {
		return cambiarColor;
	}

	public void setCambiarColor(boolean cambiarColor) {
		this.cambiarColor = cambiarColor;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public int getProporcionX() {
		return proporcionX;
	}

	public void setProporcionX(int proporcionX) {
		this.proporcionX = proporcionX;
	}

	public int getProporcionY() {
		return proporcionY;
	}

	public void setProporcionY(int proporcionY) {
		this.proporcionY = proporcionY;
	}

	public int getAnchoVentana() {
		return anchoVentana;
	}

	public void setAnchoVentana(int anchoVentana) {
		this.anchoVentana = anchoVentana;
	}

	public int getAltoVentana() {
		return altoVentana;
	}

	public void setAltoVentana(int altoVentana) {
		this.altoVentana = altoVentana;
	}


}

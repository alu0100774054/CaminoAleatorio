package es.esit.ull.PAI.CaminoAleatorio;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GeneradorDeCaminos {
	private ArrayList<Point> camino;
	private int anchoVentana;
	private int altoVentana;
	private int proporcionX;
	private int proporcionY;
	private int tam;
	private Point puntoInicio;
	private Point puntoFinal;

	public GeneradorDeCaminos() {
		setCamino(new ArrayList<Point>());
		setPuntoInicio(new Point(getAnchoVentana() / 2, getAltoVentana() / 2));
		setPuntoFinal(new Point(getAnchoVentana() / 2, getAltoVentana() / 2));
	}

	public void generar(int anchoVentana, int altoVentana, int proporcionX, int proporcionY, int tam) {
		setAnchoVentana(anchoVentana);
		setAltoVentana(altoVentana);
		setProporcionX(proporcionX);
		setProporcionY(proporcionY);
		setTam(tam);
		
		Random siguienteMovimiento = new Random();

		switch (siguienteMovimiento.nextInt(4)) {
		// Arriba
		case 0:
			puntoInicio.setLocation(puntoFinal.getX(), puntoFinal.getY());
			getCamino().add(puntoInicio);
			puntoFinal.setLocation(puntoFinal.getX(), puntoFinal.getY() - proporcionY);
			break;
			// Derecha
		case 1:
			puntoInicio.setLocation(puntoFinal.getX(), puntoFinal.getY());
			getCamino().add(puntoInicio);
			puntoFinal.setLocation(puntoFinal.getX() + proporcionX, puntoFinal.getY());
			break;
			// Abajo
		case 2:
			puntoInicio.setLocation(puntoFinal.getX(), puntoFinal.getY());
			getCamino().add(puntoInicio);
			puntoFinal.setLocation(puntoFinal.getX(), puntoFinal.getY() + proporcionY);
			break;
			// Izquierda
		case 3:
			puntoInicio.setLocation(puntoFinal.getX(), puntoFinal.getY());
			getCamino().add(puntoInicio);
			puntoFinal.setLocation(puntoFinal.getX() - proporcionX, puntoFinal.getY());
			break;
		default:
			System.out.println("Fallo del generador");
			break;
		}
	}


	/**
	 * Getter & Setter
	 * @return
	 */

	public ArrayList<Point> getCamino() {
		return camino;
	}

	public Point getPuntoInicio() {
		return puntoInicio;
	}

	public void setPuntoInicio(Point puntoInicio) {
		this.puntoInicio = puntoInicio;
	}

	public Point getPuntoFinal() {
		return puntoFinal;
	}

	public void setPuntoFinal(Point puntoFinal) {
		this.puntoFinal = puntoFinal;
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

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public void setCamino(ArrayList<Point> camino) {
		this.camino = camino;
	}


}

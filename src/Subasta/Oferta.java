package Subasta;

import Inventario.Pieza;
import Usuario.Comprador;

public class Oferta {
	
	private String turno;
	private double valorOferta;
	private Comprador comprador;
	private Pieza pieza;
	
	public Oferta(String turno, double valorOferta, Comprador comprador, Pieza pieza) {
		this.turno = turno;
		this.valorOferta = valorOferta;
		this.comprador = comprador;
		this.pieza = pieza;
	}
	public String getTurno() {
		return turno;
	}
	public double getValorOferta() {
		return valorOferta;
	}
	
	public Comprador getComprador() {
		/*Obtiene la informacion del comprador necesaria para realizar la subasta*/
		return comprador;
	}
	
	public Pieza getPieza() {
		/*Obtiene la informacion de la pieza necesaria para realizar la subasta*/
		return pieza;
	}
}

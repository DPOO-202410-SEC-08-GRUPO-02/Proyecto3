package Compra;


import java.util.HashMap;
import java.util.Map;

import Inventario.Pieza;
import Subasta.Oferta;
import Subasta.Subasta;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;

public class Compra {
	
	private double valorCompra;
	private Pieza pieza;
	private Comprador comprador;
	
	public Compra(Pieza pieza, Comprador comprador, double valorCompra)
	{
		this.pieza = pieza;
		this.comprador = comprador;
		this.valorCompra = valorCompra;
	}
	
	public double getValorCompra() {
		return valorCompra;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public Comprador getComprador() {
		return comprador;
	}

	public static void pasarCaja(Comprador comprador, Pieza pieza, String tipoCompra, Administrador admin, Cajero cajero) {
		/*Se llama pasar a Caja para diferenciar que es proceso inicial de la compra, osea consultar todos 
		 * los datos necesarios y mandar a verificarlos al cajero o al administarador y dependiendo del caso pasara a compra rechazada o confirmar compra
		 */
		
		String llave = "disponibilidad";
		admin.cambiarEstadoObra(pieza, llave, "false");
		
		double valor = 0.0;
		
		if (tipoCompra == "subasta")
		{
			Oferta ganador = Subasta.getGanador();
			valor = ganador.getValorOferta();
		}
		else
		{
			valor = pieza.getValor();
		}
		
		boolean compraVerificada = admin.verificarCompra(comprador, pieza, valor, cajero);
		
		
		if(compraVerificada ==true) {
			Compra.confirmarCompra(comprador, pieza, valor, admin, cajero);
		}else{
			Compra.compraRechazada(comprador, pieza, admin );
		}
	}
	
	public static void confirmarCompra(Comprador comprador,Pieza pieza, double valorCompra, Administrador admin, Cajero cajero) {
		/* Hace todo el proceso necesario para realizar la compra, entregar la pieza al usuario, entre otros procesos*/
		HashMap<String, Double> metodoPago= (HashMap<String, Double>) comprador.getMetodoPago();
		double dineroActual= comprador.getDineroActual();
		cajero.realizarPago(valorCompra,comprador,metodoPago,dineroActual,pieza);
		
		admin.agregarPieza (comprador, pieza);
	}
	
	public static void compraRechazada (Comprador comprador, Pieza pieza, Administrador admin) {
		/*Cuando algo en la verificacion de pasar a caja sale mal entonces devolvera todo a como estaba antes 
		 * de que el usuario eligiera una pieza para la compra y intenta resolvr el problema por el cual el comprador no es apto para comprar la pieza
		 */
		admin.cambiarEstadoObra(pieza,"disponibilidad","true");
	}
}

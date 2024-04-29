package Subasta;

import java.util.Map;

import CargadorGaleria.Galeria;
import Compra.Compra;
import Inventario.Pieza;
import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Operador;

public class Subasta {
	
	private static Compra compra; 
	private static Oferta ganador;
	
	public static String generarOferta(Comprador comprador, Pieza pieza, double valor) 
	{
		
		boolean verificado = Subasta.verificarUsuario(comprador);
		
		if (verificado == true)
		{
			int turnoAnterior = Operador.getTurnoAnterior();
			String turno = Operador.generarTurno(turnoAnterior);
			
			Oferta oferta = new Oferta(turno, valor, comprador, pieza);
			Operador.agregarOferta(oferta);
			
			double valorM = pieza.getValorMinimoS();
			
			if (valor>= valorM)
			{
				ganador = Operador.elegirGanador(turno);
				return "Ha ganado la subasta";
			}
			return "Oferta realizada con exito";
		}
		else
			return "Comprador no verificado";
	}
	
	public static boolean verificarUsuario(Comprador comprador) 
	{
		/* En caso de que el usuario no este verificado al realizar una subasta hace el proceso para verificarlo y dejarlo participar en la subasta*/
		boolean verificado = Administrador.verificarUsuario(comprador);
		return verificado;
	}

	public static Map<String, Pieza> getSubasta() 
	{
		Map<String, Pieza> subasta= Galeria.getSubastaMap();
		return subasta;
	}
	
	public double ofertaAleatoria()
	{
		return -0.0;
	}
	
	public static Oferta getGanador()
	{
		return ganador;
	}

	public static Compra getCompra() 
	{
		return compra;
	}
	public static void agregarPieza(Pieza pieza) 
	{
		Galeria.agregarPiezaSubasta(pieza);
	}
}
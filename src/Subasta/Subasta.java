package Subasta;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

import CargadorGaleria.Galeria;
import Compra.Compra;
import Inventario.Pieza;
import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Operador;

public class Subasta {
	
	private Compra compra; 
	private static Oferta ganador;
	
	public static String generarOferta(Comprador comprador, Pieza pieza, double valor, Operador operador, Administrador admin) 
	{
		
		boolean verificado = Subasta.verificarUsuario(comprador, admin);
		
		if (verificado == true)
		{
			int turnoAnterior = operador.getTurnoAnterior();
			String turno = operador.generarTurno(turnoAnterior);
			
			Oferta oferta = new Oferta(turno, valor, comprador, pieza);
			operador.agregarOferta(oferta);
			
			double valorM = pieza.getValorMinimoS();
			
			if (valor>= valorM)
			{
				ganador = operador.elegirGanador(turno);
				return "Ha ganado la subasta";
			}
			return "Oferta realizada con exito";
		}
		else
			return "Comprador no verificado";
	}
	
	public static boolean verificarUsuario(Comprador comprador, Administrador admin) 
	{
		/* En caso de que el usuario no este verificado al realizar una subasta hace el proceso para verificarlo y dejarlo participar en la subasta*/
		boolean verificado = admin.verificarUsuario(comprador);
		return verificado;
	}

	public static Map<String, Pieza> getSubasta() 
	{
		Map<String, Pieza> subasta= Galeria.getSubastaMap();
		return subasta;
	}
	
	public static double ofertaAleatoria(double ofertaComprador, double valorMin, Operador operador, Pieza pieza)
	{
		Random random = new Random();
		double numeroAleatorioLargo = ofertaComprador + (valorMin - ofertaComprador) * random.nextDouble();
		DecimalFormat df = new DecimalFormat("#.##");
		String numeroAleatorioString = df.format(numeroAleatorioLargo);
		double numeroAleatorio = Double.parseDouble(numeroAleatorioString);
		
		int turnoAnterior = operador.getTurnoAnterior();
		String turno = operador.generarTurno(turnoAnterior);
		
		Comprador comprador = null;
		
		Oferta oferta = new Oferta(turno, numeroAleatorio, comprador, pieza);
		operador.agregarOferta(oferta);
		
		return numeroAleatorio;
	}
	
	public static Oferta getGanador()
	{
		return ganador;
	}

	public Compra getCompra() 
	{
		return compra;
	}
	public static void agregarPieza(Pieza pieza) 
	{
		Galeria.agregarPiezaSubasta(pieza);
	}
}
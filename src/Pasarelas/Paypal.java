package Pasarelas;

import java.util.HashMap;

import Inventario.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;

public class Paypal extends Pasarela{

	public Paypal (String nombre){
		super(nombre);
		this.nombre=nombre;
		this.Usuarios=new HashMap<>();
	}

	public static void ComprarPorPaypal(Comprador comprador, Pieza pieza, double valor, Administrador admin,
			Cajero cajero) {
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double dineroActual= comprador.getDineroActual();
		double tarjetaCredito=metodoPagoMap.get("tarjetaCredito");
		pieza.setPrecioVenta(valor);
		/*Los otros dos no se usan*/
		double transferenciaElectronica= metodoPagoMap.get("transferenciaElectronica");
		double efectivo= metodoPagoMap.get("efectivo");
		double cobroUso= 0.1;
		
		
		double interesDeUsoPaypal= tarjetaCredito *cobroUso;
		double valorCompra= valor + interesDeUsoPaypal;
		
		pieza.setPrecioVenta(valorCompra);
		
		tarjetaCredito= tarjetaCredito - valorCompra;
		dineroActual= dineroActual- valorCompra;
		
		
		dineroActual=dineroActual*100;
		double dineroActualCorto = Math.round(dineroActual);
		dineroActualCorto=dineroActualCorto/100;
		
		tarjetaCredito=tarjetaCredito*100;
		double tarjetaCreditoCorto = Math.round(tarjetaCredito);
		tarjetaCreditoCorto=tarjetaCreditoCorto/100;
		
		comprador.editarDineroActual(dineroActualCorto, tarjetaCreditoCorto, transferenciaElectronica, efectivo, metodoPagoMap);
		pieza.setEstado("Vendida");
		pieza.setVendida(true);
		admin.agregarDueño(comprador,pieza);
		admin.agregarPieza (comprador, pieza);
		admin.agregarPiezaActual(comprador, pieza);
		admin.agregarPiezaHist(comprador, pieza);
		
	}
	
	
	
}

package Usuario;

import java.util.HashMap;
import java.util.Map;

import Inventario.Pieza;

public class Cajero extends Empleado{
	
	public Cajero(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean AccesoGaleria) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero,Tipo, AccesoGaleria);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.accesoGaleria=AccesoGaleria;
	}
	
	public void realizarPago(Comprador comprador,Pieza pieza, double valorCompra, Administrador admin, Cajero cajero, String metodoPago) 
	{
		/* Descuenta el dinero de el dinero actual del comprador pero esto va de metodo de pago en metodo de pago*/
		
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double dineroActual= comprador.getDineroActual();
		
		double tarjetaCredito=metodoPagoMap.get("tarjetaCredito");
		double transferenciaElectronica= metodoPagoMap.get("transferenciaElectronica");
		double efectivo= metodoPagoMap.get("efectivo");
		

		pieza.setPrecioVenta(valorCompra);
		
		if (metodoPago.equals("tarjetaCredito"))
		{
			if (tarjetaCredito - valorCompra >= 0) 
			{
				tarjetaCredito= tarjetaCredito - valorCompra;
				dineroActual= dineroActual- valorCompra;
				valorCompra=0.001;
			}
		
			else 
			{
				dineroActual= dineroActual- tarjetaCredito;
				valorCompra= valorCompra - tarjetaCredito;
				tarjetaCredito= 0.001;
				
				if (transferenciaElectronica - valorCompra >= 0) 
				{
					transferenciaElectronica= transferenciaElectronica - valorCompra;
					dineroActual= dineroActual- valorCompra;
					valorCompra=0.001;
				}
				
				else 
				{
					dineroActual= dineroActual- transferenciaElectronica;
					valorCompra= valorCompra - transferenciaElectronica;
					transferenciaElectronica= 0;
					
					if (efectivo - valorCompra >= 0) 
					{
						efectivo= efectivo - valorCompra;
						dineroActual= dineroActual- valorCompra;
						valorCompra=0.001;
					}
					
					else 
					{
						dineroActual= dineroActual- efectivo;
						valorCompra= valorCompra - efectivo;
						efectivo= 0.001;
					}
				}
			}
		
		}
		
		else if (metodoPago.equals("efectivo"))
		{
			if (efectivo - valorCompra >= 0) 
			{
				efectivo= efectivo - valorCompra;
				dineroActual= dineroActual- valorCompra;
				valorCompra=0.001;
			}
			
			else 
			{
				dineroActual= dineroActual- efectivo;
				valorCompra= valorCompra - efectivo;
				efectivo= 0.001;
				
				if (transferenciaElectronica - valorCompra >= 0) 
				{
					transferenciaElectronica= transferenciaElectronica - valorCompra;
					dineroActual= dineroActual- valorCompra;
					valorCompra=0.001;
				}
				
				else 
				{
					dineroActual= dineroActual- transferenciaElectronica;
					valorCompra= valorCompra - transferenciaElectronica;
					transferenciaElectronica= 0.001;
					
					if (tarjetaCredito - valorCompra >= 0) 
					{
						tarjetaCredito= tarjetaCredito - valorCompra;
						dineroActual= dineroActual- valorCompra;
						valorCompra=0.001;
					}
					
					else 
					{
						dineroActual= dineroActual- tarjetaCredito;
						valorCompra= valorCompra - tarjetaCredito;
						tarjetaCredito= 0.001;
					}
				}
			}
		
		}
		
		else if (metodoPago.equals("transferenciaElectronica"))
		{
			if (transferenciaElectronica - valorCompra >= 0) 
			{
				transferenciaElectronica= transferenciaElectronica - valorCompra;
				dineroActual= dineroActual- valorCompra;
				valorCompra=0.001;
			}
			
			else 
			{
				dineroActual= dineroActual- transferenciaElectronica;
				valorCompra= valorCompra - transferenciaElectronica;
				transferenciaElectronica= 0.001;
				
				if (tarjetaCredito - valorCompra >= 0) 
				{
					tarjetaCredito= tarjetaCredito - valorCompra;
					dineroActual= dineroActual- valorCompra;
					valorCompra=0.001;
				}
				
				else 
				{
					dineroActual= dineroActual- tarjetaCredito;
					valorCompra= valorCompra - tarjetaCredito;
					tarjetaCredito= 0.001;
					
					if (efectivo - valorCompra >= 0) 
					{
						efectivo= efectivo - valorCompra;
						dineroActual= dineroActual- valorCompra;
						valorCompra=0.001;
					}
					
					else 
					{
						dineroActual= dineroActual- efectivo;
						valorCompra= valorCompra - efectivo;
						efectivo= 0.001;
					}
				}
			}
		
		}
		
		dineroActual=dineroActual*100;
		double dineroActualCorto = Math.round(dineroActual);
		dineroActualCorto=dineroActualCorto/100;
		
		efectivo=efectivo*100;
		double efectivoCorto = Math.round(efectivo);
		efectivoCorto=efectivoCorto/100;
		
		tarjetaCredito=tarjetaCredito*100;
		double tarjetaCreditoCorto = Math.round(tarjetaCredito);
		tarjetaCreditoCorto=tarjetaCreditoCorto/100;
		
		transferenciaElectronica=transferenciaElectronica*100;
		double transferenciaElectronicaCorto = Math.round(transferenciaElectronica);
		transferenciaElectronicaCorto=transferenciaElectronicaCorto/100;
		
		comprador.editarDineroActual(dineroActualCorto, tarjetaCreditoCorto, transferenciaElectronicaCorto, efectivoCorto, metodoPagoMap);
		pieza.setEstado("Vendida");
		pieza.setVendida(true);
		admin.agregarDueño(comprador,pieza);
		admin.agregarPieza (comprador, pieza);
		admin.agregarPiezaActual(comprador, pieza);
		admin.agregarPiezaHist(comprador, pieza);
		
	}
	
	public boolean verificarSaldo(double valorCompra, Comprador comprador, Map<String, Double> map, double dineroActual) {
		/*Verifica si el saldo del comprador es suficiente para comprar la obra y que sus metodos de pago si sean igual al dinero actual*/
		
		double Total=  map.get("tarjetaCredito")+ map.get("transferenciaElectronica") + map.get("efectivo");
		
		if (dineroActual == Total) {
			if (dineroActual > valorCompra)
			return true;
		}
		return false;
	}
	
	public boolean verificarLimite(double valorCompra, Comprador comprador) {
		/*Verifica si el limite del comprador es suficiente para comprar la obra*/
		double limiteCompras = comprador.getLimiteCompras();
		double dineroActual = comprador.getDineroActual();
		
		if (valorCompra <= limiteCompras)
		{
			return true;
		}
		else 
		{
			if (valorCompra <= dineroActual)
			{
				comprador.setLimiteCompras(valorCompra);
				return true;
			}
			else
				return false;
		}
	}
	
	public void realizarDevolucion (Comprador comprador, Pieza pieza, String metodoDePago,Administrador administrador) {
		/* Verifica que todo este en orden para realizar una devolucion si es asi entonces la aprueba*/
		double dineroActual= comprador.getDineroActual();
		double precioVenta=pieza.getPrecioVenta();
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double efectivo= metodoPagoMap.get("efectivo");
		double tarjetaCredito=metodoPagoMap.get("tarjetaCredito");
		double transferenciaElectronica= metodoPagoMap.get("transferenciaElectronica");
		if (metodoDePago=="efectivo") {
		dineroActual=dineroActual+precioVenta;
		efectivo= efectivo+precioVenta;
			}
		
		if (metodoDePago=="tarjetaCredito") {
			dineroActual=dineroActual+precioVenta;
			tarjetaCredito= tarjetaCredito+precioVenta;
			}
		
		if (metodoDePago=="transferenciaElectronica") {
			dineroActual=dineroActual+precioVenta;
			transferenciaElectronica= transferenciaElectronica+precioVenta;
			}
		comprador.editarDineroActual(dineroActual, tarjetaCredito, transferenciaElectronica, efectivo, metodoPagoMap);
		administrador.verificarDevolucion (comprador, pieza);
	}
}

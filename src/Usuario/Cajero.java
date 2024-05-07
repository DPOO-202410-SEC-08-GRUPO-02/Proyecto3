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
		
		comprador.editarDineroActual(dineroActual, tarjetaCredito, transferenciaElectronica, efectivo, metodoPagoMap);
		admin.agregarPieza (comprador, pieza);
		
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
}

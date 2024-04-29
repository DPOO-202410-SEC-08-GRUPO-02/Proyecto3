package Usuario;

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
	
	public void realizarPago(double valorCompra, Comprador comprador, Map<String, Double> map,double dineroActual,Pieza pieza) {
		/* Descuenta el dinero de el dinero actual del comprador pero esto va de metodo de pago en metodo de pago*/
			
		double tarjetaCredito=map.get("Tarjeta de Credito");
		double transferenciaElectronica= map.get("Transferencia Electronica");
		double Efectivo= map.get("Efectivo");
		
		if (tarjetaCredito - valorCompra >= 0) {
			tarjetaCredito= tarjetaCredito - valorCompra;
			dineroActual= dineroActual- valorCompra;
			valorCompra=0;
		}else {
			dineroActual= dineroActual- tarjetaCredito;
			valorCompra= valorCompra - tarjetaCredito;
			tarjetaCredito= 0;
			
			if (transferenciaElectronica - valorCompra >= 0) {
				transferenciaElectronica= transferenciaElectronica - valorCompra;
				dineroActual= dineroActual- valorCompra;
				valorCompra=0;
			}else {
				dineroActual= dineroActual- transferenciaElectronica;
				valorCompra= valorCompra - transferenciaElectronica;
				transferenciaElectronica= 0;
				
				if (Efectivo - valorCompra >= 0) {
					Efectivo= Efectivo - valorCompra;
					dineroActual= dineroActual- valorCompra;
					valorCompra=0;
				}else {
					dineroActual= dineroActual- Efectivo;
					valorCompra= valorCompra - Efectivo;
					Efectivo= 0;
				}
			}
		}
		
		comprador.editarDineroActual(dineroActual, tarjetaCredito, transferenciaElectronica, Efectivo, map);
		
		
	}
	
	public boolean verificarSaldo(double valorCompra, Comprador comprador, Map<String, Double> map, double dineroActual) {
		/*Verifica si el saldo del comprador es suficiente para comprar la obra y que sus metodos de pago si sean igual al dinero actual*/
		
		double Total=  map.get("Tarjeta de Credito")+ map.get("Transferencia Electronica") + map.get("Efectivo");
		
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

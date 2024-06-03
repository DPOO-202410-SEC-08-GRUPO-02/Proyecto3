package Pasarelas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CargadorGaleria.Galeria;
import Inventario.Pieza;
import Usuario.Comprador;

	public class Pasarela {
		
		protected String nombre;
		protected Map<String, Map<String, Object>> Usuarios;
		
		public Pasarela (String nombre){
			this.nombre=nombre;
			this.Usuarios=new HashMap<>();
		}


		public void addNuevoUsuarioPas(String nombreU, List<String> pasarelas, double dineroActual, String login, String numeroTarjeta, Map<String, Double> metodoPago, 
				String correo, double limiteDeCompras, String id, String estadoTarjeta, String contraseña, int numeroCuentaPas ) 
	    {
	        Map<String, Object> infoUsuario = new HashMap<>();
	        infoUsuario.put("nombre", nombreU);
	        infoUsuario.put("pasarelas", pasarelas);
	        infoUsuario.put("dineroActual", dineroActual);
	        infoUsuario.put("login", login);
	        infoUsuario.put("contraseña", contraseña);
	        infoUsuario.put("numeroTarjeta", numeroTarjeta);
	        infoUsuario.put("metodoPago", metodoPago);
	        infoUsuario.put("correo", correo );
	        infoUsuario.put("limiteCompras", limiteDeCompras);
	        infoUsuario.put("id", id);
	        infoUsuario.put("estadoTarjeta", estadoTarjeta);
	        infoUsuario.put("numeroCuentaPas", numeroCuentaPas);
	        Usuarios.put(nombreU, infoUsuario);
	    }
		
		public String getNombre() {
			return nombre;
		}



		public void setNombre(String nombre) {
			this.nombre = nombre;
		}



		public Map<String, Map<String, Object>> getUsuarios() {
			return Usuarios;
		}

		public void setUsuarios(Map<String, Map<String, Object>> usuarios) {
			Usuarios = usuarios;
		}


		public static Map<String,String>  verificarcompra(Comprador comprador, Pieza pieza) {
			String nombre= comprador.getNombre();
			String numeroTarjeta= comprador.getNumeroTarjeta();
			int numeroCuentaPas=comprador.getNumeroCuentaPas();
			List<String> Pasarelas= comprador.getPasarelas();
			String estadoTarjeta= comprador.getEstadoTarjeta();
			
			HashMap<String, String> respuesta= new HashMap<String,String>();
			String pasarela="n/a";
			/*informacion de la pasarela para verificart que sea la misma cuenta y misma tarjeta de credito registradas en la pasarela*/
			for (int i = 0; i < Pasarelas.size(); i++) {
				String pasarelaN= Pasarelas.get(i);
				Pasarela pasarelaI = Galeria.getPasarela(pasarelaN);
				Map<String, Map<String, Object>> Usuarios= pasarelaI.getUsuarios();
				Map<String, Object> UsuarioEspecifico = Usuarios.get(nombre);
				String numeroTarjetaRegistrado= (String) UsuarioEspecifico.get("numeroTarjeta");
				int numeroCuentaPasRegistrado=(int) UsuarioEspecifico.get("numeroCuentaPas");
				 
				
				if (numeroTarjeta==numeroTarjetaRegistrado && numeroCuentaPas==numeroCuentaPasRegistrado && estadoTarjeta=="Disponible") {
					respuesta.put("bool", "true");
					respuesta.put("nombrePasarela", pasarelaN );
					return respuesta;
					
				}
			}
			respuesta.put("bool", "false");
			respuesta.put("nombrePasarela", pasarela );
			return respuesta;
			
		}
		
	}


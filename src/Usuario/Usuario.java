package Usuario;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Inventario.Pieza;

public class Usuario {
			protected String login;
			protected String contraseña;
			protected String iD;
			protected String nombre;
			protected String correo;
			protected int numero;
			protected String tipo;
			
	public Usuario(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo) {
				this.login=Login;
				this.contraseña=Contraseña;
				this.iD=ID;
				this.nombre=Nombre;
				this.correo=Correo;
				this.numero=Numero;
				this.tipo=Tipo;
			}
	public Map<String,Usuario> getUsuarios()
	{
		Map<String,Usuario> usuarios = Galeria.getUsuariosMap();
		return usuarios;
	}
	
	public String getLogin() {
		return login;
	}

	public String getContraseña() {
		return contraseña;
	}

	public String getID() {
		return iD;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public int getNumero() {
		return numero;
	}
	public String getTipo() {
		return tipo;
	}
	public static Administrador getAdmin()
	{
		Map<String,Usuario> usuarios = Galeria.getUsuariosMap();
		Administrador admin = null;
		
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) 
        {
            Usuario usuario = entry.getValue();
            
            String tipo = usuario.getTipo();
            
            if (tipo.equals("Administrador"))
            {
            	admin = (Administrador) usuario;
            }
        }
        
        return admin;
	}
	
	public static Cajero getCajero()
	{
		Map<String,Usuario> usuarios = Galeria.getUsuariosMap();
		Cajero cajero = null;
		
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) 
        {
            Usuario usuario = entry.getValue();
            
            String tipo = usuario.getTipo();
            
            if (tipo.equals("Cajero"))
            {
            	cajero = (Cajero) usuario;
            }
        }
        
        return cajero;
	}
	
	public static Operador getOperador()
	{
		Map<String,Usuario> usuarios = Galeria.getUsuariosMap();
		Operador operador = null;
		
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) 
        {
            Usuario usuario = entry.getValue();
            
            String tipo = usuario.getTipo();
            
            if (tipo.equals("Operador"))
            {
            	operador = (Operador) usuario;
            }
        }
        
        return operador;
	}
}


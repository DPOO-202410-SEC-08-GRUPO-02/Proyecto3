package Usuario;

import java.util.Map;

import CargadorGaleria.Galeria;

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
	public static Map<String,Usuario> getUsuarios()
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
	
}


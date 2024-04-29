package Usuario;

//import Galeria.Usuario;

public class Empleado extends Usuario{
	protected boolean accesoGaleria;
	
	public Empleado(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean AccesoGaleria) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero, Tipo);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.accesoGaleria=AccesoGaleria;
	}

	public boolean getAccesoGaleria() {
		return accesoGaleria;
	}

}

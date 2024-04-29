package Usuario;

public class Cliente extends Usuario {
	protected boolean verificado;
	
	public Cliente(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean Verificado) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero, Tipo);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.verificado=Verificado;
	}

	public boolean getVerificado() {
		return verificado;
	}

}

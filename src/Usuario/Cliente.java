package Usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Inventario.Pieza;

public class Cliente extends Usuario {
	protected boolean verificado;
	protected Map<String,Pieza> historialPiezas= new HashMap<String,Pieza>();
	protected  List<Pieza> piezasActuales= new ArrayList<Pieza>( );
	
	public Cliente(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, 
			String Tipo, boolean Verificado, Map<String,Pieza> HistorialPiezas, List<Pieza> PiezasActuales) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero, Tipo);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.verificado=Verificado;
		this.historialPiezas = HistorialPiezas;
		this.piezasActuales = PiezasActuales;
	}

	public boolean getVerificado() {
		return verificado;
	}

	public List<Pieza> getPiezasActuales() {
		return piezasActuales;
	}

	public Map<String, Pieza> getHistorialPiezas() {
		return historialPiezas;
	}
	
	public Collection<Pieza> getHistorialPiezasValores() {
		return historialPiezas.values();
	}
	
	public void agregarPiezaHistorial(Pieza pieza)
	{
		historialPiezas.put("16-10-23", pieza);
	}
	
	public void agregarPiezaActual(Pieza pieza)
	{
		piezasActuales.add(pieza);
	}

}

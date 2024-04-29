package Usuario;

import java.util.HashMap;
import java.util.Map;

import Subasta.Oferta;

public class Operador extends Empleado {

	private int turnoAnterior= 0;
	private Map<String, Oferta> ofertas = new HashMap<String, Oferta>();
	
	public Operador(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean AccesoGaleria, int TurnoAnterior, Map<String, Oferta> Ofertas) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero, Tipo, AccesoGaleria);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.accesoGaleria=AccesoGaleria;
		this.turnoAnterior=TurnoAnterior;
		this.ofertas=Ofertas;
	}

	public int getTurnoAnterior() {
		return turnoAnterior;
	}
	
	public String generarTurno(int turnoAnterior) {
		/*Genera otro turno (o inicia la subasta).
		 * Los turnos se miden por el input del usuario.*/
		turnoAnterior = turnoAnterior+1;
		return turnoAnterior + "";
	}
	
	public Oferta elegirGanador(String turno) {
		/*Decide quien fue el ganador por el ultimo turno y verifica si el ganador si se puede permitir comprar la pieza (con el saldo maximo y dinero actual)*/
		Oferta ganador = ofertas.get(turno);
		return ganador;
	}
	
	public void agregarOferta(Oferta oferta) {
		String turno = oferta.getTurno();
		ofertas.put(turno, oferta);
	}

	public Map<String, Oferta> getOfertas() {
		return ofertas;
	}
	
}

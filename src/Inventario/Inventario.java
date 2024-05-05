package Inventario;

import java.util.Map;

import CargadorGaleria.Galeria;

public class Inventario {

	public static Pieza getPiezaInventario(String idPieza) {
		/*Obtiene la informacion de una pieza*/
		boolean esta = Galeria.existePieza(idPieza);
		
		if (esta == false)
			return null;
		else
		{
		Pieza pieza = Galeria.getPiezaInventario(idPieza);
		return pieza;
		}
	}
	public static void agregarPiezaInventario(Pieza pieza) {
		/*Obtiene la informacion de una pieza*/
		Galeria.agregarPiezaInventario(pieza);
	}
	
	public static Map<String,Pieza> getInventario()
	{
		Map<String,Pieza> inventario = Galeria.getInventarioMap();
		return inventario;
	}
	
	public void modificarDisponibilidad(Pieza pieza, Boolean disponibilidad) {
		/*Modifica la Disponibilidad de una pieza*/
		pieza.setDisponibilidad(disponibilidad);
	}
	
	public void modificarEstado(Pieza pieza, String Estado) {
		/*Modifica el Estado de una pieza*/
		pieza.setEstado(Estado);
	}

	public static void modificarConsignacion(String fechaLimite, Pieza pieza) {
		/*Modifica la consignacion de una pieza y elimina la fecha limite en caso de que se venda o devuelva al propietario*/
		pieza.setConsignacion(true);
		pieza.setFechaLimite(fechaLimite);
	}

	public void modificarDevolucion(Pieza pieza, Boolean Devolucion) {
		/*Modifica la Devolucion de una pieza*/
		pieza.setDevolucion(Devolucion);
	}
}

package CargadorGaleria;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Artista.Artista;
import Inventario.Pieza;
import Usuario.Usuario;

public class Galeria {
	
	private static Map<String,Pieza> inventarioMap= new HashMap<String,Pieza>();
	private static Map<String,Pieza> subastaMap= new HashMap<String,Pieza>();
	private static Map<String,Usuario> usuariosMap= new HashMap<String,Usuario>();
	private static Map<String,Artista> artistasMap= new HashMap<String,Artista>();
	
	/*Subasta sera mas pequeña que Inventario porque solo estaran los elementos que tengan subasta en True*/
	
	public static Pieza getPiezaInventario(String idPieza) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Pieza pieza= inventarioMap.get(idPieza);
		return pieza;
	}
	
	public static Pieza getPiezaSubasta(String idPieza) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Pieza pieza= subastaMap.get(idPieza);
		return pieza;
	}
	
	public static Object getUsuario(String idUsuario) {
		/*Obtiene la informacion de la informacion del Usuario*/
		Object usuario = usuariosMap.get(idUsuario);
		return usuario;
	}
	
	public static Artista getArtista(String nombre) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Artista artista= artistasMap.get(nombre);
		return artista;
	}
	
	public static Collection<Pieza> getInventarioValores() {
		return inventarioMap.values();
	}

	public static Collection<Pieza> getSubastaValores() {
		return subastaMap.values();
	}

	public static Collection<Usuario> getUsuariosValores() {
		return usuariosMap.values();
	}
	
	public static Collection<Artista> getArtistaValores() {
		return artistasMap.values();
	}
	
	public static Map<String, Pieza> getInventarioMap() {
		return inventarioMap;
	}

	public static Map<String, Pieza> getSubastaMap() {
		return subastaMap;
	}

	public static Map<String, Usuario> getUsuariosMap() {
		return usuariosMap;
	}

	public static Map<String, Artista> getArtistasMap() {
		return artistasMap;
	}

	public static void agregarPiezaSubasta(Pieza pieza) {
		
		String id = pieza.getID();
		subastaMap.put(id, pieza);
	}
	
	public static void agregarPiezaInventario(Pieza pieza) {
		/*Agrega una pieza al hash map de Inventario*/
		String id = pieza.getID();
		inventarioMap.put(id, pieza);
	}
	
	public static void agregarUsuario(Usuario usuario) {
		/*Agrega un usuario al hash map de Usuario*/
		String id = usuario.getID();
		usuariosMap.put(id, usuario);
	}
	
	public static void agregarArtista(Artista artista) {
		/*Agrega un usuario al hash map de Usuario*/
		String nombre = artista.getNombre();
		artistasMap.put(nombre, artista);
	}
}

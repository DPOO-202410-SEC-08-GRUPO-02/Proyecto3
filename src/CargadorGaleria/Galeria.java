package CargadorGaleria;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Artista.Artista;
import Inventario.Pieza;
import Pasarelas.Pasarela;
import Usuario.Usuario;

public class Galeria {
	
	private static Map<String,Pieza> inventarioMap= new HashMap<String,Pieza>();
	private static Map<String,Pieza> subastaMap= new HashMap<String,Pieza>();
	private static Map<String,Usuario> usuariosMap= new HashMap<String,Usuario>();
	private static Map<String,Artista> artistasMap= new HashMap<String,Artista>();
	private static Map<String,Pasarela> pasarelaMap= new HashMap<String,Pasarela>();
	
	/*Subasta sera mas pequeña que Inventario porque solo estaran los elementos que tengan subasta en True*/
	
	public static Pieza getPiezaInventario(String idPieza) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Pieza pieza= inventarioMap.get(idPieza);
		return pieza;
	}
	
	public static Map<String,Pieza> getPiezas() {
		/*Obtiene la informacion de todas las pieza en el inventario*/
		return inventarioMap;
	}
	
	public static Pieza getPiezaSubasta(String idPieza) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Pieza pieza= subastaMap.get(idPieza);
		return pieza;
	}
	
	public static Usuario getUsuario(String login) {
		/*Obtiene la informacion de la informacion del Usuario*/
		Usuario usuario = usuariosMap.get(login);
		return usuario;
	}
	
	public static Artista getArtista(String nombre) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Artista artista= artistasMap.get(nombre);
		return artista;
	}
	public static Pasarela getPasarela(String nombre) {
		/*Obtiene la informacion de una pieza en el inventario*/
		Pasarela pasarela= pasarelaMap.get(nombre);
		return pasarela;
	}
	
	public static boolean existeUsuario(String login) {
		boolean esta = usuariosMap.containsKey(login);
		return esta;
	}
	
	public static boolean existePieza(String id) {
		boolean esta = inventarioMap.containsKey(id);
		return esta;
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
	public static Collection<Pasarela> getPasarelaValores() {
		return pasarelaMap.values();
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
		String login = usuario.getLogin();
		usuariosMap.put(login, usuario);
	}
	
	public static void agregarArtista(Artista artista) {
		/*Agrega un usuario al hash map de Artista*/
		String nombre = artista.getNombre();
		artistasMap.put(nombre, artista);
	}
	
	public static void agregarPasarela(Pasarela pasarela) {
		/*Agrega un metodo de pasarela al hash map de Pasarelas*/
		String nombre = pasarela.getNombre();
		pasarelaMap.put(nombre, pasarela);
	}
}

package CargadorGaleria;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import Inventario.Escultura;
import Inventario.Fotografia;
import Inventario.Impresion;
import Inventario.Pieza;
import Inventario.Pintura;
import Inventario.Video;
import Subasta.Oferta;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;
import Usuario.Operador;
import Usuario.Propietario;
import Usuario.Usuario;

public class CargadorGaleria {
	
	public static void cargarInventario(String archivo) throws IOException
	{
		/*Lee el archivo del inventario y genera el mapa de hash de inventario y subasta*/
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		JSONObject raiz = new JSONObject( jsonCompleto );
		JSONArray jInventario = raiz.getJSONArray( "Piezas" );
		
		int numPiezas = jInventario.length( );
		
		for( int i = 0; i < numPiezas; i++ )
        {
			JSONObject pieza = jInventario.getJSONObject(i);
			
			String tipo = pieza.getString("tipo");
			String id = pieza.getString("id");
			String tecnica = pieza.getString("tecnica");
			String autor = pieza.getString("autor");
			String titulo = pieza.getString("titulo");
			int anio = pieza.getInt("anio");
			String lugar = pieza.getString("lugar");
			String estado = pieza.getString("estado");
			boolean disponibilidad = pieza.getBoolean("disponibilidad");
			String fechaLimite = pieza.getString("fechaLimite");
			double valor = pieza.getDouble("valor");
			boolean consignacion = pieza.getBoolean("consignacion");
			boolean devolucion = pieza.getBoolean("devolucion");
			boolean subasta = pieza.getBoolean("subasta");
			double valorMinimoS = pieza.getDouble("valorMinimoS");
			double valorInicialS = pieza.getDouble("valorInicialS");
			
			
			Pieza nuevaPieza = null;
			
			if(tipo.equals("Pintura"))
			{
				double alto = pieza.getDouble("alto");
				double ancho = pieza.getDouble("ancho");
				String movimientoArtistico = pieza.getString("movimientoArtistico");
				boolean instalacion = pieza.getBoolean("instalacion");
				nuevaPieza = new Pintura(id, tecnica, autor, titulo, anio, lugar, estado, 
						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS,tipo,
						alto, ancho, movimientoArtistico, instalacion);
			
			}
			else if (tipo.equals("Escultura"))
			{
				double alto = pieza.getDouble("alto");
				double ancho = pieza.getDouble("ancho");
				double profundidad = pieza.getDouble("profundidad");
				JSONArray materialesArray = (JSONArray) pieza.getJSONArray("materiales");
				List<String> materiales = new ArrayList<>();
	            for (Object material : materialesArray) {
	                materiales.add((String) material);
	            }
				double peso = pieza.getDouble("peso");
				boolean instalacion = pieza.getBoolean("instalacion");
				boolean electricidad = pieza.getBoolean("electricidad");
				
				nuevaPieza = new Escultura (id, tecnica, autor, titulo, anio, lugar, estado, 
						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
						alto, ancho, profundidad, materiales, peso, instalacion, electricidad);
			}
			
			else if (tipo.equals("Impresion"))
			{
				double alto = pieza.getDouble("alto");
				double ancho = pieza.getDouble("ancho");
				String soporte = pieza.getString("soporte");
				boolean instalacion = pieza.getBoolean("instalacion");
				
				nuevaPieza = new Impresion (id, tecnica, autor, titulo, anio, lugar, estado, 
						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
						alto, ancho, soporte, instalacion);
			}
			
			else if (tipo.equals("Fotografia"))
			{
				double alto = pieza.getDouble("alto");
				double ancho = pieza.getDouble("ancho");
				boolean aColor = pieza.getBoolean("aColor");
				boolean instalacion = pieza.getBoolean("instalacion");
				
				nuevaPieza = new Fotografia (id, tecnica, autor, titulo, anio, lugar, estado, 
						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
						alto, ancho, aColor, instalacion);
			}
			
			else if (tipo.equals("Video"))
			{
				String duracion = pieza.getString("duracion");
				boolean electricidad = pieza.getBoolean("electricidad");
				
				nuevaPieza = new Video (id, tecnica, autor, titulo, anio, lugar, estado, 
						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
						duracion, electricidad);
			}
			
			Galeria.agregarPiezaInventario(nuevaPieza);
			
			if (subasta == true)
			{
				Galeria.agregarPiezaSubasta(nuevaPieza);
			}
        }
		
	}
	
	public static void salvarInventario(String archivo) throws IOException
	{
		/*Con la tabla de hash de Inventario modifica lo que este diferente en el archivo de inventario*/
		
		JSONObject jobject = new JSONObject( );
		JSONArray jInventario = new JSONArray( );
		
		for( Pieza pieza : Galeria.getInventarioValores() )
        {
			JSONObject jPieza = new JSONObject( );
			jPieza.put("id", pieza.getID());
			jPieza.put("tecnica", pieza.getTecnica());
			jPieza.put("autor", pieza.getAutor());
			jPieza.put("titulo", pieza.getTitulo());
			jPieza.put("anio", pieza.getAnio());
			jPieza.put("lugar", pieza.getLugar());
			jPieza.put("estado", pieza.getEstado());
			jPieza.put("disponibilidad", pieza.getDisponibilidad());
			jPieza.put("fechaLimite", pieza.getFechaLimite());
			jPieza.put("valor", pieza.getValor());
			jPieza.put("consignacion", pieza.getConsignacion());
			jPieza.put("devolucion", pieza.getDevolucion());
			jPieza.put("subasta", pieza.getSubasta());
			jPieza.put("valorMinimoS", pieza.getValorMinimoS());
			jPieza.put("valorInicialS", pieza.getValorInicialS());
			jPieza.put("tipo", pieza.getTipo());
			
			String tipo = pieza.getTipo();
			
			if (tipo == "Pintura")
			{
				Pintura pintura = (Pintura) pieza;
				
				jPieza.put("alto", pintura.getAlto());
				jPieza.put("ancho", pintura.getAncho());
				jPieza.put("movimientoArtistico", pintura.getMovimientoArtistico());
				jPieza.put("instalacion", pintura.getInstalacion());

			}
			else if (tipo == "Escultura")
			{
				Escultura escultura = (Escultura) pieza;
				
				jPieza.put("alto", escultura.getAlto());
				jPieza.put("ancho", escultura.getAncho());
				jPieza.put("profundidad", escultura.getProfundidad());
				jPieza.put("materiales", escultura.getMateriales());
				jPieza.put("peso", escultura.getPeso());
				jPieza.put("instalacion", escultura.getInstalacion());
				jPieza.put("electricidad", escultura.getElectricidad());

			}
			
			else if (tipo == "Impresion")
			{
				Impresion impresion = (Impresion) pieza;
				
				jPieza.put("alto", impresion.getAlto());
				jPieza.put("ancho", impresion.getAncho());
				jPieza.put("soporte", impresion.getSoporte());
				jPieza.put("instalacion", impresion.getInstalacion());
			}
			
			else if (tipo == "Fotografia")
			{
				Fotografia fotografia = (Fotografia) pieza;
				
				jPieza.put("alto", fotografia.getAlto());
				jPieza.put("ancho", fotografia.getAncho());
				jPieza.put("aColor", fotografia.getaColor());
				jPieza.put("instalacion", fotografia.getInstalacion());
			}
			
			else if (tipo == "Video")
			{
				Video video = (Video) pieza;
				
				jPieza.put("duracion", video.getDuracion());
				jPieza.put("electricidad", video.getElectricidad());
			}
			
			jInventario.put(jPieza);
        }
		
		jobject.put( "Piezas", jInventario );
		
		PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
		
	}
	
	public static void salvarUsuario(String archivo) throws IOException
	{
		/*Con la tabla de hash de Usuario modifica lo que este diferente en el archivo de Usuario*/
		

		JSONObject jobject = new JSONObject( );
		JSONArray jUsuarios = new JSONArray( );
		
		for( Usuario usuario : Galeria.getUsuariosValores() )
        {
			JSONObject jUsuario = new JSONObject( );
			
			jUsuario.put("login", usuario.getLogin());
			jUsuario.put("contraseña", usuario.getContraseña());
			jUsuario.put("id", usuario.getID());
			jUsuario.put("nombre", usuario.getNombre());
			jUsuario.put("correo", usuario.getCorreo());
			jUsuario.put("numero", usuario.getNumero());
			jUsuario.put("tipo", usuario.getTipo());
			
			String tipo = usuario.getTipo();
			
			if (tipo == "Comprador")
			{
				Comprador comprador = (Comprador) usuario;
				
				jUsuario.put("verificado", comprador.getVerificado());
				jUsuario.put("dineroActual", comprador.getDineroActual());
				jUsuario.put("limiteCompras", comprador.getLimiteCompras());
			}
			
			else if (tipo == "Propietario")
			{
				Propietario propietario = (Propietario) usuario;
				
				jUsuario.put("verificado", propietario.getVerificado());
				jUsuario.put("estadoPiezas", propietario.getEstadoPiezas());
				jUsuario.put("historialPiezas", propietario.getHistorialPiezas());

			}
			else if (tipo == "Cajero")
			{
				Cajero cajero = (Cajero) usuario;
				
				jUsuario.put("accesoGaleria", cajero.getAccesoGaleria());
				
			}
			else if (tipo == "Operador")
			{
				Operador operador = (Operador) usuario;
				
				jUsuario.put("accesoGaleria", operador.getAccesoGaleria());
				jUsuario.put("turnoAnterior", operador.getTurnoAnterior());
				jUsuario.put("ofertas", operador.getOfertas());
				
			}
			
			else if (tipo == "Administrador")
			{
				Administrador administrador = (Administrador) usuario;
				
				jUsuario.put("accesoGaleria", administrador.getAccesoGaleria());
			}
			
			jUsuarios.put(jUsuario);
        }
		
		jobject.put( "Piezas", jUsuarios );
		
		PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
	}
	
	public static void cargarUsuario(String archivo) throws IOException
	
	{
		/*Lee el archivo del Usuario y genera el mapa de hash de los Usuarios*/
		
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		JSONObject raiz = new JSONObject( jsonCompleto );
		JSONArray jUsuarios = raiz.getJSONArray( "Usuarios" );
		
		int numUsuarios = jUsuarios.length( );
		
		for( int i = 0; i < numUsuarios; i++ )
        {
			JSONObject usuario = jUsuarios.getJSONObject(i);
			
			System.out.println(usuario);
			
			String tipo = usuario.getString("tipo");
			String login = usuario.getString("login");
			String contraseña = usuario.getString("contraseña");
			String id = usuario.getString("id");
			String nombre = usuario.getString("nombre");
			String correo = usuario.getString("correo");
			int numero = usuario.getInt("numero");
			
			Usuario nuevoUsuario = null;
			
			if (tipo.equals("Comprador"))
			{
				boolean verificado = usuario.getBoolean("verificado");
				double dineroActual = usuario.getDouble("dineroActual");
				double limiteCompras = usuario.getDouble("limiteCompras");
				
				nuevoUsuario = new Comprador(login, contraseña, id, nombre, correo, numero, tipo, verificado,
						dineroActual,limiteCompras);
			}
			
			else if (tipo.equals("Propietario"))
			{
				boolean verificado = usuario.getBoolean("verificado");
				
				JSONArray estadoPiezaArray = (JSONArray) usuario.getJSONArray("estadoPiezas");
				List<String> estadoPieza = new ArrayList<>();
	            for (Object estadoPiezaObj : estadoPiezaArray) {
	            	estadoPieza.add((String) estadoPiezaObj);
	            }
	            
	            JSONObject historialJson = (JSONObject) usuario.get("historialPiezas");
	            Map<String, Pieza> historial = new HashMap<>();
	            for (Object key : historialJson.keySet()) {
	                String clave = (String) key;
	                Pieza pieza = (Pieza) historialJson.get(clave);
	                historial.put(clave, pieza);
	            }
	            
				nuevoUsuario = new Propietario(login, contraseña, id, nombre, correo, numero, tipo,
						verificado, estadoPieza, historial);
			}
			else if (tipo.equals("Cajero"))
			{
				boolean accesoGaleria = usuario.getBoolean("accesoGaleria");
				
				nuevoUsuario = new Cajero(login, contraseña, id, nombre, correo, numero, tipo,accesoGaleria);
			}
			else if (tipo.equals("Operador"))
			{
				boolean accesoGaleria = usuario.getBoolean("accesoGaleria");
				int turnoAnterior = usuario.getInt("turnoAnterior");
				
				JSONObject ofertasJson = (JSONObject) usuario.get("ofertas");
	            Map<String, Oferta> ofertas = new HashMap<>();
	            for (Object key : ofertasJson.keySet()) {
	                String clave = (String) key;
	                Oferta oferta = (Oferta) ofertasJson.get(clave);
	                ofertas.put(clave, oferta);
	            }
				
				nuevoUsuario = new Operador(login, contraseña, id, nombre, correo, numero, tipo,
						accesoGaleria, turnoAnterior, ofertas);
			}
			
			else if (tipo.equals("Administrador"))
			{
				boolean accesoGaleria = usuario.getBoolean("accesoGaleria");
				
				nuevoUsuario = new Administrador(login, contraseña, id, nombre, correo, numero, tipo, accesoGaleria);
			}
			
			Galeria.agregarUsuario(nuevoUsuario);

        }
	}
}

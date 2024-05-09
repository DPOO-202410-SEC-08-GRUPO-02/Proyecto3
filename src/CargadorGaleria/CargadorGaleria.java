package CargadorGaleria;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import Artista.Artista;
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
	
	public static void cargarArtista(String archivo) throws IOException
	{
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
		JSONObject raiz = new JSONObject( jsonCompleto );
		JSONArray jArtistas = raiz.getJSONArray( "Artistas" );
		
		int numArtistas = jArtistas.length( );
		
		for( int i = 0; i < numArtistas; i++ )
        {
			JSONObject artista = jArtistas.getJSONObject(i);
			
			String nombre = artista.getString("nombre");
			
			Artista nuevoArtista = new Artista(nombre);
			
			String fechaCreacion = "";
			String fechaVenta = "";
			double precioVenta = 0.0;
			boolean vendida = false;
			
			JSONObject piezasHechasJson = (JSONObject) artista.get("piezasHechas");
	           for (Object key : piezasHechasJson.keySet()) {
	        	    String clave = (String) key;
	                JSONObject mapPieza = (JSONObject) piezasHechasJson.getJSONObject(clave);
	                fechaCreacion = mapPieza.getString("fechaCreacion");
	                fechaVenta = mapPieza.getString("fechaVenta");
	                precioVenta = mapPieza.getDouble("precioVenta");
	                vendida = mapPieza.getBoolean("vendida");

	                nuevoArtista.addPiezaHecha(clave, fechaCreacion, fechaVenta, precioVenta, vendida);
	            }
	          Galeria.agregarArtista(nuevoArtista);
        }
	}
	
	public static void salvarArtistas(String archivo) throws IOException
	{
		/*Con la tabla de hash de Inventario modifica lo que este diferente en el archivo de inventario*/
		
		JSONObject jobject = new JSONObject( );
		JSONArray jArtistas = new JSONArray( );
		
		for( Artista artista : Galeria.getArtistaValores() )
        {
			JSONObject jArtista = new JSONObject( );
			jArtista.put("nombre", artista.getNombre());
			jArtista.put("piezasHechas", artista.getPiezasHechas());
			
			jArtistas.put(jArtista);
        }
		
		jobject.put( "Artistas", jArtistas );
		
		PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
		
	}
	
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
			
			Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
			boolean subasta = nuevaPieza.getSubasta();
			
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
			
			jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);
			
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
			
			if (tipo.equals("Comprador"))
			{
				Comprador comprador = (Comprador) usuario;
				
				jUsuario.put("verificado", comprador.getVerificado());
				jUsuario.put("dineroActual", comprador.getDineroActual());
				jUsuario.put("limiteCompras", comprador.getLimiteCompras());
				jUsuario.put("metodoPago", comprador.getMetodoPago());
				
				Map<String, Pieza> mapInfoPiezas = comprador.getInfoCompras();
				Map<String, Object> jsonMapInfo = new HashMap<>();
		        for (Map.Entry<String, Pieza> entry : mapInfoPiezas.entrySet()) {
		        	
		        	JSONObject jPieza = new JSONObject( );
		            String key = entry.getKey();
		            Pieza pieza = entry.getValue();

		            jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);

		            jsonMapInfo.put(key, jPieza);
		        }

				jUsuario.put("infoCompras", jsonMapInfo);
				
				Map<String, Pieza> mapPiezas = comprador.getHistorialPiezas();
				
				Map<String, Object> jsonMap = new HashMap<>();
		        for (Map.Entry<String, Pieza> entry : mapPiezas.entrySet()) {
		        	
		        	JSONObject jPieza = new JSONObject( );
		            String key = entry.getKey();
		            Pieza pieza = entry.getValue();

		            jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);

		            jsonMap.put(key, jPieza);
		        }

				jUsuario.put("historialPiezas", jsonMap);
				
				List<Pieza> listaPiezas = comprador.getPiezasActuales();
				
				List<Object> jsonList = new ArrayList<>();
		        for (Pieza pieza: listaPiezas) {
		        	
		        	JSONObject jPieza = new JSONObject( );
		            
		            jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);

		            jsonList.add(jPieza);
		        }
				
				jUsuario.put("piezasActuales", jsonList);
			}
			
			else if (tipo.equals("Propietario"))
			{
				Propietario propietario = (Propietario) usuario;
				
				jUsuario.put("verificado", propietario.getVerificado());
				jUsuario.put("estadoPiezas", propietario.getEstadoPiezas());
				
				Map<String, Pieza> mapPiezas = propietario.getHistorialPiezas();
				
				Map<String, Object> jsonMap = new HashMap<>();
		        for (Map.Entry<String, Pieza> entry : mapPiezas.entrySet()) {
		        	
		        	JSONObject jPieza = new JSONObject( );
		            String key = entry.getKey();
		            Pieza pieza = entry.getValue();

		            jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);

		            jsonMap.put(key, jPieza);
		        }

				jUsuario.put("historialPiezas", jsonMap);
				
				List<Pieza> listaPiezas = propietario.getPiezasActuales();
				
				List<Object> jsonList = new ArrayList<>();
		        for (Pieza pieza: listaPiezas) {
		        	
		        	JSONObject jPieza = new JSONObject( );
		            
		            jPieza = CargadorGaleria.salvarPieza(jPieza, pieza);

		            jsonList.add(jPieza);
		        }
				
				jUsuario.put("piezasActuales", jsonList);

			}
			else if (tipo.equals("Cajero"))
			{
				Cajero cajero = (Cajero) usuario;
				
				jUsuario.put("accesoGaleria", cajero.getAccesoGaleria());
				
			}
			else if (tipo.equals("Operador"))
			{
				Operador operador = (Operador) usuario;
				
				jUsuario.put("accesoGaleria", operador.getAccesoGaleria());
				jUsuario.put("turnoAnterior", operador.getTurnoAnterior());
				jUsuario.put("ofertas", operador.getOfertas());
				
			}
			
			else if (tipo.equals("Administrador"))
			{
				Administrador administrador = (Administrador) usuario;
				
				jUsuario.put("accesoGaleria", administrador.getAccesoGaleria());
			}
			
			jUsuarios.put(jUsuario);
        }
		
		jobject.put( "Usuarios", jUsuarios );
		
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
				double dineroActual = 0.0;
				double limiteCompras = usuario.getDouble("limiteCompras");
				JSONObject metodoPagoJson = (JSONObject) usuario.get("metodoPago");
		        Map<String, Double> metodoPago = new HashMap<>();
		        
		        BigDecimal tarjetaBigDecimal = (BigDecimal) metodoPagoJson.get("tarjetaCredito");
		        double tarjeta = tarjetaBigDecimal.doubleValue();
		        dineroActual += tarjeta;
		        metodoPago.put("tarjetaCredito", tarjeta);
		        
		        BigDecimal transferenciaBigDecimal = (BigDecimal) metodoPagoJson.get("transferenciaElectronica");
		        double transferencia = transferenciaBigDecimal.doubleValue();
		        dineroActual += transferencia;
		        metodoPago.put("transferenciaElectronica", transferencia);
		        
		        BigDecimal efectivoBigDecimal = (BigDecimal) metodoPagoJson.get("efectivo");
		        double efectivo = efectivoBigDecimal.doubleValue();
		        dineroActual += efectivo;
		        metodoPago.put("efectivo", efectivo);
		        
		        JSONObject infoPiezasJson = (JSONObject) usuario.get("infoCompras");
	            Map<String, Pieza> infoCompras = new HashMap<>();
	            for (Object key : infoPiezasJson.keySet()) {
	                String clave = (String) key;
	                JSONObject pieza = (JSONObject) infoPiezasJson.get(clave);
	                Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
	                infoCompras.put(clave, nuevaPieza);
	            }
	            
	            JSONObject historialJson = (JSONObject) usuario.get("historialPiezas");
	            Map<String, Pieza> historial = new HashMap<>();
	            for (Object key : historialJson.keySet()) {
	                String clave = (String) key;
	                JSONObject pieza = (JSONObject) historialJson.get(clave);
	                Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
	                historial.put(clave, nuevaPieza);
	            }
	            
	            JSONArray piezasActualesArray = (JSONArray) usuario.getJSONArray("piezasActuales");
				List<Pieza> piezasActuales = new ArrayList<>();
	            for (Object piezasActualesObj : piezasActualesArray) {
	            	JSONObject pieza = (JSONObject) piezasActualesObj;
	                Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
	            	piezasActuales.add(nuevaPieza);
	            }
		        
				nuevoUsuario = new Comprador(login, contraseña, id, nombre, correo, numero, tipo, verificado,
						dineroActual,limiteCompras, metodoPago, infoCompras, historial, piezasActuales);
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
	                JSONObject pieza = (JSONObject) historialJson.get(clave);
	                Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
	                historial.put(clave, nuevaPieza);
	            }
	            
	            JSONArray piezasActualesArray = (JSONArray) usuario.getJSONArray("piezasActuales");
				List<Pieza> piezasActuales = new ArrayList<>();
	            for (Object piezasActualesObj : piezasActualesArray) {
	            	JSONObject pieza = (JSONObject) piezasActualesObj;
	                Pieza nuevaPieza = CargadorGaleria.agregarPieza(pieza);
	            	piezasActuales.add(nuevaPieza);
	            }
	            
				nuevoUsuario = new Propietario(login, contraseña, id, nombre, correo, numero, tipo,
						verificado, estadoPieza, historial, piezasActuales);
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
	
	public static Pieza agregarPieza(JSONObject pieza)
	{
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
		boolean vendida = pieza.getBoolean("vendida");
		double precioVenta = pieza.getDouble("precioVenta");
		String fechaVenta= pieza.getString("fechaVenta");
		JSONArray dueñosArray = (JSONArray) pieza.getJSONArray("dueños");
		List<String> dueños = new ArrayList<>();
		for (Object dueño : dueñosArray) {
            dueños.add((String) dueño);
        }
       
		
		
		
		Pieza nuevaPieza = null;
	
		if(tipo.equals("Pintura"))
		{
			double alto = pieza.getDouble("alto");
			double ancho = pieza.getDouble("ancho");
			String movimientoArtistico = pieza.getString("movimientoArtistico");
			boolean instalacion = pieza.getBoolean("instalacion");
			nuevaPieza = new Pintura(id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS,tipo,
					alto, ancho, movimientoArtistico, instalacion,vendida,precioVenta,fechaVenta,dueños);
		
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
					alto, ancho, profundidad, materiales, peso, instalacion, electricidad,vendida,precioVenta,fechaVenta,dueños);
		}
		
		else if (tipo.equals("Impresion"))
		{
			double alto = pieza.getDouble("alto");
			double ancho = pieza.getDouble("ancho");
			String soporte = pieza.getString("soporte");
			boolean instalacion = pieza.getBoolean("instalacion");
			
			nuevaPieza = new Impresion (id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
					alto, ancho, soporte, instalacion,vendida,precioVenta,fechaVenta,dueños);
		}
		
		else if (tipo.equals("Fotografia"))
		{
			double alto = pieza.getDouble("alto");
			double ancho = pieza.getDouble("ancho");
			boolean aColor = pieza.getBoolean("aColor");
			boolean instalacion = pieza.getBoolean("instalacion");
			
			nuevaPieza = new Fotografia (id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
					alto, ancho, aColor, instalacion,vendida,precioVenta,fechaVenta,dueños);
		}
		
		else if (tipo.equals("Video"))
		{
			String duracion = pieza.getString("duracion");
			boolean electricidad = pieza.getBoolean("electricidad");
			
			nuevaPieza = new Video (id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
					duracion, electricidad,vendida,precioVenta,fechaVenta,dueños);
		}
		
		return nuevaPieza;
	}
	public static JSONObject salvarPieza(JSONObject jPieza, Pieza pieza)
	{
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
		jPieza.put("vendida",pieza.getVendida());
		jPieza.put("precioVenta",pieza.getPrecioVenta());
		jPieza.put("fechaVenta",pieza.getFechaVenta());
		jPieza.put("dueños",pieza.getDueños());
		
		String tipo = pieza.getTipo();
		
		if(tipo.equals("Pintura"))
		{
			Pintura pintura = (Pintura) pieza;
			
			jPieza.put("alto", pintura.getAlto());
			jPieza.put("ancho", pintura.getAncho());
			jPieza.put("movimientoArtistico", pintura.getMovimientoArtistico());
			jPieza.put("instalacion", pintura.getInstalacion());

		}
		else if (tipo.equals("Escultura"))
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
		
		else if (tipo.equals("Impresion"))
		{
			Impresion impresion = (Impresion) pieza;
			
			jPieza.put("alto", impresion.getAlto());
			jPieza.put("ancho", impresion.getAncho());
			jPieza.put("soporte", impresion.getSoporte());
			jPieza.put("instalacion", impresion.getInstalacion());
		}
		
		else if (tipo.equals("Fotografia"))
		{
			Fotografia fotografia = (Fotografia) pieza;
			
			jPieza.put("alto", fotografia.getAlto());
			jPieza.put("ancho", fotografia.getAncho());
			jPieza.put("aColor", fotografia.getaColor());
			jPieza.put("instalacion", fotografia.getInstalacion());
		}
		
		else if (tipo.equals("Video"))
		{
			Video video = (Video) pieza;
			
			jPieza.put("duracion", video.getDuracion());
			jPieza.put("electricidad", video.getElectricidad());
		}
		
		return jPieza;
	}
}

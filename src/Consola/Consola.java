package Consola;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import Artista.Artista;
import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Compra.Compra;
import Inventario.Escultura;
import Inventario.Fotografia;
import Inventario.Impresion;
import Inventario.Inventario;
import Inventario.Pieza;
import Inventario.Pintura;
import Inventario.Video;
import Subasta.Oferta;
import Subasta.Subasta;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Comprador;
import Usuario.Operador;
import Usuario.Propietario;
import Usuario.Usuario;

import java.io.IOException;

public class Consola 
{

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException 
    {
    	CargadorGaleria.cargarInventario("./datos/Inventario.json");
    	CargadorGaleria.cargarArtista("./datos/Artistas.json");
    	CargadorGaleria.cargarUsuario("./datos/Usuarios.json");
    	
    	inicioSesion();
    }

    private static void inicioSesion() throws IOException
    {
    	boolean continuar = true;
    	while (continuar) 
    	{
    	
	    	System.out.println("\n*** Inicio de sesión ***\n");
	    	
	    	System.out.println("1. Iniciar sesión");
	    	System.out.println("2. Salir de la galeria");
	    	
	    	System.out.print("\nIngrese una opción: ");
	    	
	    	int opcion = scanner.nextInt();
	    	scanner.nextLine();
	    	
	    	if (opcion == 2) 
	        {
	    		CargadorGaleria.salvarInventario("./datos/Inventario.json");
	    		CargadorGaleria.salvarArtistas("./datos/Artistas.json");
	    		CargadorGaleria.salvarUsuario("./datos/Usuarios.json");
	    		
	    		
	    		System.out.println("\nGracias por visitar nuestra galería. ¡Hasta pronto!");
	    		continuar = false;
	        }
	    	else if (opcion == 1)
	        {
		    	
		    	boolean esta = false;
		    	String tipo = "";
		    	String loginA = "";
		    	
		    	while (esta == false) 
		    	{
			    	System.out.print("Login: ");
			    	String login = scanner.nextLine();
			    	loginA = login;
			    	esta = Galeria.existeUsuario(login);
			    	if (esta == false)
			    		System.out.println("El usuario no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Usuario usuario = (Usuario) Galeria.getUsuario(loginA);
		        String password = usuario.getContraseña();
		       	
		        boolean contraseñaaCorrecta = false;
		        
		        while (contraseñaaCorrecta == false)
		        {
		        	System.out.print("Contraseña: ");
		            String contraseña = scanner.nextLine();
		            
			        if (contraseña.equals(password))
			       	{
			       		tipo = usuario.getTipo();
			       		contraseñaaCorrecta = true;
		
			       	}
			        	
			       	else 
			       	{
			       		System.out.println("Contraseña incorrecta");
			       	}
		        }
		        if (tipo.equals("Administrador"))
		        {
		        	Administrador admin = (Administrador) usuario;
		        	menuAdministrador(admin);
		        }
		        else if (tipo.equals("Operador"))
		        {
		        	menuOperador();
		        }
		        else if (tipo.equals("Cajero"))
		        {
		        	menuCajero();
		        }
		        else if (tipo.equals("Comprador"))
		        {
		        	Comprador comprador = (Comprador) usuario;
		        	menuComprador(comprador);
		        }
		        
		        else if (tipo.equals("Propietario"))
		        {
		        	System.out.println("\nEl usuario ingresado es un propietario, para esta entrega no se cuenta con consola");
		        	System.out.println("Ingrese otro usuario\n");
	
		        }
	        }
	    	
	    	else 
            {
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
    	}

   	}

    private static void menuAdministrador(Administrador admin) throws IOException 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("\n*** Bienvenida Administradora ***\n");

            System.out.println("1. Cambiar el estado de una pieza");
            System.out.println("2. Cambiar la disponibilidad de una pieza");
            System.out.println("3. Agregar una pieza al inventario");
            System.out.println("4. Obtener el historial de piezas de un cliente");
            System.out.println("5. Obtener las piezas actuales de un cliente");
            System.out.println("6. Obtener el valor de las piezas actuales de un cliente");
            System.out.println("7. Mirar historia de un artista");
            System.out.println("8. Mirar historia de una pieza");
            System.out.println("0. Cerrar sesión");
            
            System.out.print("Por favor, selecciona una opción: ");
            
            
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) 
            {
                System.out.print("Ingresa el ID de la pieza que queres modificar: ");
                
                String id = scanner.nextLine();
                
    	    	Pieza pieza = Inventario.getPiezaInventario(id);
    	    	
    	    	if (pieza == null)
    	    	{
    	    		System.out.println("La pieza no existe");
    	    	}
    	    	else
    	    	{
    	    		System.out.println("\nLa pieza que vas a editar es la siguente: \n");
    	    		System.out.println("Estado: " + pieza.getEstado() + " - Título: " + pieza.getTitulo() +
    	                    " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
    	                    " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
    	    		
    	    		System.out.print("Ingresa el nuevo estado de la pieza: ");
                    
                    String estado = scanner.nextLine();
                    
                    admin.cambiarEstadoObra (pieza, "estado", estado);
                    
                    System.out.println("\nEl estado fue cambiado con éxito: ");
                    
                    pieza = Inventario.getPiezaInventario(id);
                    
                    System.out.println("Estado: " + pieza.getEstado() + " - Título: " + pieza.getTitulo() +
    	                    " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
    	                    " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
    	    	}
            } 
            else if (opcion == 2) 
            {
            	System.out.print("Ingresa el ID de la pieza que queres modificar: ");
                
                String id = scanner.nextLine();
                
    	    	Pieza pieza = Inventario.getPiezaInventario(id);
    	    	
    	    	if (pieza == null)
    	    	{
    	    		System.out.println("La pieza no existe");
    	    	}
    	    	else
    	    	{
    	    		System.out.println("\nLa pieza que vas a editar es la siguente: \n");
    	    		System.out.println("Disponibilidad: " + pieza.getDisponibilidad() + " - Título: " + pieza.getTitulo() +
    	                    " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
    	                    " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
    	    		
    	    		System.out.print("Ingresa la nueva disponibilidad de la pieza (true/false): ");
                    
                    String estado = scanner.nextLine();
                    
                    
                    
                    admin.cambiarEstadoObra (pieza, "disponibilidad", estado);
                    
                    System.out.println("\nLa disponibilidad fue cambiada con éxito: ");
                    
                    pieza = Inventario.getPiezaInventario(id);
                    
                    System.out.println("Disponibilidad: " + pieza.getDisponibilidad() + " - Título: " + pieza.getTitulo() +
    	                    " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
    	                    " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
    	    	}
            } 
            else if (opcion == 3) 
            {
            	Pieza nuevaPieza = null;
            	
            	System.out.print("1. Pintura\n");
            	System.out.print("2. Escultura\n");
            	System.out.print("3. Impresion\n");
            	System.out.print("4. Fotografia\n");
            	System.out.print("5. Video\n");
            	
            	System.out.print("Ingresa que tipo de pieza vas a agregar: ");
                
                int tipoPieza = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Ingresa el ID de la nueva pieza: ");
                String id = scanner.nextLine();
                
                System.out.print("Ingresa la tecnica de la nueva pieza: ");
                String tecnica = scanner.nextLine();
                
                System.out.print("Ingresa el autor de la nueva pieza: ");
                String autor = scanner.nextLine();
                
                System.out.print("Ingresa el titulo de la nueva pieza: ");
                String titulo = scanner.nextLine();
                
                System.out.print("Ingresa el año de la nueva pieza: ");
                int anio = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Ingresa el lugar de la nueva pieza: ");
                String lugar = scanner.nextLine();
                
                System.out.print("Ingresa el estado de la nueva pieza: ");
                String estado = scanner.nextLine();
                
                System.out.print("Ingresa la disponibilidad de la nueva pieza (false/true): ");
                String disponibilidadStr = scanner.nextLine();
                boolean disponibilidad;
                if(disponibilidadStr.equalsIgnoreCase("true")) {
                    disponibilidad = true;
                } else if(disponibilidadStr.equalsIgnoreCase("false")) {
                    disponibilidad = false;
                } else {
                    disponibilidad = false;
                }
                
                System.out.print("Ingresa la fecha limite de la nueva pieza: ");
                String fechaLimite = scanner.nextLine();
                
                System.out.print("Ingresa valor de la nueva pieza: ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                
                System.out.print("Ingresa si la nueva pieza es consignacion (false/true): ");
                String consignacionStr = scanner.nextLine();
                
                boolean consignacion;
                if(consignacionStr.equalsIgnoreCase("true")) {
                	consignacion = true;
                } else if(consignacionStr.equalsIgnoreCase("false")) {
                	consignacion = false;
                } else {
                	consignacion = false;
                }
                
                System.out.print("Ingresa si la nueva pieza es una devolucion (false/true): ");
                String devolucionStr = scanner.nextLine();
                
                boolean devolucion;
                if(devolucionStr.equalsIgnoreCase("true")) {
                	devolucion = true;
                } else if(devolucionStr.equalsIgnoreCase("false")) {
                	devolucion = false;
                } else {
                	devolucion = false;
                }
                
                System.out.print("Ingresa si la nueva pieza pertenece a una subasta (false/true): ");
                String subastaStr = scanner.nextLine();
                
                boolean subasta;
                if(subastaStr.equalsIgnoreCase("true")) {
                	subasta = true;
                } else if(subastaStr.equalsIgnoreCase("false")) {
                	subasta = false;
                } else {
                	subasta = false;
                }
                
                System.out.print("Ingresa el valor minimo de la nueva pieza para una subasta: ");
                double valorMinimoS = scanner.nextDouble();
                scanner.nextLine();
                
                System.out.print("Ingresa el valor inicial de la nueva pieza para una subasta: ");
                double valorInicialS = scanner.nextDouble();
                scanner.nextLine();
                
                if (tipoPieza == 1)
                {
                	String tipo = "Pintura";
                	
                	System.out.print("Ingresa la altura de la nueva pieza: ");
                    double alto = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el ancho de la nueva pieza: ");
                    double ancho = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el movimiento al que pertenece la nueva pieza: ");
                    String movimientoArtistico = scanner.nextLine();
                    
                    System.out.print("Ingresa si la nueva pieza necesita algun tipo de instalacion (false/true): ");
                    String instalacionStr = scanner.nextLine();
                    boolean instalacion;
                    if(instalacionStr.equalsIgnoreCase("true")) {
                    	instalacion = true;
                    } else if(instalacionStr.equalsIgnoreCase("false")) {
                    	instalacion = false;
                    } else {
                    	instalacion = false;
                    }
                    boolean vendida=false;
                    double precioVenta=0.0;
                    String fechaVenta="n/a";
                    List<String> dueños = null;
                    
                    nuevaPieza = new Pintura(id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS,tipo,
    						alto, ancho, movimientoArtistico, instalacion, vendida, precioVenta, fechaVenta, dueños);
                }
                else if (tipoPieza == 2)
                {
                	String tipo = "Escultura";
                	
                	System.out.print("Ingresa la altura de la nueva pieza: ");
                    double alto = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el ancho de la nueva pieza: ");
                    double ancho = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa la profundidad de la nueva pieza: ");
                    double profundidad = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el numero de materiales que tiene la nueva pieza: ");
                    int numMateriales = scanner.nextInt();
                    scanner.nextLine();

    				List<String> materiales = new ArrayList<>();
    	            for (int i = 0; i < numMateriales; i++) {
    	            	System.out.print("Ingresa el material: ");
                        String material = scanner.nextLine();
                        materiales.add(material);
					}
    	            
    	            System.out.print("Ingresa el peso de la nueva pieza: ");
                    double peso = scanner.nextDouble();
                    scanner.nextLine();
    	            
    	            System.out.print("Ingresa si la nueva pieza necesita algun tipo de instalacion (false/true): ");
                    String instalacionStr = scanner.nextLine();
                    boolean instalacion;
                    if(instalacionStr.equalsIgnoreCase("true")) {
                    	instalacion = true;
                    } else if(instalacionStr.equalsIgnoreCase("false")) {
                    	instalacion = false;
                    } else {
                    	instalacion = false;
                    }
                    
                    System.out.print("Ingresa si la nueva pieza necesita electricidad (false/true): ");
                    String electricidadStr = scanner.nextLine();
                    boolean electricidad;
                    if(electricidadStr.equalsIgnoreCase("true")) {
                    	electricidad = true;
                    } else if(electricidadStr.equalsIgnoreCase("false")) {
                    	electricidad = false;
                    } else {
                    	electricidad = false;
                    }
                    boolean vendida=false;
                    double precioVenta=0.0;
                    String fechaVenta="n/a";
                    List<String> dueños = null;
                    nuevaPieza = new Escultura (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, profundidad, materiales, peso, instalacion, electricidad, vendida, precioVenta, fechaVenta, dueños);
    	            
                }
                
                else if (tipoPieza == 3)
                {
                	String tipo = "Impresion";
                	
                	System.out.print("Ingresa la altura de la nueva pieza: ");
                    double alto = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el ancho de la nueva pieza: ");
                    double ancho = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el soporte de la nueva pieza: ");
                    String soporte = scanner.nextLine();
                    
                    System.out.print("Ingresa si la nueva pieza necesita algun tipo de instalacion (false/true): ");
                    String instalacionStr = scanner.nextLine();
                    boolean instalacion;
                    if(instalacionStr.equalsIgnoreCase("true")) {
                    	instalacion = true;
                    } else if(instalacionStr.equalsIgnoreCase("false")) {
                    	instalacion = false;
                    } else {
                    	instalacion = false;
                    }
                    boolean vendida=false;
                    double precioVenta=0.0;
                    String fechaVenta="n/a";
                    List<String> dueños = null;
                    nuevaPieza = new Impresion (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, soporte, instalacion, vendida, precioVenta, fechaVenta, dueños);
                }
                
                else if (tipoPieza == 4)
                {
                	String tipo = "Fotografia";
                	
                	System.out.print("Ingresa la altura de la nueva pieza: ");
                    double alto = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa el ancho de la nueva pieza: ");
                    double ancho = scanner.nextDouble();
                    scanner.nextLine();
                    
                    System.out.print("Ingresa si la nueva pieza es a color (false/true): ");
                    String aColorStr = scanner.nextLine();
                    boolean aColor;
                    if(aColorStr.equalsIgnoreCase("true")) {
                    	aColor = true;
                    } else if(aColorStr.equalsIgnoreCase("false")) {
                    	aColor = false;
                    } else {
                    	aColor = false;
                    }
                    
                    System.out.print("Ingresa si la nueva pieza necesita algun tipo de instalacion (false/true): ");
                    String instalacionStr = scanner.nextLine();
                    boolean instalacion;
                    if(instalacionStr.equalsIgnoreCase("true")) {
                    	instalacion = true;
                    } else if(instalacionStr.equalsIgnoreCase("false")) {
                    	instalacion = false;
                    } else {
                    	instalacion = false;
                    }
                    boolean vendida=false;
                    double precioVenta=0.0;
                    String fechaVenta="n/a";
                    List<String> dueños = null;
                    nuevaPieza = new Fotografia (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, aColor, instalacion, vendida, precioVenta, fechaVenta, dueños);
                }
                
                else if (tipoPieza == 5)
                {
                	String tipo = "Video";
                	
                	System.out.print("Ingresa la duracion de la nueva pieza: ");
                    String duracion = scanner.nextLine();
                	
                	System.out.print("Ingresa si la nueva pieza necesita electricidad (false/true): ");
                    String electricidadStr = scanner.nextLine();
                    boolean electricidad;
                    if(electricidadStr.equalsIgnoreCase("true")) {
                    	electricidad = true;
                    } else if(electricidadStr.equalsIgnoreCase("false")) {
                    	electricidad = false;
                    } else {
                    	electricidad = false;
                    }
                    boolean vendida=false;
                    double precioVenta=0.0;
                    String fechaVenta="n/a";
                    List<String> dueños = null;
                    nuevaPieza = new Video (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						duracion, electricidad, vendida, precioVenta, fechaVenta, dueños);
                }
                
                Galeria.agregarPiezaInventario(nuevaPieza);
    			
    			if (subasta == true)
    			{
    				Galeria.agregarPiezaSubasta(nuevaPieza);
    			}
    			
    			Pieza pieza =  Inventario.getPiezaInventario(id);
    	    	
    	    	if (pieza == null)
    	    	{
    	    		System.out.println("La no fue agregada");
    	    	}
    	    	else
    	    	{
    	    		System.out.println("\nLa pieza fue agregada con éxito: \n");
    	    		System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
    	                    " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
    	                    " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor() + " - Tipo: " + pieza.getTipo());
    	    	}
                
            } 
            else if (opcion == 4) 
            {

            	boolean esta = false;
		    	String login = "";
		    	
		    	while (esta == false) 
		    	{
		    		System.out.print("Ingrese el login del cliente: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El cliente no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Cliente cliente = (Cliente) Galeria.getUsuario(login);
		    	Map<String,Pieza> piezas = admin.piezasCompradas(cliente);
		    	
		    	if (piezas == null)
		    	{
		    		System.out.println("El cliente no tiene historial");
		    	}
		    	
		    	else
		    	{
			    	System.out.println("\nEste es el historial del cliente: \n");
			    	
			    	for (Map.Entry<String, Pieza> entry : piezas.entrySet()) 
			    	{
			    		String key = entry.getKey();
			            Pieza pieza = entry.getValue();		
			            
			            System.out.println("Pieza obtenida el: " + key);
			    		System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
		                " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
		                " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor() + "\n");
		            }
		    	}
            } 
            else if (opcion == 5) 
            {
            	
            	boolean esta = false;
		    	String login = "";
		    	
		    	while (esta == false) 
		    	{
		    		System.out.print("Ingrese el login del cliente: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El cliente no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Cliente cliente = (Cliente) Galeria.getUsuario(login);
		    	List<Pieza> piezas = admin.piezasDuenio(cliente);
		    	
		    	if (piezas == null)
		    	{
		    		System.out.println("El cliente no tiene piezas actualmente");
		    	}
		    	
		    	else
		    	{
			    	System.out.println("\nEstas son las piezas actuales del cliente: \n");
			    	
			    	for (Pieza pieza: piezas) 
			    	{

			    		System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
		                " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
		                " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor() + "\n");
		            }
		    	}
		    	
            } 
            else if (opcion == 6) 
            {
            	
            	boolean esta = false;
		    	String login = "";
		    	
		    	while (esta == false) 
		    	{
		    		System.out.print("Ingrese el login del cliente: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El cliente no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Cliente cliente = (Cliente) Galeria.getUsuario(login);
		    	double valor = admin.valorColleccion(cliente);
		    	
		    	System.out.println("El valor de colleccion del cliente es: " + (valor + ""));
            } 
            
            else if (opcion == 7) 
            {
            	boolean bucle = false;
            	Artista artista = null;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. Miguel Angelo");
		    		System.out.println("2. Leonardo da Vinci");
		    		
		    		System.out.print("Ingrese el numero del artista que decea ver: ");
			    	int opcionArtista = scanner.nextInt();

			    	if (opcionArtista == 1)
			    	{
			    		artista = Galeria.getArtista("Michel Angelo");
			    		bucle = true;
			    	}
			    	else if (opcionArtista == 2)
			    	{
			    		artista = Galeria.getArtista("Leonardo da Vinci");
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
		    	historiaArtista(artista);
		    	
            }
            
            else if (opcion == 8) 
            {
            	boolean bucle = false;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. La Mona Lisa");
		    		System.out.println("2. David");
		    		System.out.println("3. Campbell's Soup Cans");
		    		System.out.println("4. Moonrise, Hernandez, New Mexico");
		    		System.out.println("5. Toy Story");
		    		System.out.println("6. Brave");
		    		
		    		
		    		
		    		System.out.print("Ingrese el numero de la pieza que desea consultar: ");
			    	int opcionPieza = scanner.nextInt();

			    	if (opcionPieza == 1)
			    	{
			    		Pintura pintura;
			    		pintura = (Pintura) Inventario.getPiezaInventario("a");

	    	    		System.out.println("	Titulo: " + pintura.getTitulo()+"\n"+
	    	    							"		Autor: "+ pintura.getAutor() + "\n"+
	    	    							"		Año: " + pintura.getAnio()+ "\n"+
	    	    							"		Tipo: " + pintura.getTipo()+ "\n"+
	    	    							"		Movimiento Artistico: " + pintura.getMovimientoArtistico()+ "\n"+
	    	    							"		Tecnica: " + pintura.getTecnica() + "\n"+
	    	    							"		Estado: " + pintura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  pintura.getLugar()+ "\n"+
	    	    							"		Valor: $" + pintura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ pintura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ pintura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ pintura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ pintura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + pintura.getAncho()+ " cm\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + pintura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 2)
			    	{
			    		Escultura escultura;
			    		escultura = (Escultura) Inventario.getPiezaInventario("b");

	    	    		System.out.println("	Titulo: " + escultura.getTitulo()+"\n"+
	    	    							"		Autor: "+ escultura.getAutor() + "\n"+
	    	    							"		Año: " + escultura.getAnio()+ "\n"+
	    	    							"		Tipo: " + escultura.getTipo()+ "\n"+
	    	    							"		Tecnica: " + escultura.getTecnica() + "\n"+
	    	    							"		Estado: " + escultura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  escultura.getLugar()+ "\n"+
	    	    							"		Valor: $" + escultura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ escultura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ escultura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ escultura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ escultura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + escultura.getAncho()+ " cm\n"+
	    	    							"		Profundidad: " + escultura.getProfundidad()+ " cm\n"+
	    	    							"		Materiales: " + escultura.getMateriales()+ " \n"+
	    	    							"		Peso: " + escultura.getPeso()+ " kg\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + escultura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 3)
			    	{
			    		Impresion impresion;
			    		impresion = (Impresion) Inventario.getPiezaInventario("c");

	    	    		System.out.println("	Titulo: " + impresion.getTitulo()+"\n"+
	    	    							"		Autor: "+ impresion.getAutor() + "\n"+
	    	    							"		Año: " + impresion.getAnio()+ "\n"+
	    	    							"		Tipo: " + impresion.getTipo()+ "\n"+
	    	    							"		Tecnica: " + impresion.getTecnica() + "\n"+
	    	    							"		Estado: " + impresion.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  impresion.getLugar()+ "\n"+
	    	    							"		Valor: $" + impresion.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ impresion.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ impresion.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ impresion.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ impresion.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + impresion.getAncho()+ " cm\n"+
	    	    							"		Soporte: " + impresion.getSoporte()+ "\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + impresion.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 4)
			    	{
			    		Fotografia fotografia;
			    		fotografia= (Fotografia) Inventario.getPiezaInventario("d");

	    	    		System.out.println("	Titulo: " + fotografia.getTitulo()+"\n"+
	    	    							"		Autor: "+ fotografia.getAutor() + "\n"+
	    	    							"		Año: " + fotografia.getAnio()+ "\n"+
	    	    							"		Tipo: " + fotografia.getTipo()+ "\n"+
	    	    							"		Tecnica: " + fotografia.getTecnica() + "\n"+
	    	    							"		Estado: " + fotografia.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  fotografia.getLugar()+ "\n"+
	    	    							"		Valor: $" + fotografia.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ fotografia.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ fotografia.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ fotografia.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ fotografia.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + fotografia.getAncho()+ " cm\n"+
	    	    							"		¿Esta a color?: " + fotografia.getaColor()+ " \n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + fotografia.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 5)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("e");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 6)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("f");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
            }
            
            else if (opcion == 0) 
            {
                continuar = false;
            } 
            else 
            {
                System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
            }

            if (continuar==true) 
            {
                continuar = preguntarContinuar();
            }
        }
    }
    
    private static void menuOperador() 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("\n*** Bienvenido operador ***\n");
            System.out.println("1. Mirar historia de un artista");
            System.out.println("2. Mirar historia de una pieza");
            System.out.println("0. Salir");
            System.out.print("Por favor, elige una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) 
            {
            	boolean bucle = false;
            	Artista artista = null;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. Miguel Angelo");
		    		System.out.println("2. Leonardo da Vinci");
		    		
		    		System.out.print("Ingrese el numero del artista que decea ver: ");
			    	int opcionArtista = scanner.nextInt();
			    	scanner.nextLine();

			    	if (opcionArtista == 1)
			    	{
			    		artista = Galeria.getArtista("Michel Angelo");
			    		bucle = true;
			    	}
			    	else if (opcionArtista == 2)
			    	{
			    		artista = Galeria.getArtista("Leonardo da Vinci");
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
		    	historiaArtista(artista);
            } 
            else if (opcion == 2) 
            {
            	boolean bucle = false;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. La Mona Lisa");
		    		System.out.println("2. David");
		    		System.out.println("3. Campbell's Soup Cans");
		    		System.out.println("4. Moonrise, Hernandez, New Mexico");
		    		System.out.println("5. Toy Story");
		    		System.out.println("6. Brave");
		    		
		    		
		    		
		    		System.out.print("Ingrese el numero de la pieza que desea consultar: ");
			    	int opcionPieza = scanner.nextInt();

			    	if (opcionPieza == 1)
			    	{
			    		Pintura pintura;
			    		pintura = (Pintura) Inventario.getPiezaInventario("a");

	    	    		System.out.println("	Titulo: " + pintura.getTitulo()+"\n"+
	    	    							"		Autor: "+ pintura.getAutor() + "\n"+
	    	    							"		Año: " + pintura.getAnio()+ "\n"+
	    	    							"		Tipo: " + pintura.getTipo()+ "\n"+
	    	    							"		Movimiento Artistico: " + pintura.getMovimientoArtistico()+ "\n"+
	    	    							"		Tecnica: " + pintura.getTecnica() + "\n"+
	    	    							"		Estado: " + pintura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  pintura.getLugar()+ "\n"+
	    	    							"		Valor: $" + pintura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ pintura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ pintura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ pintura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ pintura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + pintura.getAncho()+ " cm\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + pintura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 2)
			    	{
			    		Escultura escultura;
			    		escultura = (Escultura) Inventario.getPiezaInventario("b");

	    	    		System.out.println("	Titulo: " + escultura.getTitulo()+"\n"+
	    	    							"		Autor: "+ escultura.getAutor() + "\n"+
	    	    							"		Año: " + escultura.getAnio()+ "\n"+
	    	    							"		Tipo: " + escultura.getTipo()+ "\n"+
	    	    							"		Tecnica: " + escultura.getTecnica() + "\n"+
	    	    							"		Estado: " + escultura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  escultura.getLugar()+ "\n"+
	    	    							"		Valor: $" + escultura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ escultura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ escultura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ escultura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ escultura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + escultura.getAncho()+ " cm\n"+
	    	    							"		Profundidad: " + escultura.getProfundidad()+ " cm\n"+
	    	    							"		Materiales: " + escultura.getMateriales()+ " \n"+
	    	    							"		Peso: " + escultura.getPeso()+ " kg\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + escultura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 3)
			    	{
			    		Impresion impresion;
			    		impresion = (Impresion) Inventario.getPiezaInventario("c");

	    	    		System.out.println("	Titulo: " + impresion.getTitulo()+"\n"+
	    	    							"		Autor: "+ impresion.getAutor() + "\n"+
	    	    							"		Año: " + impresion.getAnio()+ "\n"+
	    	    							"		Tipo: " + impresion.getTipo()+ "\n"+
	    	    							"		Tecnica: " + impresion.getTecnica() + "\n"+
	    	    							"		Estado: " + impresion.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  impresion.getLugar()+ "\n"+
	    	    							"		Valor: $" + impresion.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ impresion.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ impresion.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ impresion.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ impresion.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + impresion.getAncho()+ " cm\n"+
	    	    							"		Soporte: " + impresion.getSoporte()+ "\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + impresion.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 4)
			    	{
			    		Fotografia fotografia;
			    		fotografia= (Fotografia) Inventario.getPiezaInventario("d");

	    	    		System.out.println("	Titulo: " + fotografia.getTitulo()+"\n"+
	    	    							"		Autor: "+ fotografia.getAutor() + "\n"+
	    	    							"		Año: " + fotografia.getAnio()+ "\n"+
	    	    							"		Tipo: " + fotografia.getTipo()+ "\n"+
	    	    							"		Tecnica: " + fotografia.getTecnica() + "\n"+
	    	    							"		Estado: " + fotografia.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  fotografia.getLugar()+ "\n"+
	    	    							"		Valor: $" + fotografia.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ fotografia.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ fotografia.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ fotografia.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ fotografia.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + fotografia.getAncho()+ " cm\n"+
	    	    							"		¿Esta a color?: " + fotografia.getaColor()+ " \n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + fotografia.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 5)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("e");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 6)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("f");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
            }

            
            else if (opcion == 0) 
            {
                continuar = false;
            } 
            else 
            {
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }

            if (continuar==true) 
            {
                continuar = preguntarContinuar();
            }
        }
    }
    
    private static void menuCajero() 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("\n*** Bienvenido cajero ***\n");
            System.out.println("1. Mirar historia de un artista");
            System.out.println("2. Mirar la historia de una pieza");
            System.out.println("0. Salir");
            System.out.print("Por favor, elige una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) 
            {
            	boolean bucle = false;
            	Artista artista = null;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. Miguel Angelo");
		    		System.out.println("2. Leonardo da Vinci");
		    		
		    		System.out.print("Ingrese el numero del artista que decea ver: ");
			    	int opcionArtista = scanner.nextInt();
			    	scanner.nextLine();

			    	if (opcionArtista == 1)
			    	{
			    		artista = Galeria.getArtista("Michel Angelo");
			    		bucle = true;
			    	}
			    	else if (opcionArtista == 2)
			    	{
			    		artista = Galeria.getArtista("Leonardo da Vinci");
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
		    	historiaArtista(artista);
            } 
            else if (opcion == 2) 
            {
            	boolean bucle = false;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. La Mona Lisa");
		    		System.out.println("2. David");
		    		System.out.println("3. Campbell's Soup Cans");
		    		System.out.println("4. Moonrise, Hernandez, New Mexico");
		    		System.out.println("5. Toy Story");
		    		System.out.println("6. Brave");
		    		
		    		
		    		
		    		System.out.print("Ingrese el numero de la pieza que desea consultar: ");
			    	int opcionPieza = scanner.nextInt();

			    	if (opcionPieza == 1)
			    	{
			    		Pintura pintura;
			    		pintura = (Pintura) Inventario.getPiezaInventario("a");

	    	    		System.out.println("	Titulo: " + pintura.getTitulo()+"\n"+
	    	    							"		Autor: "+ pintura.getAutor() + "\n"+
	    	    							"		Año: " + pintura.getAnio()+ "\n"+
	    	    							"		Tipo: " + pintura.getTipo()+ "\n"+
	    	    							"		Movimiento Artistico: " + pintura.getMovimientoArtistico()+ "\n"+
	    	    							"		Tecnica: " + pintura.getTecnica() + "\n"+
	    	    							"		Estado: " + pintura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  pintura.getLugar()+ "\n"+
	    	    							"		Valor: $" + pintura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ pintura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ pintura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ pintura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ pintura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + pintura.getAncho()+ " cm\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + pintura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 2)
			    	{
			    		Escultura escultura;
			    		escultura = (Escultura) Inventario.getPiezaInventario("b");

	    	    		System.out.println("	Titulo: " + escultura.getTitulo()+"\n"+
	    	    							"		Autor: "+ escultura.getAutor() + "\n"+
	    	    							"		Año: " + escultura.getAnio()+ "\n"+
	    	    							"		Tipo: " + escultura.getTipo()+ "\n"+
	    	    							"		Tecnica: " + escultura.getTecnica() + "\n"+
	    	    							"		Estado: " + escultura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  escultura.getLugar()+ "\n"+
	    	    							"		Valor: $" + escultura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ escultura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ escultura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ escultura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ escultura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + escultura.getAncho()+ " cm\n"+
	    	    							"		Profundidad: " + escultura.getProfundidad()+ " cm\n"+
	    	    							"		Materiales: " + escultura.getMateriales()+ " \n"+
	    	    							"		Peso: " + escultura.getPeso()+ " kg\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + escultura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 3)
			    	{
			    		Impresion impresion;
			    		impresion = (Impresion) Inventario.getPiezaInventario("c");

	    	    		System.out.println("	Titulo: " + impresion.getTitulo()+"\n"+
	    	    							"		Autor: "+ impresion.getAutor() + "\n"+
	    	    							"		Año: " + impresion.getAnio()+ "\n"+
	    	    							"		Tipo: " + impresion.getTipo()+ "\n"+
	    	    							"		Tecnica: " + impresion.getTecnica() + "\n"+
	    	    							"		Estado: " + impresion.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  impresion.getLugar()+ "\n"+
	    	    							"		Valor: $" + impresion.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ impresion.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ impresion.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ impresion.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ impresion.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + impresion.getAncho()+ " cm\n"+
	    	    							"		Soporte: " + impresion.getSoporte()+ "\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + impresion.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 4)
			    	{
			    		Fotografia fotografia;
			    		fotografia= (Fotografia) Inventario.getPiezaInventario("d");

	    	    		System.out.println("	Titulo: " + fotografia.getTitulo()+"\n"+
	    	    							"		Autor: "+ fotografia.getAutor() + "\n"+
	    	    							"		Año: " + fotografia.getAnio()+ "\n"+
	    	    							"		Tipo: " + fotografia.getTipo()+ "\n"+
	    	    							"		Tecnica: " + fotografia.getTecnica() + "\n"+
	    	    							"		Estado: " + fotografia.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  fotografia.getLugar()+ "\n"+
	    	    							"		Valor: $" + fotografia.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ fotografia.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ fotografia.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ fotografia.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ fotografia.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + fotografia.getAncho()+ " cm\n"+
	    	    							"		¿Esta a color?: " + fotografia.getaColor()+ " \n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + fotografia.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 5)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("e");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 6)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("f");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
            }

            
            else if (opcion == 0) 
            {
                continuar = false;
            } 
            else 
            {
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }

            if (continuar==true) 
            {
                continuar = preguntarContinuar();
            }
        }
    }
    
    private static void menuComprador(Comprador comprador) 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("\n*** Bienvenido comprador ***\n");
            System.out.println("1. Realizar una compra");
            System.out.println("2. Participar en una subasta");
            System.out.println("3. Mirar historia de un artista");
            System.out.println("4. Mirar historia de una pieza");
            System.out.println("0. Salir");
            System.out.print("Por favor, elige una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) 
            {
                iniciarProcesoCompra(comprador);
            } 
            else if (opcion == 2) 
            {
                iniciarProcesoSubasta(comprador);
            }
            else if (opcion == 3) 
            {
            	boolean bucle = false;
            	Artista artista = null;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. Miguel Angelo");
		    		System.out.println("2. Leonardo da Vinci");
		    		
		    		System.out.print("Ingrese el numero del artista que decea ver: ");
			    	int opcionArtista = scanner.nextInt();
			    	scanner.nextLine();

			    	if (opcionArtista == 1)
			    	{
			    		artista = Galeria.getArtista("Michel Angelo");
			    		bucle = true;
			    	}
			    	else if (opcionArtista == 2)
			    	{
			    		artista = Galeria.getArtista("Leonardo da Vinci");
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
		    	historiaArtista(artista);
            }
            
            else if (opcion == 4) 
            {
            	boolean bucle = false;
		    	
		    	while (bucle == false) 
		    	{
		    		System.out.println("1. La Mona Lisa");
		    		System.out.println("2. David");
		    		System.out.println("3. Campbell's Soup Cans");
		    		System.out.println("4. Moonrise, Hernandez, New Mexico");
		    		System.out.println("5. Toy Story");
		    		System.out.println("6. Brave");
		    		
		    		
		    		
		    		System.out.print("Ingrese el numero de la pieza que desea consultar: ");
			    	int opcionPieza = scanner.nextInt();

			    	if (opcionPieza == 1)
			    	{
			    		Pintura pintura;
			    		pintura = (Pintura) Inventario.getPiezaInventario("a");

	    	    		System.out.println("	Titulo: " + pintura.getTitulo()+"\n"+
	    	    							"		Autor: "+ pintura.getAutor() + "\n"+
	    	    							"		Año: " + pintura.getAnio()+ "\n"+
	    	    							"		Tipo: " + pintura.getTipo()+ "\n"+
	    	    							"		Movimiento Artistico: " + pintura.getMovimientoArtistico()+ "\n"+
	    	    							"		Tecnica: " + pintura.getTecnica() + "\n"+
	    	    							"		Estado: " + pintura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  pintura.getLugar()+ "\n"+
	    	    							"		Valor: $" + pintura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ pintura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ pintura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ pintura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ pintura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + pintura.getAncho()+ " cm\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + pintura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 2)
			    	{
			    		Escultura escultura;
			    		escultura = (Escultura) Inventario.getPiezaInventario("b");

	    	    		System.out.println("	Titulo: " + escultura.getTitulo()+"\n"+
	    	    							"		Autor: "+ escultura.getAutor() + "\n"+
	    	    							"		Año: " + escultura.getAnio()+ "\n"+
	    	    							"		Tipo: " + escultura.getTipo()+ "\n"+
	    	    							"		Tecnica: " + escultura.getTecnica() + "\n"+
	    	    							"		Estado: " + escultura.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  escultura.getLugar()+ "\n"+
	    	    							"		Valor: $" + escultura.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ escultura.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ escultura.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ escultura.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ escultura.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + escultura.getAncho()+ " cm\n"+
	    	    							"		Profundidad: " + escultura.getProfundidad()+ " cm\n"+
	    	    							"		Materiales: " + escultura.getMateriales()+ " \n"+
	    	    							"		Peso: " + escultura.getPeso()+ " kg\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + escultura.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 3)
			    	{
			    		Impresion impresion;
			    		impresion = (Impresion) Inventario.getPiezaInventario("c");

	    	    		System.out.println("	Titulo: " + impresion.getTitulo()+"\n"+
	    	    							"		Autor: "+ impresion.getAutor() + "\n"+
	    	    							"		Año: " + impresion.getAnio()+ "\n"+
	    	    							"		Tipo: " + impresion.getTipo()+ "\n"+
	    	    							"		Tecnica: " + impresion.getTecnica() + "\n"+
	    	    							"		Estado: " + impresion.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  impresion.getLugar()+ "\n"+
	    	    							"		Valor: $" + impresion.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ impresion.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ impresion.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ impresion.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ impresion.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + impresion.getAncho()+ " cm\n"+
	    	    							"		Soporte: " + impresion.getSoporte()+ "\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + impresion.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 4)
			    	{
			    		Fotografia fotografia;
			    		fotografia= (Fotografia) Inventario.getPiezaInventario("d");

	    	    		System.out.println("	Titulo: " + fotografia.getTitulo()+"\n"+
	    	    							"		Autor: "+ fotografia.getAutor() + "\n"+
	    	    							"		Año: " + fotografia.getAnio()+ "\n"+
	    	    							"		Tipo: " + fotografia.getTipo()+ "\n"+
	    	    							"		Tecnica: " + fotografia.getTecnica() + "\n"+
	    	    							"		Estado: " + fotografia.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  fotografia.getLugar()+ "\n"+
	    	    							"		Valor: $" + fotografia.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ fotografia.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ fotografia.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ fotografia.getFechaVenta()+ "\n"+
	    	    							"		Alto: "+ fotografia.getAlto()+ " cm\n"+
	    	    							"		Ancho: " + fotografia.getAncho()+ " cm\n"+
	    	    							"		¿Esta a color?: " + fotografia.getaColor()+ " \n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + fotografia.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 5)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("e");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	
			    	else if (opcionPieza == 6)
			    	{
			    		Video video;
			    		video= (Video) Inventario.getPiezaInventario("f");

	    	    		System.out.println("	Titulo: " + video.getTitulo()+"\n"+
	    	    							"		Autor: "+ video.getAutor() + "\n"+
	    	    							"		Año: " + video.getAnio()+ "\n"+
	    	    							"		Tipo: " + video.getTipo()+ "\n"+
	    	    							"		Tecnica: " + video.getTecnica() + "\n"+
	    	    							"		Estado: " + video.getEstado() +"\n"+ 
	    	    							"		Lugar: "+  video.getLugar()+ "\n"+
	    	    							"		Valor: $" + video.getValor()+ "\n"+
	    	    							"		¿Ya se vendio?: "+ video.getVendida()+ "\n"+
	    	    							"		Precio de la venta: $"+ video.getPrecioVenta()+ "\n"+
	    	    							"		Fecha de la venta: "+ video.getFechaVenta()+ "\n"+
	    	    							"		Duracion: "+ video.getDuracion()+"\n"+
	    	    							"		Dueños (en orden de ultimo hasta el primero): " + video.getDueños());
			    		bucle = true;
			    	}
			    	else
			    		System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
		    	}
		    	
            }

            else if (opcion == 0) 
            {
                continuar = false;
            } 
            else 
            {
                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }

            if (continuar==true) 
            {
                continuar = preguntarContinuar();
            }
        }
    }
    
    private static boolean preguntarContinuar() 
    {
    	System.out.println("\n1. Realizar otra accion");
        System.out.println("2. Cerrar sesión");
        System.out.print("\n¿Deseas ver otro caso o terminar la operación? (1/2): ");
        int respuesta = scanner.nextInt();
        if (respuesta == 1) 
        {
            return true;
        } 
        else if (respuesta == 2) 
        {
            return false;
        } 
        else 
        {
            System.out.println("\nOpción no válida. Por favor, intenta de nuevo.");
            return preguntarContinuar();
        }
    } 
    
    public static void historiaArtista(Artista artista)
    {
    	Map<String, Map<String, Object>> piezasHechas = artista.getPiezasHechas();

    	List<String> mostrar = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> infoArtista : piezasHechas.entrySet()) 
        {
            String piezaInfo = "Pieza: "+infoArtista.getKey();
            Map<String, Object> infoPieza=infoArtista.getValue();

            piezaInfo += "\nFecha de creacion: "+infoPieza.get("fechaCreacion");
            boolean vendida = (boolean) infoPieza.get("vendida");

            if (vendida == true) 
            {
                piezaInfo+="\nFecha de venta:"+infoPieza.get("fechaVenta");
                piezaInfo+="\nPrecio de venta:"+infoPieza.get("precioVenta");
            } 
            else 
            {
                piezaInfo+="\nEsta pieza no ha sido vendida.";
            }

            mostrar.add(piezaInfo);
        }

        for (String info:mostrar) 
        {
            System.out.println(info);
        }
    	
    }
    

    
    private static void iniciarProcesoCompra(Comprador comprador) 
    {
    	
        Map<String, Pieza> catalogoCompleto = Inventario.getInventario();
        
        mostrarCatalogo(catalogoCompleto);
        
        Pieza pieza = null;
        
        boolean continuar = true;
        while (continuar) 
        {
	        System.out.print("\nIngresa el id de la pieza que quieras comprar(segun el catalogo mostrado): ");
	        String idr = scanner.next();
	        
	        boolean esta = Galeria.existePieza(idr);
	        
	        if (esta == true)
	        {
	        	continuar = false;
	        	pieza = Inventario.getPiezaInventario(idr);
	        }
	        else
	        {
	        	System.out.println("\nOpción no válida.");
	        }
        
        }
        
        boolean disponibilidad = pieza.getDisponibilidad();
        
        if (disponibilidad == true) 
        {
        	Administrador admin = Usuario.getAdmin();
        	double valor = pieza.getValor();
        	Cajero cajero = Usuario.getCajero();
        	
        	boolean verificarCompra = admin.verificarCompra(comprador, pieza, valor, cajero);

            if (verificarCompra == true) 
            {
            	Map<String, Double> mapMetodo = comprador.getMetodoPago();
            	double dineroActual = comprador.getDineroActual();
            	
            	boolean saldo = cajero.verificarSaldo(valor, comprador, mapMetodo, dineroActual);
            	
            	if (saldo == true)
            	{
	                Compra nuevaCompra = new Compra(pieza, comprador, valor);
	                
	                nuevaCompra.pasarCaja(comprador, pieza, "normal", admin, cajero);
	
		            double efectivo = mapMetodo.get("efectivo");
		            double tarjeta = mapMetodo.get("tarjetaCredito");
		            double transferencia = mapMetodo.get("transferenciaElectronica");
		            
		            System.out.println("Metodos de pago: \n");
		            System.out.println("1. Efectivo: " + (efectivo + ""));
		            System.out.println("2. Tarjeta de credito: " + (tarjeta + ""));
		            System.out.println("3. Transferencia electronica: " + (transferencia + ""));
		            
		            System.out.print("Ingrese el numero de la opcion deceada: ");
		            int metPagoNum = scanner.nextInt();
		            scanner.nextLine();
	                
		            String metodoPago = "";
		            
		            if (metPagoNum == 1)
		            {
		            	metodoPago = "efectivo";
		            }
		            
		            else if (metPagoNum == 2)
		            {
		            	metodoPago = "tarjetaCredito";
		            }
		            
		            else if (metPagoNum == 3)
		            {
		            	metodoPago = "transferenciaElectronica";
		            }
		            
	                cajero.realizarPago(comprador, pieza, valor, admin, cajero, metodoPago);
	                
	                System.out.println("Compra realizada con éxito. Gracias por tu compra.");
                } 
                else 
                {
                    System.out.println("No se pudo realizar la compra. Saldo insuficiente");
                }
            } 
            else 
            {
                System.out.println("La compra no ha sido aprobada por el administrador.");
                Compra.compraRechazada (comprador, pieza, admin);
            }
        } 
        else 
        {
            System.out.println("Lo sentimos, la pieza no está disponible o no existe.");
        }
    }

    private static void iniciarProcesoSubasta(Comprador comprador) 
    {
        Map<String, Pieza> piezasSubasta = Subasta.getSubasta();
        mostrarPiezasSubasta(piezasSubasta);
        
        Pieza pieza = null;
        
        boolean continuar = true;
        while (continuar) 
        {
	        System.out.print("\nIngresa el id de la pieza por la cual quieres ofertar: ");
	        String idr = scanner.next();
	        
	        boolean esta = Galeria.existePieza(idr);

	        if (esta == true)
	        {
	        	pieza = Inventario.getPiezaInventario(idr);
	        	boolean subasta = pieza.getSubasta();
	        	
	        	if (subasta == true)
	        	{
	        	continuar = false;
	        	}
	        	
	        	else
		        {
		        	System.out.println("\nOpción no válida.");
		        }
	        }
	        else
	        {
	        	System.out.println("\nOpción no válida.");
	        }
        
        }
        
        Administrador admin = Usuario.getAdmin();
    	Operador operador = Usuario.getOperador();
    	Cajero cajero = Usuario.getCajero();
    	
    	boolean verificado = true;
    	boolean continuarSubasta = true;
    	double valorOfertaAnterior = 0.0;
        while (continuarSubasta) 
        {
        	boolean valorMenor = true;
        	double valorOferta = 0.0;
            while (valorMenor) 
            {
		    	System.out.print("Ingrese el valor de la oferta: ");
		    	valorOferta = scanner.nextDouble();
		    	scanner.nextLine();
		    	
		    	double valorInicial = pieza.getValorInicialS();
		    	
		    	if (valorOferta >= valorInicial)
		    	{
		    		
		    		if (valorOfertaAnterior > valorOferta)
		    		{
		    			System.out.println("No puede ingresar un valor menor a la oferta anterior: " + valorOfertaAnterior);
		    		}
		    		else 
		    		{
		    		valorMenor = false;
		    		}
		    	}
		    	else
		    	{
		    		System.out.println("El valor ingresado es menor al valor de la oferta inicial");
		    	}
            }
            
	    	String mensaje = comprador.generarOfertasSubasta(pieza, valorOferta, operador, admin);
	    	
	    	if (mensaje.equals("Comprador no verificado"))
	    	{
	    		System.out.println("Comprador no verificado");
	    		continuarSubasta = false;
	    		verificado = false;
	    	}
	    	else if (mensaje.equals("Ha ganado la subasta"))
	    	{
	    		continuarSubasta = false;
	    	}
	    	else if (mensaje.equals("Oferta realizada con exito"))
	    	{
	    		
	    		double ofertaMin = pieza.getValorMinimoS();
	    		
	    		if (valorOferta < ofertaMin)
	    		{    			
	    			System.out.println("Generando oferta aleatoria...\n");
	    			valorOfertaAnterior = Subasta.ofertaAleatoria(valorOferta, ofertaMin, operador, pieza);
	    		}
	    		else
	    		{
	    			valorOfertaAnterior = valorOferta;
	    		}
	    		
	    		Map<String, Oferta> ofertas = operador.getOfertas();
	    		System.out.println("Ofertas realizadas hasta el momento: ");
	    		
	    		for (Map.Entry<String, Oferta> entry : ofertas.entrySet()) 
		    	{
		            Oferta oferta = entry.getValue();		
		            
		            Comprador compOferta = oferta.getComprador();
		            
		            if (compOferta == null)
		            {
			    		System.out.println("Turno: " + oferta.getTurno() + " - Valor de la oferta: " + oferta.getValorOferta() +
		                " - Comprador: Aleatorio" + " - Titulo de la pieza: " + pieza.getTitulo() + "\n");
		            }
		            else
		            {
		            	System.out.println("Turno: " + oferta.getTurno() + " - Valor de la oferta: " + oferta.getValorOferta() +
				        " - Comprador: " + compOferta.getNombre() + " - Titulo de la pieza: " + pieza.getTitulo() + "\n");
		            }
	            }
	    		
	    	}
	    	
        }
        
        Oferta ganador = Subasta.getGanador();
    	
    	Pieza piezaOferta = ganador.getPieza();
    	if (piezaOferta.equals(pieza)) 
    	{
        	double valor = ganador.getValorOferta();
        	
        	boolean verificarCompra = admin.verificarCompra(comprador, pieza, valor, cajero);

            if (verificarCompra == true) 
            {
            	Map<String, Double> mapMetodo = comprador.getMetodoPago();
            	double dineroActual = comprador.getDineroActual();
            	
            	boolean saldo = cajero.verificarSaldo(valor, comprador, mapMetodo, dineroActual);
            	
            	if (saldo == true)
            	{
	                Compra nuevaCompra = new Compra(pieza, comprador, valor);
	                
	                nuevaCompra.pasarCaja(comprador, pieza, "normal", admin, cajero);
	
		            double efectivo = mapMetodo.get("efectivo");
		            double tarjeta = mapMetodo.get("tarjetaCredito");
		            double transferencia = mapMetodo.get("transferenciaElectronica");
		            
		            System.out.println("Metodos de pago: \n");
		            System.out.println("1. Efectivo: " + (efectivo + ""));
		            System.out.println("2. Tarjeta de credito: " + (tarjeta + ""));
		            System.out.println("3. Transferencia electronica: " + (transferencia + ""));
		            
		            System.out.print("Ingrese el numero de la opcion deceada: ");
		            int metPagoNum = scanner.nextInt();
		            scanner.nextLine();
	                
		            String metodoPago = "";
		            
		            if (metPagoNum == 1)
		            {
		            	metodoPago = "efectivo";
		            }
		            
		            else if (metPagoNum == 2)
		            {
		            	metodoPago = "tarjetaCredito";
		            }
		            
		            else if (metPagoNum == 3)
		            {
		            	metodoPago = "transferenciaElectronica";
		            }
		            
	                cajero.realizarPago(comprador, pieza, valor, admin, cajero, metodoPago);
	                
	                System.out.println("Compra realizada con éxito. Gracias por su compra.");
                } 
                else 
                {
                    System.out.println("No se pudo realizar la compra. Saldo insuficiente");
                }
            } 
            else 
            {
                System.out.println("La compra no ha sido aprobada por el administrador.");
                Compra.compraRechazada (comprador, pieza, admin);
            }
    	} 
    	else
    	{
    		System.out.println("No se ha podido completar la subasta para la pieza seleccionada.");
        } 
    	
    	operador.setOfertas();
    }

    private static void mostrarPiezasSubasta(Map<String, Pieza> piezasSubasta) 
    {
        if (piezasSubasta.isEmpty()) {
            System.out.println("Actualmente no hay piezas disponibles para subasta.");
        } 
        else 
        {
            System.out.println("Piezas Disponibles para Subasta:");
            for (Pieza pieza : piezasSubasta.values())
            {
                System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
                " - Autor: " + pieza.getAutor() + " - Valor Mínimo: $" + pieza.getValorMinimoS());
            }
        }
    }


    private static void mostrarCatalogo(Map<String, Pieza> catalogoCompleto) 
    {
;
        if (catalogoCompleto.isEmpty()) 
        {
            System.out.println("Actualmente no hay piezas disponibles en el catálogo.");
        } 
        else 
        {
            System.out.println("Catálogo de Piezas Disponibles:");
            for (Pieza pieza : catalogoCompleto.values()) 
            {
                System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
                " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
                " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
            }
        }
    }
}


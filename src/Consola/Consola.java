package Consola;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;

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
import Usuario.Comprador;
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
    	
    	inicioSecion();
    }

    private static void inicioSecion() throws IOException
    {
    	boolean continuar = true;
    	while (continuar) 
    	{
    	
	    	System.out.println("*** Inicio de secion ***\n");
	    	
	    	System.out.println("1. Iniciar secion");
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
		        	menuComprador();
		        }
		        
		        else if (tipo.equals("Propietario"))
		        {
		        	System.out.println("\nEl usuario ingresado es un propietario, para esta entrega no cuenta con consola");
		        	System.out.println("Ingrese otro usuario\n");
		        	inicioSecion();
	
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
        	
            System.out.println("*** Bienvenida Administradora ***\n");

            System.out.println("1. Cambiar el estado de una pieza");
            System.out.println("2. Cambiar la disponibilidad de una pieza");
            System.out.println("3. Agregar una pieza al inventario");
            System.out.println("4. Obtener el historial de piezas de un propietario");
            System.out.println("5. Obtener el las piezas actuales de un propietario");
            System.out.println("6. Obtener el valor de las piezas actuales de un propietario");
            System.out.println("7. Mirar historia de un artista");
            System.out.println("8. Cerrar secion");
            
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
                    
                    nuevaPieza = new Pintura(id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS,tipo,
    						alto, ancho, movimientoArtistico, instalacion);
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
                    
                    nuevaPieza = new Escultura (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, profundidad, materiales, peso, instalacion, electricidad);
    	            
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
                    
                    nuevaPieza = new Impresion (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, soporte, instalacion);
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
                    
                    nuevaPieza = new Fotografia (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						alto, ancho, aColor, instalacion);
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
                    
                    nuevaPieza = new Video (id, tecnica, autor, titulo, anio, lugar, estado, 
    						disponibilidad, fechaLimite,valor, consignacion, devolucion, subasta, valorMinimoS, valorInicialS, tipo,
    						duracion, electricidad);
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
		    		System.out.print("Ingrese el login del propietario: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El propietario no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Propietario propietario = (Propietario) Galeria.getUsuario(login);
		    	Map<String,Pieza> piezas = admin.piezasCompradasProp(propietario);
		    	
		    	if (piezas == null)
		    	{
		    		System.out.println("El propietario no tiene historial");
		    	}
		    	
		    	else
		    	{
			    	System.out.println("\nEste es el historial del propietario: \n");
			    	
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
		    		System.out.print("Ingrese el login del propietario: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El propietario no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Propietario propietario = (Propietario) Galeria.getUsuario(login);
		    	List<Pieza> piezas = admin.piezasDuenioProp(propietario);
		    	
		    	if (piezas == null)
		    	{
		    		System.out.println("El propietario no tiene piezas actualmente");
		    	}
		    	
		    	else
		    	{
			    	System.out.println("\nEstas son las piezas actuales del propietario: \n");
			    	
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
//            	("6. Obtener el valor de las piezas actuales de un propietario")
            	
            	boolean esta = false;
		    	String login = "";
		    	
		    	while (esta == false) 
		    	{
		    		System.out.print("Ingrese el login del propietario: ");
			    	String loginA = scanner.nextLine();
			    	login = loginA;
			    	esta = Galeria.existeUsuario(loginA);
			    	if (esta == false)
			    		System.out.println("El propietario no existe, vuelva a intentarlo");
			    	else
			    		esta =true;
		    	}
		    	
		    	Propietario propietario = (Propietario) Galeria.getUsuario(login);
		    	double valor = admin.valorColleccion(propietario);
		    	
		    	System.out.println("El valor de colleccion del propietario es: " + (valor + ""));
            } 
            
            else if (opcion == 7) 
            {
            	boolean esta = false;
            	Artista artista = null;
		    	
		    	while (esta == false) 
		    	{
		    		System.out.println("1. Miguel Angelo");
		    		System.out.println("2. Leonardo da Vinci");
		    		
		    		System.out.print("Ingrese el numero del artista que decea ver: ");
			    	int opcionArtista = scanner.nextInt();

			    	if (opcionArtista == 1)
			    		artista = Galeria.getArtista("Miguel Angelo");
			    	else if (opcionArtista == 2)
			    		artista = Galeria.getArtista("Leonardo da Vinci");
		    	}
		    	
		    	
            }
            
            else if (opcion == 8) 
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
//    	boolean continuar = true;
//        while (continuar) 
//        {
//        	
//            System.out.println("*** BIENVENIDA A LA GALERÍA DE ARTE ***\n");
//            System.out.println("1. Ver catálogo e iniciar proceso de compra");
//            System.out.println("2. Participar en una subasta");
//            System.out.println("3. Salir");
//            System.out.print("Por favor, elige una opción: ");
//            int opcion = scanner.nextInt();
//
//            if (opcion == 1) 
//            {
//                iniciarProcesoCompra();
//            } 
//            else if (opcion == 2) 
//            {
//                iniciarProcesoSubasta();
//            } 
//            else if (opcion == 3) 
//            {
//                System.out.println("Gracias por visitar nuestra galería. ¡Hasta pronto!");
//                continuar = false;
//            } 
//            else 
//            {
//                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
//            }
//
//            if (continuar==true) 
//            {
//                continuar = preguntarContinuar();
//            }
//        }
    }
    
    private static void menuCajero() 
    {
//    	boolean continuar = true;
//        while (continuar) 
//        {
//        	
//            System.out.println("*** BIENVENIDA A LA GALERÍA DE ARTE ***\n");
//            System.out.println("1. Ver catálogo e iniciar proceso de compra");
//            System.out.println("2. Participar en una subasta");
//            System.out.println("3. Salir");
//            System.out.print("Por favor, elige una opción: ");
//            int opcion = scanner.nextInt();
//
//            if (opcion == 1) 
//            {
//                iniciarProcesoCompra();
//            } 
//            else if (opcion == 2) 
//            {
//                iniciarProcesoSubasta();
//            } 
//            else if (opcion == 3) 
//            {
//                System.out.println("Gracias por visitar nuestra galería. ¡Hasta pronto!");
//                continuar = false;
//            } 
//            else 
//            {
//                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
//            }
//
//            if (continuar==true) 
//            {
//                continuar = preguntarContinuar();
//            }
//        }
    }
    
    private static void menuComprador() 
    {
//    	boolean continuar = true;
//        while (continuar) 
//        {
//        	
//            System.out.println("*** BIENVENIDA A LA GALERÍA DE ARTE ***\n");
//            System.out.println("1. Ver catálogo e iniciar proceso de compra");
//            System.out.println("2. Participar en una subasta");
//            System.out.println("3. Salir");
//            System.out.print("Por favor, elige una opción: ");
//            int opcion = scanner.nextInt();
//
//            if (opcion == 1) 
//            {
//                iniciarProcesoCompra();
//            } 
//            else if (opcion == 2) 
//            {
//                iniciarProcesoSubasta();
//            } 
//            else if (opcion == 3) 
//            {
//                System.out.println("Gracias por visitar nuestra galería. ¡Hasta pronto!");
//                continuar = false;
//            } 
//            else 
//            {
//                System.out.println("Opción no válida. Por favor, intenta de nuevo.");
//            }
//
//            if (continuar==true) 
//            {
//                continuar = preguntarContinuar();
//            }
//        }
    }
    
    private static void menuPropietario() 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("*** BIENVENIDA A LA GALERÍA DE ARTE ***\n");

            if (continuar==true) 
            {
                continuar = preguntarContinuar();
            }
        }
    }
    
    private static boolean preguntarContinuar() 
    {
    	System.out.println("\n1. Ver otro caso");
        System.out.println("2. terminar proceso");
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
    	
    }
    
    private static void iniciarProcesoCompra() 
    {
//    	
//        Map<String, Pieza> catalogoCompleto = Inventario.getInventario();
//        
//        mostrarCatalogo();
//        
//        System.out.print("\nEscoge el id de la pieza que quieras(segun el catalogo mostrado): ");
//        String idr = scanner.next();
//        
//        
//        Pieza piezaSeleccionada = catalogoCompleto.get(idr);
//        
//        // Datos ficticios del comprador para efectos practicos :D
//        
//        Comprador compradorActual = new Comprador("usuario123", "contraseña", "ID123", "Usuario", "correo@ejemplo.com", 5551234, "Comprador", true, 23.0, 5.0);
//        if (piezaSeleccionada != null && piezaSeleccionada.getDisponibilidad()) 
//        {
//            // Datos ficticios del Administrador para efectos practicos :D
//        	
//            Administrador admin = new Administrador("admin123", "adminpass", "ADM001", "Admin Nombre", "admin@ejemplo.com", 5554321, "Administrador", true);
//            if (admin.verificarCompra(compradorActual, piezaSeleccionada, piezaSeleccionada.getValor())) 
//            {
//                Compra nuevaCompra = new Compra(piezaSeleccionada, compradorActual, piezaSeleccionada.getValor());
//                nuevaCompra.pasarCaja(compradorActual, piezaSeleccionada, "normal");
//                
//                // Datos ficticios del Cajero para efectos practicos :D
//                
//                Cajero cajero = new Cajero("cajero123", "cajeropass", "CAJ001", "Cajero Nombre", "cajero@ejemplo.com", 5556789, "Cajero", true);
//                if (cajero.verificarSaldo(piezaSeleccionada.getValor(), compradorActual, compradorActual.getMetodoPago(), compradorActual.getDineroActual()) && cajero.verificarLimite(piezaSeleccionada.getValor(), compradorActual)) 
//                {
//                    cajero.realizarPago(piezaSeleccionada.getValor(), compradorActual, compradorActual.getMetodoPago(),compradorActual.getDineroActual(), piezaSeleccionada);
//                    admin.agregarPieza(compradorActual, piezaSeleccionada);
//                    System.out.println("Compra realizada con éxito. Gracias por tu compra.");
//                } 
//                else 
//                {
//                    System.out.println("No se pudo realizar la compra. Verifica tu saldo o límite de compra.");
//                }
//            } 
//            else 
//            {
//                System.out.println("La compra no ha sido aprobada por el administrador.");
//            }
//        } 
//        else 
//        {
//            System.out.println("Lo sentimos, la pieza no está disponible o no existe.");
//        }
    }

    private static void iniciarProcesoSubasta() 
    {
//        // Datos ficticios para efectos practicos >:(
//        Comprador compradorActual = new Comprador("usuario123", "contraseña123", "ID123", "Juan Perez", "juan.perez@example.com", 5551234, "Comprador", true, 100000.0, 50000.0);
//
//        Map<String, Pieza> piezasSubasta = Subasta.getSubasta();
//        mostrarPiezasSubasta(piezasSubasta);
//        System.out.println(Subasta.getSubasta());
//
//        String idPiezaSeleccionada = "PIEZA001";
//        Pieza piezaSeleccionada = piezasSubasta.get(idPiezaSeleccionada);
//
//        if (piezaSeleccionada != null && piezaSeleccionada.getSubasta()) 
//        {
//            double valorOferta = 30000.0;
//            String resultadoOferta = Subasta.generarOferta(compradorActual, piezaSeleccionada, valorOferta);
//            System.out.println(resultadoOferta);
//
//            Oferta ofertaGanadora = Subasta.getGanador();
//            if (ofertaGanadora != null && ofertaGanadora.getPieza().equals(piezaSeleccionada)) 
//            {
//                Compra compra = new Compra(piezaSeleccionada, ofertaGanadora.getComprador(), ofertaGanadora.getValorOferta());
//                compra.pasarCaja(ofertaGanadora.getComprador(), piezaSeleccionada, "subasta");
//                System.out.println("Compra realizada con éxito. La pieza ha sido adjudicada a: " + ofertaGanadora.getComprador().getNombre());
//            } 
//            else 
//            {
//                System.out.println("No se ha podido completar la subasta para la pieza seleccionada.");
//            }
//        } 
//        else 
//        {
//            System.out.println("La pieza seleccionada no está disponible para subasta o no existe.");
//        }
    }
//
//    private static void mostrarPiezasSubasta(Map<String, Pieza> piezasSubasta) 
//    {
//        if (piezasSubasta.isEmpty()) {
//            System.out.println("Actualmente no hay piezas disponibles para subasta.");
//        } 
//        else 
//        {
//            System.out.println("Piezas Disponibles para Subasta:");
//            for (Pieza pieza : piezasSubasta.values())
//            {
//                System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
//                " - Autor: " + pieza.getAutor() + " - Valor Mínimo: $" + pieza.getValorMinimoS());
//            }
//        }
//    }
//
//
    private static void mostrarCatalogo() 
    {
        Map<String, Pieza> catalogoCompleto = Inventario.getInventario();
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


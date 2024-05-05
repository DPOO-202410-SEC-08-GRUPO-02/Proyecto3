package Consola;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Compra.Compra;
import Inventario.Inventario;
import Inventario.Pieza;
import Subasta.Oferta;
import Subasta.Subasta;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;
import Usuario.Usuario;

import java.io.IOException;

public class Consola 
{

    private static Scanner scanner = new Scanner(System.in);
    private static Scanner scanner2 = new Scanner(System.in);

    public static void main(String[] args) throws IOException 
    {
    	CargadorGaleria.cargarInventario("./datos/Inventario.json");
    	CargadorGaleria.cargarArtista("./datos/Artistas.json");
    	CargadorGaleria.cargarUsuario("./datos/Usuarios.json");
    	
    	inicioSecion();
    }

    private static void inicioSecion()
    {
    	System.out.println("*** Inicio de secion ***\n");
    	
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
        	menuAdministrador();
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

    private static void menuAdministrador() 
    {
    	boolean continuar = true;
        while (continuar) 
        {
        	
            System.out.println("*** Bienvenida Administradora ***\n");

            System.out.println("1. Cambiar el estado de una compra");
            System.out.println("2. Agregar una pieza al inventario");
            System.out.println("3. Obtener el historial de piezas de un propietario");
            System.out.println("4. Obtener el las piezas actuales de un propietario");
            System.out.println("5. Obtener el valor de las piezas actuales de un propietario");
            System.out.println("6. Salir");
            
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
    	    		System.out.println(pieza);
    	    	}
            } 
            else if (opcion == 2) 
            {
                iniciarProcesoSubasta();
            } 
            else if (opcion == 3) 
            {
                iniciarProcesoSubasta();
            } 
            else if (opcion == 4) 
            {
                iniciarProcesoSubasta();
            } 
            else if (opcion == 5) 
            {
                iniciarProcesoSubasta();
            } 
            else if (opcion == 6) 
            {
                System.out.println("Gracias por visitar nuestra galería. ¡Hasta pronto!");
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
//    private static void mostrarCatalogo() 
//    {
//        Map<String, Pieza> catalogoCompleto = Inventario.getInventario();
//        if (catalogoCompleto.isEmpty()) 
//        {
//            System.out.println("Actualmente no hay piezas disponibles en el catálogo.");
//        } 
//        else 
//        {
//            System.out.println("Catálogo de Piezas Disponibles:");
//            for (Pieza pieza : catalogoCompleto.values()) 
//            {
//                System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
//                " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
//                " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor());
//            }
//        }
//    }
}


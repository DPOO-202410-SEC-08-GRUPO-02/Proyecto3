package GaleraTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Compra.Compra;
import Subasta.Oferta;
import Subasta.Subasta;
import Inventario.Escultura;
import Inventario.Fotografia;
import Inventario.Pieza;
import Inventario.Video;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;
import Usuario.Operador;

class GaleriaTest {
	private Comprador comprador;
	private Fotografia fotografia;
	private Video video;
	private Administrador administrador;
	private Cajero cajero;
	private Operador operador;
	
	@BeforeEach
	void setUp() throws Exception {
		/* Antes de cada prueba vamos a hacer la carga de datos y sacar especificas piezas y un cliente para hacer las pruebas*/
    	CargadorGaleria.cargarInventario("./datos/Inventario.json");
    	CargadorGaleria.cargarArtista("./datos/Artistas.json");
    	CargadorGaleria.cargarUsuario("./datos/Usuarios.json");
    	CargadorGaleria.cargarPasarelas("./datos/Pasarelas.json");
    	
    	comprador = (Comprador) Galeria.getUsuario("German");
    	fotografia= (Fotografia) Galeria.getPiezaSubasta("d");
    	video= (Video) Galeria.getPiezaInventario("f");
    	administrador= (Administrador) Galeria.getUsuario("Majo");
    	cajero= (Cajero) Galeria.getUsuario("Kevinsitp");
    	operador= (Operador) Galeria.getUsuario("Scru");
	
	}


	@Test
	@DisplayName("Comprobar Carga y salva de datos funciona")

	public void CargaFuncionando() throws IOException {
		/* Esta prueba es para ver que todo haya cargado bien para las pruebas*/
		/*Comprador*/
		assertEquals("German", comprador.getNombre());
		assertEquals( 102.2, comprador.getDineroActual());
		assertEquals( 75, comprador.getLimiteCompras());
		
		/*Administrador*/
		assertEquals("Maria", administrador.getNombre());
		assertEquals( true, administrador.getAccesoGaleria());
		
		/*Cajero*/
		assertEquals("Kevin", cajero.getNombre());
		assertEquals( false, cajero.getAccesoGaleria());
		
		/*Operador*/
		assertEquals("Santi", operador.getNombre());
		assertEquals( false, operador.getAccesoGaleria());
		assertEquals( 0, (operador.getOfertas()).size());
		
		/*Pieza en Subasta*/
		assertEquals("Moonrise, Hernandez, New Mexico", fotografia.getTitulo());
		assertEquals("Ansel Adams", fotografia.getAutor());
		/*Verificar que la pieza si este en una subasta*/
		assertEquals( true, fotografia.getSubasta());
		
		/*Pieza en exhibicion para comprar*/
		assertEquals("Brave", video.getTitulo());
		assertEquals("Pixar Animation Studios", video.getAutor());
		assertEquals( 23.5, video.getValor());
		
		/*Salvar galeria (para el coverage)*/
		CargadorGaleria.salvarInventario("./datos/Inventario.json");
		CargadorGaleria.salvarArtistas("./datos/Artistas.json");
		CargadorGaleria.salvarUsuario("./datos/Usuarios.json");
		CargadorGaleria.salvarPasarelas("./datos/Pasarelas.json");
		
	}
	
	
	@Test
	@DisplayName("Administrador verifica a un usuario que no esta verificado")
	
	public void UsuarioNoVerificado() {
		/*Creamos un usuario nuevo en la galeria que quiere ser un comprador por lo que dijita sus datos y se crea su usuario que sera un comprador no verificado*/
		Map<String,Pieza> historialPiezas= new HashMap<String,Pieza>();
		Map<String,Pieza> infoCompras= new HashMap<String,Pieza>();
		List<Pieza> piezasActuales= new ArrayList<Pieza>( );
		List<String> pasarelas= new ArrayList<String>( );
		pasarelas.add("Paypal");
		Map<String,Double> metodoPago= new HashMap<String,Double>();
		metodoPago.put("tarjetaCredito", 10.5);
		metodoPago.put("efectivo", 30.2);
		metodoPago.put("tarjetaCredito", 11.5);
		Comprador compradorNoVerificado= new Comprador("Andres", "1234", "z", "Andres", "Andre@gmail.com", 789, "comprador", false, 52.2, 0.0, metodoPago, infoCompras ,historialPiezas,piezasActuales,pasarelas, 456321789, "Disponible","1234-5678-8765-4321") ;
		
		/*Comprobamos que no tenga los atributos de un comprador verificado*/
		assertEquals(false, compradorNoVerificado.getVerificado());
		assertEquals(0.0, compradorNoVerificado.getLimiteCompras());
		
		/*Ahora el administrador vera que el usuario no esta verificado por lo que lo verificara el mismo*/
		administrador.verificarUsuario(compradorNoVerificado);

		/*Por ultimo comprobamos que el administrador haya verificado correctamente*/
		assertEquals(true, compradorNoVerificado.getVerificado());
		assertEquals(35.0, compradorNoVerificado.getLimiteCompras());

	}
	
	
	
	@Test
	@DisplayName("Cajero atiende a un comprador que quiere devolver una pieza")
	
	public void DevolverPieza() {
		/*Sacamos la pieza que el comprador quiere devolver*/
		List<Pieza>piezasEnPosesion=comprador.getPiezasActuales();
		Escultura escultura=(Escultura) piezasEnPosesion.get(0);
		
		/*El cajero recibe la pieza y hace el proceso para devolver una pieza con su reembolso*/
		/*Diremos que se le reembolsara en efectivo porque cuando compro la pieza la pago en efectivo*/
		cajero.realizarDevolucion(comprador, escultura,"efectivo", administrador );
		 
		/*Verificamos que la pieza si se haya devolvido mediante atributos*/
		assertEquals(true, escultura.getDevolucion());
		assertEquals("Bodega", escultura.getEstado());
		assertEquals(true, escultura.getDisponibilidad());
		assertEquals(false, escultura.getVendida());
		assertEquals("n/a", escultura.getFechaVenta());
		assertEquals(0.0, escultura.getPrecioVenta());
		
		/*Verificamos que el comprador no tenga piezas en su posesion (ya que devolvio su unica pieza)*/
		int cuantasPiezasActuales=(comprador.getPiezasActuales()).size();
		assertEquals(0, cuantasPiezasActuales);
		/*Por ultimo verificamos que se le hacya hecho la devolucion del dinero al comprador*/
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double efectivo= metodoPagoMap.get("efectivo");
		double dineroActual= comprador.getDineroActual();
		assertEquals(55.2, efectivo);
		assertEquals(127.2, dineroActual);
	}
	
	@Test
	@DisplayName("Comprador quiere comprar un video (compra normal)")
	
	public void CompraNormal() {
		/*Vamos a decir que el comprador quiere pagar su compra con efectivo*/
		Compra.pasarCaja(comprador, video, "normal", administrador, cajero, "efectivo",false);
		
		/*Confirmamos que la operacion de resta de dinero este bien hecha*/
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double dineroActual= comprador.getDineroActual();
		double efectivo= metodoPagoMap.get("efectivo");
		assertEquals(6.7, efectivo,0.1);
		assertEquals(78.7, dineroActual,0.1);
		
		/*Confirmamos que la pieza se halla editado*/
		List<String> dueños=video.getDueños();
		String ultimoDueño=dueños.get(0);
		assertEquals("German", ultimoDueño);
		assertEquals("Vendida", video.getEstado());
		assertEquals(false, video.getDisponibilidad());
		assertEquals(true,video.getVendida());
		assertEquals(23.5, video.getPrecioVenta());
		
		/*Confirmamos que la pieza se haya añadido al usuario*/
		Map<String,Pieza> historialPiezas= comprador.getHistorialPiezas();
		assertEquals(video,historialPiezas.get("16-10-23"));
		List<Pieza> piezasActuales= comprador.getPiezasActuales();
		int posicion= (piezasActuales.size())-1;
		assertEquals(video, piezasActuales.get(posicion));
	}
	
	@Test
	@DisplayName("Comprador quiere participar y ganar una subasta (subasta)")
	
	public void Subasta() {
		/*Haremos que el usuario haga ofertas por la pieza que quiere la cual sera la fotografia*/
		double oferta1=12.0;
		Subasta.generarOferta(comprador,fotografia, oferta1,operador,administrador);
		/*Sacamos el valor minimo de venta de la pieza para que el sistema (simulando a otros compradores) haga una oferta*/
		double valorMin= fotografia.getValorMinimoS();
		double ofertaSistema= Subasta.ofertaAleatoria(oferta1, valorMin, operador, fotografia);
		/*Comprobamos que no se lleva la pieza y despues el comprador hace otra oferta*/
		Subasta.generarOferta(comprador,fotografia, ofertaSistema,operador,administrador);
		/*El comprador hace otra oferta*/
		double oferta2=13.5;
		Subasta.generarOferta(comprador,fotografia, oferta2,operador,administrador);
		/*Sistema hace otra oferta*/
		double ofertaSistema2= Subasta.ofertaAleatoria(oferta2, valorMin, operador, fotografia);
		Subasta.generarOferta(comprador,fotografia, ofertaSistema2,operador,administrador);
		/*El comprador hace otra oferta*/
		double oferta3=17.0;
		Subasta.generarOferta(comprador,fotografia, oferta3,operador,administrador);
		
		/*Miramos el valor minimo de la obra*/
		assertEquals(14, valorMin);
		/*Como la oferta del comprador es mayor al valor minimo para vender en subasta entonces el comprador de la llevara por ese precio*/
		Oferta ganador = Subasta.getGanador();
		Comprador ganadorComprador=ganador.getComprador();
		Fotografia ganadorPieza=(Fotografia) ganador.getPieza();
		double ganadorValor= ganador.getValorOferta();
		/* Vamos a comparar si los resultados del ganador tienen coherencia con los atributos de GaleriaTest (deben ser los mismos)*/
		assertEquals(comprador,ganadorComprador);
		assertEquals(fotografia,ganadorPieza);
		assertEquals(oferta3,ganadorValor);
		/*Aqui acaba el proceso de la subasta por lo que empieza el proceso de realizar el pago de la pieza (compra normal)*/
		
		/*Vamos a decir que el comprador quiere pagar su pieza de la subasta con tarjeta de credito*/
		/*Tambien vamos a pasarle el atributo original de comprador y el atributo original de fotografia*/
		Compra.pasarCaja(comprador, fotografia, "subasta", administrador, cajero, "tarjetaCredito",false);
		
		/*Confirmamos que la operacion de resta de dinero este bien hecha*/
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double dineroActual= comprador.getDineroActual();
		double tarjetaCredito= metodoPagoMap.get("tarjetaCredito");
		assertEquals(43.5, tarjetaCredito,0.1);
		assertEquals(85.2, dineroActual,0.1);
		/*El valor original de la fotografia era 15 pero como la oferta que hicimos fue 17, entonces toda la compra se hizo por 17 lo cual muestran estos assertequals*/
		
		/*Confirmamos que la pieza se halla editado*/
		List<String> dueños=fotografia.getDueños();
		String ultimoDueño=dueños.get(0);
		assertEquals("German", ultimoDueño);
		assertEquals("Vendida", fotografia.getEstado());
		assertEquals(false, fotografia.getDisponibilidad());
		assertEquals(true,fotografia.getVendida());
		assertEquals(17.0, fotografia.getPrecioVenta());
		
		/*Confirmamos que la pieza se haya añadido al usuario*/
		Map<String,Pieza> historialPiezas= comprador.getHistorialPiezas();
		assertEquals(fotografia,historialPiezas.get("16-10-23"));
		List<Pieza> piezasActuales= comprador.getPiezasActuales();
		int posicion= (piezasActuales.size())-1;
		assertEquals(fotografia, piezasActuales.get(posicion));
		
		/*Por ultimo limpiamos el mapa de ofertas del operador para tener todo listo para una siguiente subasta*/
		operador.setOfertas();
		assertEquals(true,(operador.getOfertas().isEmpty()));
	}
}

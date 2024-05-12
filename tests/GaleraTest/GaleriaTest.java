package GaleraTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Inventario.Escultura;
import Inventario.Fotografia;
import Inventario.Pieza;
import Inventario.Video;
import Usuario.Administrador;
import Usuario.Comprador;

class GaleriaTest {
	private Comprador comprador;
	private Fotografia fotografia;
	private Video video;
	private Administrador administrador;
	
	@BeforeEach
	void setUp() throws Exception {
		/* Antes de cada prueba vamos a hacer la carga de datos y sacar especificas piezas y un cliente para hacer las pruebas*/
    	CargadorGaleria.cargarInventario("./datos/Inventario.json");
    	CargadorGaleria.cargarArtista("./datos/Artistas.json");
    	CargadorGaleria.cargarUsuario("./datos/Usuarios.json");
    	
    	comprador = (Comprador) Galeria.getUsuario("German");
    	fotografia= (Fotografia) Galeria.getPiezaSubasta("d");
    	video= (Video) Galeria.getPiezaInventario("f");
    	administrador= (Administrador) Galeria.getUsuario("Majo");
	}


	@Test
	@DisplayName("Comprobar Carga de datos funciona")

	public void CargaFuncionando() {
		/* Esta prueba es para ver que todo haya cargado bien para las pruebas*/
		/*Comprador*/
		assertEquals("German", comprador.getNombre());
		assertEquals( 52.2, comprador.getDineroActual());
		assertEquals( 25, comprador.getLimiteCompras());
		
		/*Administrador*/
		assertEquals("Maria", administrador.getNombre());
		assertEquals( true, administrador.getAccesoGaleria());
		
		/*Pieza en Subasta*/
		assertEquals("Moonrise, Hernandez, New Mexico", fotografia.getTitulo());
		assertEquals("Ansel Adams", fotografia.getAutor());
		/*Verificar que la pieza si este en una subasta*/
		assertEquals( true, fotografia.getSubasta());
		
		/*Pieza en exhibicion para comprar*/
		assertEquals("Brave", video.getTitulo());
		assertEquals("Pixar Animation Studios", video.getAutor());
		assertEquals( 23.5, video.getValor());
		
	}
	
	
	@Test
	@DisplayName("Verificar a un usuario que no esta verificado")
	
	public void UsuarioNoVerificado() {
		comprador.setVerificado(false);
		comprador.setLimiteCompras(0);
		assertEquals(false, comprador.getVerificado());
		assertEquals(0, comprador.getLimiteCompras());
		
		/*Ahora llamamos al administrador para que verifique a el usuario*/
		administrador.verificarUsuario(comprador);

		assertEquals(true, comprador.getVerificado());
		assertEquals(35.0, comprador.getLimiteCompras());

	}
	
	
	
	@Test
	@DisplayName("Usuario quiere devolover una pieza")
	
	public void DevolverPieza() {
		/*Sacamos la pieza que el comprador quiere devolver*/
		List<Pieza>piezasEnPosesion=comprador.getPiezasActuales();
		Escultura escultura=(Escultura) piezasEnPosesion.get(0);
		/*El administrador hace el proceso para devolver una pieza*/
		administrador.verificarDevolucion(comprador, escultura);
		 
		/*Verificamos que la pieza si se haya devolvido mediante atributos*/
		assertEquals(true, escultura.getDevolucion());
		assertEquals("Bodega", escultura.getEstado());
		assertEquals(true, escultura.getDisponibilidad());
		assertEquals(false, escultura.getVendida());
		assertEquals("n/a", escultura.getFechaVenta());
		assertEquals(0.0, escultura.getPrecioVenta());
		
		/*Verificamos que el comprador no tenga piezas en su posesion (devolvio su unica pieza)*/
		int cuantasPiezasActuales=(comprador.getPiezasActuales()).size();
		assertEquals(0, cuantasPiezasActuales);
		/*Por ultimo verificamos que se le hacya hecho la devolucion del dinero al comprador*/
		HashMap<String, Double> metodoPagoMap= (HashMap<String, Double>) comprador.getMetodoPago();
		double efectivo= metodoPagoMap.get("efectivo");
		double dineroActual= comprador.getDineroActual();
		assertEquals(55.2, efectivo);
		assertEquals(77.2, dineroActual);
	}

}

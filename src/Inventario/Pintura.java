package Inventario;

import java.util.List;

public class Pintura extends Pieza{
	private double alto;
	private double ancho;
	private String movimientoArtistico;
	private boolean instalacion;
	
	public Pintura(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS,
			String Tipo, double Alto, double Ancho, String MovimientoArtistico, boolean Instalacion, boolean vendida, double precioVenta, String fechaVenta,List<String> dueños) {
			super(ID,Tecnica,Autor,Titulo,Anio,Lugar,Estado,Disponibilidad,FechaLimite,Valor,Consignacion,Devolucion,Subasta,ValorMinimoS,ValorInicialS, Tipo,vendida,precioVenta,fechaVenta,dueños);
			this.iD=ID;
			this.tecnica=Tecnica;
			this.autor=Autor;
			this.titulo=Titulo;
			this.anio=Anio;
			this.lugar=Lugar;
			this.estado=Estado;
			this.disponibilidad=Disponibilidad;
			this.fechaLimite=FechaLimite;
			this.valor=Valor;
			this.consignacion=Consignacion;
			this.devolucion=Devolucion;
			this.subasta=Subasta;
			this.valorMinimoS=ValorMinimoS;
			this.valorInicialS=ValorInicialS;
			this.tipo=Tipo;
			this.alto=Alto;
			this.ancho=Ancho;
			this.movimientoArtistico=MovimientoArtistico;
			this.instalacion=Instalacion;
			this.vendida=vendida;
			this.precioVenta=precioVenta;
			this.fechaVenta=fechaVenta;
			this.dueños=dueños;
		}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public String getMovimientoArtistico() {
		return movimientoArtistico;
	}

	public Boolean getInstalacion() {
		return instalacion;
	}

}


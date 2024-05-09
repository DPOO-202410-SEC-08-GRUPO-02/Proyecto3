package Inventario;

import java.util.List;

public class Video extends Pieza{
	private String duracion;
	private boolean electricidad;
	
	public Video(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS,
			String Tipo, String Duracion,boolean Electricidad, boolean vendida, double precioVenta, String fechaVenta,List<String> dueños) {
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
			this.duracion=Duracion;
			this.electricidad=Electricidad;
			this.vendida=vendida;
			this.precioVenta=precioVenta;
			this.fechaVenta=fechaVenta;
			this.dueños=dueños;
		}

	public String getDuracion() {
		return duracion;
	}

	public Boolean getElectricidad() {
		return electricidad;
	}

}

package Inventario;

public class Impresion extends Pieza{
	private double alto;
	private double ancho;
	private String soporte;
	private boolean instalacion;
	
	public Impresion(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS,
			String Tipo, double Alto, double Ancho, String Soporte,boolean Instalacion) {
			super(ID,Tecnica,Autor,Titulo,Anio,Lugar,Estado,Disponibilidad,FechaLimite,Valor,Consignacion,Devolucion,Subasta,ValorMinimoS,ValorInicialS, Tipo);
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
			this.soporte=Soporte;
			this.instalacion=Instalacion;
		}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public String getSoporte() {
		return soporte;
	}

	public Boolean getInstalacion() {
		return instalacion;
	}

}

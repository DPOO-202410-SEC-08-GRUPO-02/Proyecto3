package Inventario;


import java.util.List;

public class Escultura extends Pieza{
	private double alto;
	private double ancho;
	private double profundidad;
	private List<String> materiales;
	private double peso;
	private boolean instalacion;
	private boolean electricidad;
	
	public Escultura(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS,
			String Tipo, double Alto, double Ancho, double Profundidad,List<String> Materiales,double Peso, boolean Instalacion, boolean Electricidad) {
			super(ID,Tecnica,Autor,Titulo,Anio,Lugar,Estado,Disponibilidad,FechaLimite,Valor,Consignacion,Devolucion,Subasta,ValorMinimoS,ValorInicialS,Tipo);
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
			this.profundidad=Profundidad;
			this.materiales=Materiales;
			this.peso=Peso;
			this.instalacion=Instalacion;
			this.electricidad=Electricidad;
		}

	public double getAlto() {
		return alto;
	}

	public double getAncho() {
		return ancho;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public List<String> getMateriales() {
		return materiales;
	}

	public double getPeso() {
		return peso;
	}

	public Boolean getInstalacion() {
		return instalacion;
	}

	public Boolean getElectricidad() {
		return electricidad;
	}

}

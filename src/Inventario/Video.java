package Inventario;

public class Video extends Pieza{
	private String duracion;
	private boolean electricidad;
	
	public Video(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS,
			String Tipo, String Duracion,boolean Electricidad) {
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
			this.duracion=Duracion;
			this.electricidad=Electricidad;
		}

	public String getDuracion() {
		return duracion;
	}

	public Boolean getElectricidad() {
		return electricidad;
	}

}

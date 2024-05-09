package Inventario;

import java.util.ArrayList;
import java.util.List;

public class Pieza {
		protected String iD;
		protected String tecnica;
		protected String autor;
		protected String titulo;
		protected int anio;
		protected String lugar;
		protected String estado;
		protected boolean disponibilidad;
		protected String fechaLimite;
		protected double valor;
		protected boolean consignacion;
		protected boolean devolucion;
		protected boolean subasta;
		protected double valorMinimoS;
		protected double valorInicialS;
		protected String tipo;
        protected boolean vendida;
		protected double precioVenta;
		protected String fechaVenta;
		protected List<String> dueños= new ArrayList<String>();
		
		
	public Pieza(String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS, String Tipo,
			boolean vendida, double precioVenta, String fechaVenta,List<String> dueños) {
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
			this.vendida= vendida;
			this.precioVenta= precioVenta;
			this.fechaVenta= fechaVenta;
			this.dueños=dueños;
		}
	
	
	public String getID() {
		return iD;
	}
	
	
	public String getTecnica() {
		return tecnica;
	}
	
	
	public String getAutor() {
		return autor;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	
	
	public int getAnio() {
		return anio;
	}
	
	
	public String getLugar() {
		return lugar;
	}
	
	
	public String getEstado() {
		return estado;
	}
	
	
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}
	
	
	public String getFechaLimite() {
		return fechaLimite;
	}
	
	
	public double getValor() {
		return valor;
	}
	
	
	public Boolean getConsignacion() {
		return consignacion;
	}
	
	
	public Boolean getDevolucion() {
		return devolucion;
	}
	
	
	public Boolean getSubasta() {
		return subasta;
	}
	
	
	public Double getValorMinimoS() {
		return valorMinimoS;
	}
	
	
	public Double getValorInicialS() {
		return valorInicialS;
	}

	public boolean getVendida() {
		return vendida;
	}
	
	public double getPrecioVenta() {
		return precioVenta;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}
	public List<String> getDueños() {
		return dueños;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public void setDevolucion(Boolean devolucion) {
		this.devolucion= devolucion;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}


	public void setConsignacion(Boolean consignacion) {
		this.consignacion = consignacion;
	}


	public void setSubasta(Boolean subasta) {
		this.subasta = subasta;
	}


	public void setValorMinimoS(Double valorMinimoS) {
		this.valorMinimoS = valorMinimoS;
	}


	public void setValorInicialS(Double valorInicialS) {
		this.valorInicialS = valorInicialS;
	}


	public String getTipo() {
		return tipo;
	}
	
	
	public void setVendida(boolean vendida) {
		this.vendida= vendida;
	}
	
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta= precioVenta;
	}
	
	public void setfechaVenta(String fechaVenta) {
		this.fechaVenta= fechaVenta;
	}
	
}

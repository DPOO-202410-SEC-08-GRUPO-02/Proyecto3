package Artista;

import java.util.HashMap;
import java.util.Map;

public class Artista 
{
    private String nombre;
    private Map<String, Map<String, Object>> piezasHechas;

    public Artista(String nombre) 
    {
        this.nombre=nombre;
        this.piezasHechas=new HashMap<>();
    }

    public String getNombre() 
    {
        return nombre;
    }

    public Map<String, Map<String, Object>> getPiezasHechas() 
    {
        return piezasHechas;
    }

    public void addPiezaHecha(String nombrePieza, String fechaCreacion, String fechaVenta, double precioVenta, boolean vendida) 
    {
        Map<String, Object> infoPieza = new HashMap<>();
        infoPieza.put("fechaCreacion", fechaCreacion);
        infoPieza.put("fechaVenta", fechaVenta);
        infoPieza.put("precioVenta", precioVenta);
        infoPieza.put("vendida", vendida);
        piezasHechas.put(nombrePieza, infoPieza);
    }
}


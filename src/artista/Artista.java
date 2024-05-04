package artista;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artista 
{
    private String nombre;
    private Map<String, Map<String, String>> piezasHechas;

    public Artista(String nombre) 
    {
        this.nombre=nombre;
        this.piezasHechas=new HashMap<>();
    }

    public String getNombre() 
    {
        return nombre;
    }

    public Map<String, Map<String, String>> getPiezasHechas() 
    {
        return piezasHechas;
    }

    public void addPiezaHecha(String nombrePieza, String fechaCreacion, String fechaVenta, String precioVenta) 
    {
        Map<String, String> infoPieza = new HashMap<>();
        infoPieza.put("FechaDeCreacion", fechaCreacion);
        infoPieza.put("FechaDeVenta", fechaVenta);
        infoPieza.put("PrecioDeVenta", String.valueOf(precioVenta));
        piezasHechas.put(nombrePieza, infoPieza);
    }

    public void mostrarHistorial() 
    {
        List<String> mostrar = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> infoArtista : piezasHechas.entrySet()) 
        {
            String piezaInfo = "Pieza: "+infoArtista.getKey();
            Map<String, String> infoPieza=infoArtista.getValue();

            piezaInfo += "Fecha de creacion: "+infoPieza.get("FechaDeCreacion");

            if (infoPieza.containsKey("FechaDeVenta")) 
            {
                piezaInfo+="Fecha de venta:"+infoPieza.get("FechaDeVenta");
                piezaInfo+="Precio de venta:"+infoPieza.get("PrecioDeVenta");
            } 
            else 
            {
                piezaInfo+="Esta pieza no ha sido vendida.";
            }

            mostrar.add(piezaInfo);
        }

        for (String info:mostrar) 
        {
            System.out.println(info);
        }
    }
    //No se si esa vaina de mostrar historial funcione
}


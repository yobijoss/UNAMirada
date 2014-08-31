package mx.unam.hack.unamirada.objetos;

import java.util.ArrayList;

/**
 * Created by jagspage2013 on 31/08/14.
 */
public class Lista {

    String nombre;
    String fecha;
    ArrayList<Eventos> eventos ;
    long id;


    public Lista(long id,String nombre, String fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.id = id;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setEventos(ArrayList<Eventos> eventos){
        this.eventos = eventos;
    }

    public ArrayList<Eventos> getEventos(){
        return eventos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

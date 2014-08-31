package mx.unam.hack.unamirada.objetos;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by jagspage2013 on 30/08/14.
 */
@ParseClassName("Eventos")
public class Eventos extends ParseObject{

    public void setParseUser(ParseUser user){
        put("usuario",user);
    }
    public ParseUser getParseUser(){
        return getParseUser("usuario");
    }

    public void setNombre(String nombre){put("nombre",nombre);}
    public String getNombre(){return getString("nombre");}

    public void setHoraInicio(String hora_inicio){put("hora_inicio",hora_inicio);}
    public String getHoraInicio(){return getString("hora_inicio");}

    public void setHoraFinal(String hora_final){put("hora_final",hora_final);}
    public String getHoraFinal(){return getString("hora_final");}

    public void setFecha(String fecha){put("fecha",fecha);}
    public String getFecha(){return getString("fecha");}

    public void setDescripcion(String descripcion){put("descripcion",descripcion);}
    public String getDescripcion(){return getString("descripcion");}

    public void setLugar(String lugar){put("lugar",lugar);}
    public String getLugar(){return getString("lugar");}

    public void setCategoria(int categoria){put("categoria",categoria);}
    public int getCategoria(){return getInt("categoria");}

    public void setSubCategoria(int sub_categoria){put("sub_categoria",sub_categoria);}
    public int getSubCategoria(){return getInt("sub_categoria");}

}

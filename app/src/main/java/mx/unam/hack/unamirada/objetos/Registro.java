package mx.unam.hack.unamirada.objetos;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by jagspage2013 on 30/08/14.
 */

@ParseClassName("Registro")
public class Registro extends ParseObject {

    public void setParseUser(ParseUser user){
        put("usuario",user);
    }
    public ParseUser getParseUser(){
        return getParseUser("usuario");
    }

    public void setNombre(String nombre){put("nombre",nombre);}
    public String getNombre(){return getString("nombre");}

}

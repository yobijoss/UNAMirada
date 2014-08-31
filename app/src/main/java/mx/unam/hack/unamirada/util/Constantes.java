package mx.unam.hack.unamirada.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import mx.unam.hack.unamirada.objetos.Eventos;

/**
 * Created by jagspage2013 on 28/08/14.
 */
public class Constantes {

    public static ArrayList<Integer> op1;
    public static ArrayList<Integer> op2;
    public static ArrayList<Integer> op3;
    public static int actual;
    public static ArrayList<Eventos> eve_ret;

    public static void init(){
        op1 = new ArrayList<Integer>();
        op2 = new ArrayList<Integer>();
        op3 = new ArrayList<Integer>();
        eve_ret= new ArrayList<Eventos>();
    }

    public static void setName(Context context,String name){
        SharedPreferences.Editor edit =  context.getSharedPreferences("unamirada",Context.MODE_PRIVATE).edit();
        edit.putString("nombre",name);
        edit.commit();
    }

    public static String getName(Context context){
        String nombre = context.getSharedPreferences("unamirada",Context.MODE_PRIVATE).getString("nombre","");
        return nombre;
    }

    public static void setOpciones (Context context, int categoria, int[] elem){
        SharedPreferences.Editor edit =  context.getSharedPreferences("unamirada_"+categoria,Context.MODE_PRIVATE).edit();
        edit.putInt("size", elem.length);
        int count = 0;
        for(int i : elem){
            edit.putInt("value_"+count++,i);
        }
        edit.commit();
        //Log.d("UNAMirada","Se guardaron opciones "+elem.length);
    }

    public static ArrayList<Integer> getOpciones(Context context, int categoria){
        ArrayList<Integer> e = new ArrayList<Integer>();
        SharedPreferences prefs = context.getSharedPreferences("unamirada_"+categoria,Context.MODE_PRIVATE);
        int count = prefs.getInt("size",-1);
        for(int i= 0 ; i <count; i++){
            e.add(prefs.getInt("value_" + i, -1));
        }
        return e;
    }
}

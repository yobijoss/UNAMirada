package mx.unam.hack.unamirada.util.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.ArrayList;

import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.objetos.Lista;
import mx.unam.hack.unamirada.util.MetodosUtiles;

/**
 * Created by jagspage2013 on 31/08/14.
 */
public class DBTransaction  {

    private SQLiteDatabase db;
    private DictionaryHelper mDbHelper;
    private ArrayList<String> ids;

    public DBTransaction(Context context){
         mDbHelper = new DictionaryHelper(context);
    }

    public void open(){
        db = mDbHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }

    public long insertEvento(Eventos event){
        ContentValues values = new ContentValues();
        values.put(DictionaryHelper.COL_ID_EVENTOS,event.getObjectId());
        values.put(DictionaryHelper.COL_NOMBRE,event.getNombre());
        values.put(DictionaryHelper.COL_FECHA,event.getFecha());
        values.put(DictionaryHelper.COL_HORA_INICIO,event.getHoraInicio());
        values.put(DictionaryHelper.COL_HORA_FINAL,event.getHoraFinal());
        values.put(DictionaryHelper.COL_LUGAR,event.getLugar());
        values.put(DictionaryHelper.COL_DESCRIPCION,event.getDescripcion());
        try {
            values.put(DictionaryHelper.COL_IMAGEN, event.getImagen().getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return db.insert(DictionaryHelper.TABLE_NAME_EVENTOS,null,values);
    }
    public long insertLista(ContentValues values){

        return db.insert(DictionaryHelper.TABLE_NAME_LISTAS,null,values);
    }

    public long insertContiene(Lista lista, Eventos evento){
        ContentValues values = new ContentValues();
        values.put(DictionaryHelper.COL_ID_EVENTOS, evento.getObjectId());
        values.put(DictionaryHelper.COL_ID_LISTAS,lista.getId());
        return db.insert(DictionaryHelper.TABLE_NAME_CONTIENE,null,values);
    }

    public ArrayList<Eventos> getEventosDeLista(long id){
        ArrayList<Eventos> eventos ;
        ids = new ArrayList<String>();
        String query = "SELECT "+DictionaryHelper.COL_ID_EVENTOS+" FROM "
                +DictionaryHelper.TABLE_NAME_CONTIENE + " WHERE " +
                DictionaryHelper.COL_ID_LISTAS+" = ?";
        Log.d("UNAMirada",query + " id "+new String[]{String.valueOf(id)}.toString());
        Cursor cursor = db.rawQuery(query,
                         new String[]{String.valueOf(id)}
        );
        Log.d("UNAMirada", "Eventos encontrados " + cursor.getPosition());
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Log.d("UNAMirada ","" +cursor.getString(0));
            ids.add(cursor.getString(0));
            cursor.moveToNext();
        }
        eventos = findEventos();
        return eventos;
    }


    private ArrayList<Eventos> findEventos() {
        ArrayList<Eventos> eventos = new ArrayList<Eventos>();
        String[] array = ids.toArray(new String[ids.size()]);

        Cursor cursor = db.rawQuery("SELECT * FROM "+DictionaryHelper.TABLE_NAME_EVENTOS+ " WHERE "+
                DictionaryHelper.COL_ID_EVENTOS+" in ("+ponInterrogaciones(array.length)+");",array);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Eventos e = new Eventos();
            e.setObjectId(String.valueOf(cursor.getString(0)));
            e.setNombre(cursor.getString(1));
            e.setFecha(cursor.getString(2));
            try{
                e.setHoraInicio(cursor.getString(3));
                e.setHoraFinal(cursor.getString(4));
            }catch(IllegalArgumentException il){

            }
            e.setLugar(cursor.getString(5));
            e.setDescripcion(cursor.getString(6));
            e.setImagen(new ParseFile("imagen",cursor.getBlob(7)));
            eventos.add(e);
            cursor.moveToNext();
        }

        return eventos;
    }


    public Eventos findEvento(String id) {
        Eventos e = new Eventos();

        Cursor cursor = db.rawQuery("SELECT * FROM "+DictionaryHelper.TABLE_NAME_EVENTOS+ " WHERE "+
                DictionaryHelper.COL_ID_EVENTOS+" = ?",new String[]{id});
        cursor.moveToFirst();
        e.setObjectId(String.valueOf(cursor.getLong(0)));
        e.setNombre(cursor.getString(1));
        e.setFecha(cursor.getString(2));
        try{
            e.setHoraInicio(cursor.getString(3));
            e.setHoraFinal(cursor.getString(4));
        }catch(IllegalArgumentException il){

        }
        e.setLugar(cursor.getString(5));
        e.setDescripcion(cursor.getString(6));
        e.setImagen(new ParseFile("imagen",cursor.getBlob(7)));

        return e;
    }

    public ArrayList<Lista> getListas(){
        ArrayList<Lista> listas = new ArrayList<Lista>();
        Cursor cursor = db.query(DictionaryHelper.TABLE_NAME_LISTAS,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            listas.add(new Lista(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            cursor.moveToNext();
        }
        return listas;
    }

    String ponInterrogaciones(int len){
        if (len < 1) {
            // It will lead to an invalid query anyway ..
            throw new RuntimeException("No placeholders");
        } else {
            StringBuilder sb = new StringBuilder(len * 2 - 1);
            sb.append("?");
            for (int i = 1; i < len; i++) {
                sb.append(",?");
            }
            return sb.toString();
        }
    }




}

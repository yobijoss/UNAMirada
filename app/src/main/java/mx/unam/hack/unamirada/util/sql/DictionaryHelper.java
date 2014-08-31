package mx.unam.hack.unamirada.util.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jagspage2013 on 31/08/14.
 */
public class DictionaryHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 3;
    private static String DATABASE_NAME = "MiBD7.bd";

    //tabla eventos
    public static String TABLE_NAME_EVENTOS = "eventos";
    public static String COL_ID_EVENTOS = "id_evento";
    public static String COL_NOMBRE = "nombre";
    public static String COL_FECHA ="fecha";
    public static String COL_HORA_INICIO = "hora_inicio";
    public static String COL_HORA_FINAL = "hora_final";
    public static String COL_LUGAR = "lugar";
    public static String COL_DESCRIPCION ="descripcion";
    public static String COL_IMAGEN="imagen";

    //tabla listas
    public static String TABLE_NAME_LISTAS = "listas";
    public static String COL_ID_LISTAS = "id_lista";
    public static String COL_FECHA_CREACION ="fecha_creacion";

    //tabla contiene
    public static String TABLE_NAME_CONTIENE ="contiene";
    public static String COL_KEY_ID ="_id";

    private static String EVENTOS_CREATE_TABLE =
            "CREATE TABLE "+ DictionaryHelper.TABLE_NAME_EVENTOS + "("+
            DictionaryHelper.COL_ID_EVENTOS +" TEXT UNIQUE,"+
            DictionaryHelper.COL_NOMBRE + " TEXT,"+
            DictionaryHelper.COL_FECHA + " TEXT,"+
            DictionaryHelper.COL_HORA_INICIO + " TEXT,"+
            DictionaryHelper.COL_HORA_FINAL +" TEXT,"+
            DictionaryHelper.COL_LUGAR + " TEXT,"+
            DictionaryHelper.COL_DESCRIPCION + " TEXT,"+
            DictionaryHelper.COL_IMAGEN +" BLOB);";

    private static String LISTAS_CREATE_TABLE =
            "CREATE TABLE "+ DictionaryHelper.TABLE_NAME_LISTAS + "("+
                    DictionaryHelper.COL_ID_LISTAS +" integer primary key autoincrement,"+
                    DictionaryHelper.COL_NOMBRE + " TEXT,"+
                    DictionaryHelper.COL_FECHA + " TEXT);";

    private static String CONTIENE_CREATE_TABLE =
            "CREATE TABLE "+ DictionaryHelper.TABLE_NAME_CONTIENE + "("+
                    DictionaryHelper.COL_KEY_ID +" integer primary key autoincrement,"+
                    DictionaryHelper.COL_ID_EVENTOS +" TEXT NOT NULL,"+
                    DictionaryHelper.COL_ID_LISTAS + " integer NOT NULL);";

    public DictionaryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EVENTOS_CREATE_TABLE);
        db.execSQL(LISTAS_CREATE_TABLE);
        db.execSQL(CONTIENE_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

    }
}

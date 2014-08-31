package mx.unam.hack.unamirada.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jagspage2013 on 30/08/14.
 */
public class MetodosUtiles {

    public static int[]convierte_lista_arreglo(ArrayList<Integer> integers){
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next();
        }
        return ret;
    }

    public static void removeFromList(ArrayList<Integer> e, int dato){
        for (Iterator<Integer> iter = e.listIterator(); iter.hasNext(); ) {
            int a = iter.next();
            if (a == dato) {
                iter.remove();
            }
        }
    }

    public static Bitmap resuelve(ParseFile imagen) {

        byte[] data = new byte[1024];
        try {
            data = imagen.getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

        return bmp;
    }
}

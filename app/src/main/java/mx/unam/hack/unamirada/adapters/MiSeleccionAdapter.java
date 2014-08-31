package mx.unam.hack.unamirada.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;

import java.util.ArrayList;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.util.MetodosUtiles;

/**
 * Created by jagspage2013 on 30/08/14.
 */
public class MiSeleccionAdapter extends ArrayAdapter<Eventos>{

    Context context;
    ArrayList<Eventos> objects;
    public MiSeleccionAdapter(Context context, int resource, ArrayList<Eventos> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    static class ViewHolder{
        public ImageView img_titulo;
        public TextView lbl_thumb_nombre;
        public TextView lbl_thumb_lugar;
        public TextView lbl_thumb_fecha;
        public TextView lbl_thumb_inicio;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null)
        convertView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                        inflate(R.layout.layout_adapter_seleccion, null);
        ViewHolder holder  = new ViewHolder();
        holder.img_titulo = (ImageView)convertView.findViewById(R.id.img_titulo);
        holder.lbl_thumb_nombre = (TextView)convertView.findViewById(R.id.lbl_thumb_nombre);
        holder.lbl_thumb_lugar = (TextView)convertView.findViewById(R.id.lbl_thumb_lugar);
        holder.lbl_thumb_fecha = (TextView)convertView.findViewById(R.id.lbl_thumb_fecha);
        holder.lbl_thumb_inicio = (TextView)convertView.findViewById(R.id.lbl_thumb_inicio);
        holder.lbl_thumb_nombre.setText( objects.get(position).getNombre());
        holder.lbl_thumb_lugar.setText( objects.get(position).getLugar());
        holder.lbl_thumb_fecha.setText( objects.get(position).getFecha());
        holder.lbl_thumb_inicio.setText( objects.get(position).getHoraInicio());
        holder.img_titulo.setImageBitmap(MetodosUtiles.resuelve(objects.get(position).getParseFile("imagen")));
        holder.img_titulo.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.img_titulo.setAdjustViewBounds(true);
        convertView.setTag(holder);


        return convertView;
    }


}

package mx.unam.hack.unamirada.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.objetos.Lista;

/**
 * Created by jagspage2013 on 31/08/14.
 */
public class ListEventAdapter extends ArrayAdapter<Lista> {

    private Context context;
    private ArrayList<Lista> objects;
    public ListEventAdapter(Context context, int resource, ArrayList<Lista> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    static class ViewHolder{
        public  TextView lbl_adapter_listas_titulo;
        public  TextView lbl_adapter_listas_fecha;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder  = new ViewHolder();
        if(convertView==null)
            convertView=((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_adapter_lista_eventos,null);

        holder.lbl_adapter_listas_titulo = (TextView)convertView.findViewById(R.id.lbl_adapter_listas_titulo);
        holder.lbl_adapter_listas_fecha = (TextView)convertView.findViewById(R.id.lbl_adapter_listas_fecha);
        holder.lbl_adapter_listas_titulo.setText(objects.get(position).getNombre());
        holder.lbl_adapter_listas_fecha.setText(objects.get(position).getFecha());
        convertView.setTag(holder);
        return convertView;
    }
}

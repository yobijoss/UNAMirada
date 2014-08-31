package mx.unam.hack.unamirada.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Iterator;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.util.Constantes;
import mx.unam.hack.unamirada.util.MetodosUtiles;

/**
 * Created by jagspage2013 on 30/08/14.
 */
public class ListaSelectorAdapter extends ArrayAdapter<String> {

    String [] objetos;
    Context context;

    public ListaSelectorAdapter(Context context, int resource, String[] objetos) {
        super(context, resource, objetos);
        this.objetos = objetos;
        this.context = context;
    }

    public static class ViewHolder{
        public CheckBox checkBox;
    }

    @Override
    public View getView( final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null){
            view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.layout_item_lista_selector, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox)view.findViewById(R.id.checkBox_selector);
            viewHolder.checkBox.setText(objetos[position]);
            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.d("UNAMirada","Checked en pos "+Constantes.actual);
                    if(b){
                        switch (Constantes.actual){
                            case 0:
                                if(!Constantes.op1.contains(position))
                                    Constantes.op1.add(position);
                                break;
                            case 1:
                                if(!Constantes.op2.contains(position))
                                    Constantes.op2.add(position);
                                break;
                            case 2:
                                if(!Constantes.op3.contains(position))
                                    Constantes.op3.add(position);
                                break;
                        }

                    }
                    else{
                        switch (Constantes.actual){
                            case 0:
                                MetodosUtiles.removeFromList(Constantes.op1, position) ;
                                break;
                            case 1:
                                MetodosUtiles.removeFromList(Constantes.op2, position) ;
                                break;
                            case 2:
                                MetodosUtiles.removeFromList(Constantes.op3, position) ;
                                break;
                        }
                    }

                }
            });
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder)view.getTag();

        return view;
    }

}

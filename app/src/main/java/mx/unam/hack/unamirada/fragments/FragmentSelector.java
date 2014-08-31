package mx.unam.hack.unamirada.fragments;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.adapters.ListaSelectorAdapter;
import mx.unam.hack.unamirada.util.Constantes;

/**
 * Created by jagspage2013 on 30/08/14.
 */
public class FragmentSelector extends Fragment {

    public static String ARG_OBJECT;

    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int pos;
        ListView list;
        ImageView titulo;
        View raiz = inflater.inflate(R.layout.fragment_selector,null);
        list  = (ListView)raiz.findViewById(R.id.listView);
        titulo = (ImageView)raiz.findViewById(R.id.lbl_titulo);
        int seleccion = 0;
        pos = getArguments().getInt(ARG_OBJECT);
        switch (pos){
            case 1:seleccion = R.array.opc_arte; titulo.setImageResource(R.drawable.arte);break;
            case 2:seleccion = R.array.opc_deporte; titulo.setImageResource(R.drawable.deporte); break;
            case 3:seleccion = R.array.opc_ciencia_tecnología; titulo.setImageResource(R.drawable.ciencia_tecnologia); break;
        }
        String[] objeto = getActivity().getResources().getStringArray(seleccion);
        list.setAdapter(new ListaSelectorAdapter(context,R.layout.layout_item_lista_selector,objeto));

        return raiz;
    }


}

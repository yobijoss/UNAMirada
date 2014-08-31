package mx.unam.hack.unamirada.fragments;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.adapters.ListEventAdapter;
import mx.unam.hack.unamirada.objetos.Lista;
import mx.unam.hack.unamirada.util.sql.DBTransaction;
import mx.unam.hack.unamirada.util.sql.DictionaryHelper;

public class ListaEventosFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemClickListener{

    private OnFragmentAdapterListener mListener;

    private ListView list;
    private ListEventAdapter adapter;
    private ArrayList<Lista> listas;

    public ListaEventosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_lista_eventos, container, false);
        list = (ListView)v.findViewById(R.id.listView);
        ((Button)v.findViewById(R.id.btn_agregarLista)).setOnClickListener(this);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DBTransaction transaction = new DBTransaction(getActivity());
        transaction.open();
        listas = transaction.getListas();
        adapter = new ListEventAdapter(getActivity(), R.layout.layout_adapter_lista_eventos, listas);
        list.setAdapter(adapter);
        transaction.close();
        list.setOnItemClickListener(this);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentAdapterListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentAdapterListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_agregarLista){
            if(mListener!=null)
                mListener.onFragmentInteraction();
        }
    }

    public void update(ContentValues values) {
        adapter.add(new Lista(values.getAsLong(DictionaryHelper.COL_ID_LISTAS),values.getAsString(DictionaryHelper.COL_NOMBRE),values.getAsString(DictionaryHelper.COL_FECHA)));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mListener.irNuevListaEventos((i));
    }

    public interface OnFragmentAdapterListener {
        public void onFragmentInteraction();
        public void irNuevListaEventos(int pos);
    }


}

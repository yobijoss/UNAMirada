package mx.unam.hack.unamirada.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.adapters.MiSeleccionAdapter;
import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.util.Constantes;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class MiSeleccionFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private GridView mGridView;
    private MiSeleccionAdapter mAdapter;


    public MiSeleccionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new MiSeleccionAdapter(this.getActivity(),R.layout.layout_adapter_seleccion, Constantes.eve_ret);
        if(!Constantes.eve_ret.isEmpty()){
            getEventos();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_miseleccionfragment, container, false);

        // Set the adapter
        mGridView = (GridView) view.findViewById(android.R.id.list);
        mGridView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mGridView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onFragmentInteraction(position);
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mGridView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int pos);
    }

    public void getEventos() {

        Log.d("uNAMirada","Entrango Get EVntos");
        final ArrayList<Integer> op1 = Constantes.getOpciones(getActivity(),0);
        final ArrayList<Integer> op2 = Constantes.getOpciones(getActivity(),1);
        final ArrayList<Integer> op3 = Constantes.getOpciones(getActivity(),2);

        ArrayList<Integer> cat_query = new ArrayList<Integer>();
        if(op1.size()>0)
            cat_query.add(0);
        if(op2.size()>0)
            cat_query.add(1);
        if(op3.size()>0)
            cat_query.add(2);


            ParseQuery<Eventos> query = ParseQuery.getQuery("Eventos");
            query.whereContainedIn("categoria", cat_query);
            Log.d("UNAMirada","Tamaño cat query : "+cat_query.toArray());
            query.findInBackground(new FindCallback<Eventos>() {
                @Override
                public void done(List<Eventos> eventos, ParseException e) {

                     if(e==null) {
                         Log.d("UNAMirada","Tamaño query"+eventos.size());

                         if (eventos.size() > 0) {
                             Iterator<Eventos> iter = eventos.iterator();
                             while (iter.hasNext()) {
                                 Eventos ev = iter.next();
                                 if ((ev.getCategoria() == 0 && op1.contains(ev.getSubCategoria())) ||
                                         (ev.getCategoria() == 1 && op2.contains(ev.getSubCategoria())) ||
                                         (ev.getCategoria() == 2 && op3.contains(ev.getSubCategoria()))) {
                                     Constantes.eve_ret.add(ev);
                                     mAdapter.notifyDataSetChanged();
                                     Log.d("UNAMirada","Se agrego Algo");
                                 }
                             }
                         }
                     }else{
                         Log.d("UNAMirada","Error ");
                         e.printStackTrace();
                     }

                }
            });


    }


}

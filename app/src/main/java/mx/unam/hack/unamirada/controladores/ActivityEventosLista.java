package mx.unam.hack.unamirada.controladores;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.adapters.MiSeleccionAdapter;
import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.objetos.Lista;
import mx.unam.hack.unamirada.util.sql.DBTransaction;

public class ActivityEventosLista extends ActionBarActivity {

    private ArrayList<Eventos> eventos;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_eventos_lista);
        gridView = (GridView)findViewById(R.id.lista_alterna);
        preparaEvento(getIntent().getExtras().getInt("pos"));
        MiSeleccionAdapter adapter = new MiSeleccionAdapter(this,R.layout.layout_adapter_lista_eventos,eventos);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 irARecordatorio(i);
            }
        });

    }

    private void irARecordatorio(int i) {
        Log.d("UNAMirada","Evento escogido +" +i);
        Eventos e  = eventos.get(i);
        Intent intent = new Intent(ActivityEventosLista.this,RecordarEventoActivity.class);
        intent.putExtra("id",e.getObjectId());
        Log.d("UNAMirada","Id Enviado"+e.getObjectId());
        startActivity(intent);

    }

    private void preparaEvento(int pos) {
        DBTransaction trans = new DBTransaction(this);
        trans.open();
        ArrayList<Lista> listas= trans.getListas();
        Log.d("UNAMirada","Lista escogida : "+listas.get(pos).getNombre());
        long i = listas.get(pos).getId();
        Log.d("Unamirada", "ID de la lista "+i);
        eventos = trans.getEventosDeLista(i);
        Log.d("UNAMirada","Numero de Eventos"+ eventos.size());
        trans.close();
    }


}

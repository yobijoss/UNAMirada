package mx.unam.hack.unamirada.controladores;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.objetos.Lista;
import mx.unam.hack.unamirada.util.Constantes;
import mx.unam.hack.unamirada.util.MetodosUtiles;
import mx.unam.hack.unamirada.util.sql.DBTransaction;

public class EventoDetalladoActivity extends ActionBarActivity {

    private ImageView img_det;
    private TextView lbl_det_nombre;
    private TextView lbl_det_lugar;
    private TextView lbl_det_fecha;
    private TextView lbl_det_horaInicio;
    private TextView lbl_det_horaFinal;
    private TextView lbl_det_descripcion;
    private Eventos evento;
    private Lista lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detallado);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        img_det = (ImageView)findViewById(R.id.img_det);
        lbl_det_nombre = (TextView)findViewById(R.id.lbl_det_nombre);
        lbl_det_fecha = (TextView)findViewById(R.id.lbl_det_fecha);
        lbl_det_lugar = (TextView)findViewById(R.id.lbl_det_lugar);
        lbl_det_horaInicio = (TextView)findViewById(R.id.lbl_det_hotaInicio);
        lbl_det_horaFinal = (TextView)findViewById(R.id.lbl_det_horaFinal);
        lbl_det_descripcion = (TextView)findViewById(R.id.lbl_det_descripcion);

        Bundle bundle = getIntent().getExtras();
        evento = Constantes.eve_ret.get(bundle.getInt("pos"));
        setUpScreen(evento);

        ((Button)findViewById(R.id.btn_guardar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialogChoser();

            }
        });
    }

    private void getDialogChoser() {
        DBTransaction trans  = new DBTransaction(this);
        trans.open();
        ArrayList<String> nombres = getNombres(trans.getListas());
        trans.close();

        AlertDialog dialog = new AlertDialog.Builder(this).
                 setTitle("Escoge una lista")
                .setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombres),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("UNAMirada", "Opcion escogida : " + i);
                        guardarEnDB(i);
                    }
                }
        ).create();
        dialog.show();
    }

    private void setUpScreen(Eventos eve) {

        img_det.setImageBitmap(MetodosUtiles.resuelve(eve.getParseFile("imagen")));
        lbl_det_nombre.setText(eve.getNombre());
        lbl_det_fecha.setText(eve.getFecha());
        lbl_det_lugar.setText(eve.getLugar());
        lbl_det_horaInicio.setText(eve.getHoraInicio());
        lbl_det_horaFinal.setText(eve.getHoraFinal());
        lbl_det_descripcion.setText(eve.getDescripcion());

    }

    public ArrayList<String> getNombres(ArrayList<Lista> lista){
        ArrayList<String> s = new ArrayList<String>();
        Iterator<Lista> iter = lista.iterator();
        while(iter.hasNext()){
            s.add(iter.next().getNombre());
        }
        return s;
    }

    public void  guardarEnDB(int i){
        DBTransaction trans = new DBTransaction(this);
        trans.open();
        long  l= trans.insertEvento(evento);
        if(l==-1){
            Log.d("UNAMirada","Error al guardar el evento");
        }
        long j = trans.insertContiene(trans.getListas().get(i),evento);
        trans.close();
        if(l==-1){
            Log.d("UNAMirada","Error al guardar en contiene");
        }
        Log.d("UNAMirada","Guardado ? ");
    }

}

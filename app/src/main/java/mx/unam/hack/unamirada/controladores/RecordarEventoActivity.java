package mx.unam.hack.unamirada.controladores;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.objetos.Eventos;
import mx.unam.hack.unamirada.util.MetodosUtiles;
import mx.unam.hack.unamirada.util.sql.DBTransaction;

public class RecordarEventoActivity extends ActionBarActivity {

    private ImageView img_fin;
    private TextView lbl_fin_nombre;
    private TextView lbl_fin_lugar;
    private TextView lbl_fin_fecha;
    private TextView lbl_fin_horaInicio;
    private TextView lbl_fin_horaFinal;
    private TextView lbl_fin_descripcion;
    private Eventos evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar_evento);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        img_fin = (android.widget.ImageView) findViewById(R.id.img_fin);
        lbl_fin_nombre = (TextView) findViewById(R.id.lbl_fin_nombre);
        lbl_fin_fecha = (TextView) findViewById(R.id.lbl_fin_fecha);
        lbl_fin_lugar = (TextView) findViewById(R.id.lbl_fin_lugar);
        lbl_fin_horaInicio = (TextView) findViewById(R.id.lbl_fin_hotaInicio);
        lbl_fin_horaFinal = (TextView) findViewById(R.id.lbl_fin_horaFinal);
        lbl_fin_descripcion = (TextView) findViewById(R.id.lbl_fin_descripcion);

        Bundle bundle = getIntent().getExtras();
        DBTransaction tran = new DBTransaction(this);
        tran.open();
        Log.d("UNAMirada", "id : " + bundle.getString("id"));
        evento = tran.findEvento(bundle.getString("id"));
        tran.close();
        setUpScreen(evento);
        ((Button) findViewById(R.id.btn_agregar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReminders();
            }
        });

    }

    private void setReminders() {

        SimpleDateFormat formatter_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat formatter_horas = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date horainicio = null;
        Date horafinal = null;

        try {
            horainicio = formatter_fecha.parse(evento.getFecha() + " " + evento.getHoraInicio());
            horafinal = formatter_horas.parse(evento.getFecha() + " " + evento.getHoraFinal());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long calID = 3;
        long startMillis = 0;
        long endMillis = 0;
        startMillis = horainicio.getTime();
        endMillis = horafinal.getTime();


        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, evento.getNombre());
        values.put(CalendarContract.Events.DESCRIPTION, evento.getDescripcion());
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "America/Mexico_City");
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

        long eventID = Long.parseLong(uri.getLastPathSegment());

        ContentResolver cr2 = getContentResolver();
        ContentValues values2 = new ContentValues();
        values2.put(CalendarContract.Reminders.MINUTES, 120);
        values2.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values2.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri2 = cr2.insert(CalendarContract.Reminders.CONTENT_URI, values2);

        Toast.makeText(this,"Tu evento a sido salvado",Toast.LENGTH_SHORT).show();
    }


    private void setUpScreen(Eventos eve) {

        img_fin.setImageBitmap(MetodosUtiles.resuelve(eve.getParseFile("imagen")));
        lbl_fin_nombre.setText(eve.getNombre());
        lbl_fin_fecha.setText(eve.getFecha());
        lbl_fin_lugar.setText(eve.getLugar());
        lbl_fin_horaInicio.setText(eve.getHoraInicio());
        lbl_fin_horaFinal.setText(eve.getHoraFinal());
        lbl_fin_descripcion.setText(eve.getDescripcion());

    }
}

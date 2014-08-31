package mx.unam.hack.unamirada.controladores;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.fragments.AddListDialog;
import mx.unam.hack.unamirada.fragments.ListaEventosFragment;
import mx.unam.hack.unamirada.fragments.MiSeleccionFragment;
import mx.unam.hack.unamirada.fragments.NavigationDrawerFragment;
import mx.unam.hack.unamirada.objetos.Lista;


public class ActivityEventos extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks ,MiSeleccionFragment.OnFragmentInteractionListener
                   ,ListaEventosFragment.OnFragmentAdapterListener,AddListDialog.DialogComunicate{

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private ListaEventosFragment frag_eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#141442")));

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MiSeleccionFragment())
                        .commit();
                break;
            case 1:
                frag_eventos = new ListaEventosFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, frag_eventos)
                        .commit();
                break;
            case 2:
               /* fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();*/
                break;
        }

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.activity_eventos, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(int pos) {
        //vamos a actividad evento. con la pos del evento.
        Intent intent = new Intent(ActivityEventos.this, EventoDetalladoActivity.class);
        intent.putExtra("pos",pos);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction() {
        AddListDialog dialog = new AddListDialog();
        dialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void irNuevListaEventos(int  lista) {
        Intent intent = new Intent(ActivityEventos.this, ActivityEventosLista.class);
        intent.putExtra("pos",lista);
        startActivity(intent);
    }

    @Override
    public void actualizar(ContentValues values) {
        frag_eventos.update(values);

    }
}

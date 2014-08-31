package mx.unam.hack.unamirada.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.viewpagerindicator.IconPageIndicator;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.adapters.MyPagerAdapter;
import mx.unam.hack.unamirada.fragments.FragmentContinuar;
import mx.unam.hack.unamirada.util.Constantes;
import mx.unam.hack.unamirada.util.MetodosUtiles;


public class FormularioActivity extends ActionBarActivity implements FragmentContinuar.SelectorContinuar{

    ViewPager mViewPager;
    MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Constantes.actual = 0;
        mViewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        IconPageIndicator pagerIndicator = (IconPageIndicator)findViewById(R.id.pagerIndicator);
        pagerIndicator.setViewPager(mViewPager);

        pagerIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                switch (i){
                    case 0:
                        if(i== Constantes.actual)
                        Constantes.setOpciones(getApplicationContext(),i, MetodosUtiles.convierte_lista_arreglo(Constantes.op1));
                        break;

                    case 1:
                        if(i== Constantes.actual)
                            Constantes.setOpciones(getApplicationContext(),i, MetodosUtiles.convierte_lista_arreglo(Constantes.op2));

                        break;
                    case 2:
                        if(i== Constantes.actual)
                            Constantes.setOpciones(getApplicationContext(),i, MetodosUtiles.convierte_lista_arreglo(Constantes.op3));

                        break;
                }
            }

            @Override
            public void onPageSelected(int i) {
                Constantes.actual = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });

        Constantes.init();
    }



    @Override
    public void continuar() {
        Intent intent = new Intent(FormularioActivity.this,ActivityEventos.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

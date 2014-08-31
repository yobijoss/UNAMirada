package mx.unam.hack.unamirada.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mx.unam.hack.unamirada.R;


public class FragmentContinuar extends Fragment {

    public SelectorContinuar continuar;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        continuar = (SelectorContinuar)activity;
    }

    public interface SelectorContinuar{
        public void continuar();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View raiz = inflater.inflate(R.layout.fragment_continuar,null);
        ((Button)raiz.findViewById(R.id.btn_continuar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continuar.continuar();
            }
        });
        return raiz;
    }
}

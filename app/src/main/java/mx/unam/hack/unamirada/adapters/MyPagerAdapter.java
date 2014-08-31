package mx.unam.hack.unamirada.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viewpagerindicator.IconPagerAdapter;

import mx.unam.hack.unamirada.R;
import mx.unam.hack.unamirada.fragments.FragmentContinuar;
import mx.unam.hack.unamirada.fragments.FragmentSelector;


public class MyPagerAdapter extends FragmentStatePagerAdapter implements IconPagerAdapter{

    public MyPagerAdapter(FragmentManager manager){
        super(manager);

    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;
        if(i<3){
            fragment = new FragmentSelector();
            Bundle args = new Bundle();
            args.putInt(FragmentSelector.ARG_OBJECT, i + 1);
            fragment.setArguments(args);
        }else{
            fragment = new FragmentContinuar();
        }
        return fragment;
    }

    @Override
    public int getIconResId(int index) {
        return R.drawable.circulo_indicador;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

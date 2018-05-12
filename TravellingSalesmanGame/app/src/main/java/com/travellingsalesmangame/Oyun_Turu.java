package com.travellingsalesmangame;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travellingsalesmangame.Views.Game.Hikaye;

public class Oyun_Turu extends Fragment {


    private View view;
    private Button btn_bilgisayar_karsi,btn_tek_oyna;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    private void init(){
        btn_bilgisayar_karsi=view.findViewById(R.id.btn_bilgisayar_karsi);
        btn_tek_oyna=view.findViewById(R.id.btn_tek_oyna);

        btn_tek_oyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hikaye hikaye=new Hikaye();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,hikaye);
                transaction.commit();
            }
        });

        btn_bilgisayar_karsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bilgisayar_Karsi bilgisayar=new Bilgisayar_Karsi();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,bilgisayar);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_oyun_turu,container,false);
        init();
        return view;
    }
}

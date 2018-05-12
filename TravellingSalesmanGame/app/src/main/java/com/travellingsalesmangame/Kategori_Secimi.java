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
import com.travellingsalesmangame.Views.Game.PopActivity;


public class Kategori_Secimi extends Fragment {

    private View view;
    private Button btn_Konu,btn_Test,btn_Oyun;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private void init(){

        getActivity().setTitle("Kategori");
        btn_Konu=view.findViewById(R.id.btn_Konu);
        btn_Oyun=view.findViewById(R.id.btn_Oyun);
        btn_Test=view.findViewById(R.id.btn_Test);

        btn_Konu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopActivity konu=new PopActivity();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,konu);
                transaction.commit();
            }
        });

        btn_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test test=new Test();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,test);
                transaction.commit();
            }
        });

        btn_Oyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Oyun_Turu oyunTuru=new Oyun_Turu();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,oyunTuru);
                transaction.commit();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_kategori_secimi,container,false);
        getActivity().setTitle("Kategotiler");
        init();
        return view;
    }
}

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

public class Master_Acilis extends Fragment {

    private View view;
    private Button btn_Basla;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private void init(){

        getActivity().setTitle("Anasayfa");
        btn_Basla=view.findViewById(R.id.btn_Basla);

        btn_Basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kategori_Secimi change=new Kategori_Secimi();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,change);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_master_acilis,container,false);
        init();
        return  view;
    }


}

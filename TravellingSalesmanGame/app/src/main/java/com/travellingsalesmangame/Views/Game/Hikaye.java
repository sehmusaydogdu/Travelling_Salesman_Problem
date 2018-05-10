package com.travellingsalesmangame.Views.Game;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.travellingsalesmangame.R;

public class Hikaye extends Fragment {

    private View view;
    private Button btn_Oyun;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private void init(){
        getActivity().setTitle("Oyun Başlıyor");
        btn_Oyun=view.findViewById(R.id.btn_Oyun);

        btn_Oyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LevelMenu_Fragment level=new LevelMenu_Fragment();
                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,level);
                transaction.commit();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_hikaye_anlat,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        super.onActivityCreated(savedInstanceState);
    }
}

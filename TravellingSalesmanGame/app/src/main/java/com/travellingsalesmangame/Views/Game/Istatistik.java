package com.travellingsalesmangame.Views.Game;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travellingsalesmangame.R;

public class Istatistik extends Fragment {

    private View view;

    private void init(){
        getActivity().setTitle("İstatistikler");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_istatistik,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        super.onActivityCreated(savedInstanceState);
    }
}

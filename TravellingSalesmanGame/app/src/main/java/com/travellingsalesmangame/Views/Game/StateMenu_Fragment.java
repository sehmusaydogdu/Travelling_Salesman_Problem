package com.travellingsalesmangame.Views.Game;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.travellingsalesmangame.Controllers.Game.ButtonCreater;
import com.travellingsalesmangame.Models.Game.Examples;
import com.travellingsalesmangame.Models.Game.ScreenSettings;
import com.travellingsalesmangame.R;


public class StateMenu_Fragment extends Fragment {

    private int levelSaved,levelClicked,stateSaved;
    private RelativeLayout stateMenuActivity;
    private SharedPreferences prefs;
    private View view;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private ScreenSettings screenSettings;

    private void setMenu() {
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GameActivity_Fragment game=new GameActivity_Fragment();

                Bundle bundle=new Bundle();
                bundle.putInt("levelSaved",levelSaved);
                bundle.putInt("stateSaved",stateSaved);
                bundle.putInt("levelClicked",levelClicked);
                bundle.putInt("stateClicked",v.getId());

                game.setArguments(bundle);

                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,game);
                transaction.commit();
            }
        };

        ButtonCreater buttonCreater=new ButtonCreater(view.getContext(),
                                                        stateMenuActivity,
                                                        onClickListener,
                                                        screenSettings);

        //kayıtlı level, secili levele esit ise statemenu aktivitisinin butonlarını kayıtlı state degerine gore olustur(renk,enable)
        if(levelSaved == levelClicked)
            buttonCreater.create(Examples.getCores()[levelClicked].length, stateSaved);
        else
            buttonCreater.create(Examples.getCores()[levelClicked].length,Examples.getCores()[levelClicked].length); //, değil ise hepsi yeşil ve enabled, dizi aşılıyor**

    }

    private void init() {

        Bundle bundle=getArguments();

        stateMenuActivity=view.findViewById(R.id.stateMenuActivity);
        prefs= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        screenSettings=new ScreenSettings(getActivity());

        levelSaved=bundle.getInt("levelSaved",0);   //kayıtlı level
        levelClicked=bundle.getInt("levelClicked",0);   //tiklanan level

        stateSaved=prefs.getInt("state",0);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_state_menu,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        setMenu();
        super.onActivityCreated(savedInstanceState);
    }


}

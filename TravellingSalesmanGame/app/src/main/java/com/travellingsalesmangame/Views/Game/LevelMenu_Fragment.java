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

public class LevelMenu_Fragment extends Fragment {

    private RelativeLayout levelMenuActivity;
    private SharedPreferences prefs;
    private ScreenSettings screenSettings;
    private View view;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private int level;

    private void init(){

        getActivity().setTitle("Oyun");
        screenSettings=new ScreenSettings(getActivity());
        levelMenuActivity=view.findViewById(R.id.levelMenuActivity);
        prefs= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        level=prefs.getInt("level",0);
    }

    private void setMenu(){
        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StateMenu_Fragment stateMenuFragment=new StateMenu_Fragment();

                Bundle bundle=new Bundle();
                bundle.putInt("levelSaved",level);
                bundle.putInt("levelClicked",v.getId());

                stateMenuFragment.setArguments(bundle);

                fragmentManager=getFragmentManager();
                transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.context_main,stateMenuFragment);
                transaction.commit();

            }
        };

        ButtonCreater buttonCreater=new ButtonCreater(view.getContext(),levelMenuActivity,onClickListener,screenSettings);
        buttonCreater.create(Examples.getCores().length,level);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.activity_level_menu,container,false);
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        setMenu();
        super.onActivityCreated(savedInstanceState);
    }
}

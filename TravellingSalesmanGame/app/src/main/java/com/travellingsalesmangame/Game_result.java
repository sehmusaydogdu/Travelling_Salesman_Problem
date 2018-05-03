package com.travellingsalesmangame;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.travellingsalesmangame.Views.Game.LevelMenu_Fragment;
import com.travellingsalesmangame.Views.Game.StateMenu_Fragment;

public class Game_result extends Fragment {

    private View view;
    private ImageView imgView;
    private TextView txtYorum,txtSure_Sonuc,txtPuan_Sonuc;
    private boolean levelState_Belirle;
    private int levelSaved,levelClicked;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private Button btn_Oyun;

    private void init(){

        getActivity().setTitle("Skor");
        imgView=view.findViewById(R.id.imgView);
        btn_Oyun=view.findViewById(R.id.btn_Oyun);

        txtYorum=view.findViewById(R.id.txtYorum);
        txtPuan_Sonuc=view.findViewById(R.id.txtPuan_Sonuc);
        txtSure_Sonuc=view.findViewById(R.id.txtSure_Sonuc);

        Bundle bundle =getArguments();
        txtYorum.setText(bundle.getString("yorum"));
        txtSure_Sonuc.setText(bundle.getString("sure"));
        txtPuan_Sonuc.setText(String.valueOf("Puan : "+bundle.getInt("puan")));
        levelState_Belirle=bundle.getBoolean("level_state_belirle");
        levelSaved=bundle.getInt("levelSaved",0);
        levelClicked=bundle.getInt("levelClicked",0);

        if(bundle.getInt("puan")>0)
            imgView.setImageResource(R.drawable.prize);

        else
            imgView.setImageResource(R.drawable.odul_kayip);

    }

    @Override
    public void onResume() {
        super.onResume();
        btn_Oyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(levelState_Belirle == true){
                    StateMenu_Fragment state=new StateMenu_Fragment();

                    Bundle bundle=new Bundle();
                    bundle.putInt("levelSaved",levelSaved);
                    bundle.putInt("levelClicked",levelClicked);

                    state.setArguments(bundle);
                    fragmentManager=getFragmentManager();
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.context_main,state);
                    transaction.commit();
                }
                else {

                    LevelMenu_Fragment level=new LevelMenu_Fragment();
                    fragmentManager=getFragmentManager();
                    transaction=fragmentManager.beginTransaction();
                    transaction.replace(R.id.context_main,level);
                    transaction.commit();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_game_result,container,false);
        init();
        return view;
    }
}

package com.project.travellingsalesman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class StateMenuActivity extends Activity{

    int levelSaved,levelClicked,stateSaved;

    private RelativeLayout layout;
    List<Button> buttons;
    private SharedPreferences prefs;
    private Intent intent;

    private ScreenLayout screenLayout;

    private void init(){
        screenLayout=new ScreenLayout();
        intent = getIntent();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        levelSaved = intent.getIntExtra("levelSaved",0);        //kayıtlı level
        levelClicked = intent.getIntExtra("levelClicked",0);    //tiklanan level
        stateSaved = prefs.getInt("state",0);                       //kayıtlı state(kayıtlı levelin kayıtlı son sorusu)

        layout = findViewById(R.id.stateMenuLayout);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_menu);

        init();
        setMenu();  //Level menu ile aynı islev
    }

    private void setMenu() {


        CreatorButtons creatorButtons = new CreatorButtons();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(StateMenuActivity.this,GameActivity.class);
                intent.putExtra("levelSaved",levelSaved); //kayıtlı level
                intent.putExtra("stateSaved",stateSaved); //kayıtlı state
                intent.putExtra("levelClicked",levelClicked); //seçili level
                intent.putExtra("stateClicked",v.getId()); //seçili state, kayıtlı level == secili level ve kayıtlı state == secili state ise oyun bitince seviye arttır game activityde.
                startActivity(intent);
                finish();
            }
        };

        if(levelSaved == levelClicked)      //kayıtlı level, secili levele esit ise statemenu aktivitisinin butonlarını kayıtlı state degerine gore olustur(renk,enable)
            buttons =  creatorButtons.create(this,
                                                     layout,
                                                     onClickListener,
                                                     Examples.getCores()[levelClicked].length,
                                                     stateSaved,
                                                     screenLayout.getScreenView(StateMenuActivity.this));
        else
            buttons =  creatorButtons.create(this,
                                                     layout,
                                                     onClickListener,
                                                     Examples.getCores()[levelClicked].length,
                                                     Examples.getCores()[levelClicked].length,
                                                     screenLayout.getScreenView(StateMenuActivity.this)); //, değil ise hepsi yeşil ve enabled, dizi aşılıyor**
    }
}

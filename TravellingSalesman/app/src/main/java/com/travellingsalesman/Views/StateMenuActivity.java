package com.travellingsalesman.Views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.travellingsalesman.Controllers.ButtonCreater;
import com.travellingsalesman.Models.Examples;
import com.travellingsalesman.Models.ScreenSettings;
import com.travellingsalesman.R;

public class StateMenuActivity extends Activity {


    private int levelSaved,levelClicked,stateSaved;
    private RelativeLayout stateMenuActivity;
    private SharedPreferences prefs;
    private ScreenSettings screenSettings;
    private Intent intent;

    private void init(){

        stateMenuActivity=findViewById(R.id.stateMenuActivity);
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        screenSettings=new ScreenSettings(this);
        intent=getIntent();

        levelSaved = intent.getIntExtra("levelSaved",0);        //kayıtlı level
        levelClicked = intent.getIntExtra("levelClicked",0);    //tiklanan level
        stateSaved = prefs.getInt("state",0); //kayıtlı state(kayıtlı levelin kayıtlı son sorusu)

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_menu);
        init();
        setMenu();
    }

    private void setMenu() {

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

        ButtonCreater buttonCreater=new ButtonCreater(this,
                                                              stateMenuActivity,
                                                              onClickListener,
                                                              screenSettings);

        //kayıtlı level, secili levele esit ise statemenu aktivitisinin butonlarını kayıtlı state degerine gore olustur(renk,enable)
        if(levelSaved == levelClicked)
            buttonCreater.create(Examples.getCores()[levelClicked].length, stateSaved);
        else
           buttonCreater.create(Examples.getCores()[levelClicked].length,Examples.getCores()[levelClicked].length); //, değil ise hepsi yeşil ve enabled, dizi aşılıyor**

    }
}

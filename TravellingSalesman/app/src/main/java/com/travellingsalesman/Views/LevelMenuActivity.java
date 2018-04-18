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


public class LevelMenuActivity extends Activity{

    private RelativeLayout levelMenuActivity;
    private SharedPreferences prefs;
    private ScreenSettings screenSettings;
    private int level;

    private void init(){
        screenSettings=new ScreenSettings(this);
        levelMenuActivity=findViewById(R.id.levelMenuActivity);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        level=prefs.getInt("level",0); // Kayıtlı level
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        init();
        setMenu();
    }

    private void setMenu() {

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LevelMenuActivity.this,StateMenuActivity.class);
                intent.putExtra("levelSaved",level);//kayıtlı level, kullanıcının en son geldigi level
                intent.putExtra("levelClicked",v.getId());
                //tiklanan level, kullanıcının oynamak için seçtiği level,
                // kayıtlı ve secili level esit ise 2. aktivite ona gore olusturulacak.
                startActivity(intent);
                finish();
            }
        };

        ButtonCreater buttonCreater=new ButtonCreater(this,
                                                       levelMenuActivity,
                                                       onClickListener,
                                                       screenSettings);

        buttonCreater.create(Examples.getCores().length,level);
    }

}

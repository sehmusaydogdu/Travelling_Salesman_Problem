package com.project.travellingsalesman;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class LevelMenuActivity extends Activity{

    private RelativeLayout layout;
    ArrayList<Button> buttons;
    private SharedPreferences prefs;
    private ScreenLayout screenLayout;
    private int level;

    private void init(){

        screenLayout=new ScreenLayout();
        layout = findViewById(R.id.levelMenuLayout);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        level = prefs.getInt("level",0); //kayıtlı level
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);

        init();
        setMenu();
        //butonlar icin onclick listener oluşturma ve
        // layouta bu özelliklerde butonlar atanması icin creatorButtons sınıfını kullanma.
    }

    private void setMenu() {

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LevelMenuActivity.this,StateMenuActivity.class);
                intent.putExtra("levelSaved",level);        //katıtlı level, kullanıcının en son geldigi level
                intent.putExtra("levelClicked",v.getId());  //tiklanan level, kullanıcının oynamak için seçtiği level, kayıtlı ve secili level esit ise 2. aktivite ona gore olusturulacak.
                startActivity(intent);
                finish();
            }
        };

        CreatorButtons creatorButtons = new CreatorButtons();
        buttons = creatorButtons.create(this,layout, onClickListener, Examples.getCores().length,level,screenLayout.getScreenView(LevelMenuActivity.this));
        //layouta dinamik olarak buton ekleme
    }
}

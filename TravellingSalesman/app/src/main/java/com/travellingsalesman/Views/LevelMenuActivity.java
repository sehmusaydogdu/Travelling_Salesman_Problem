package com.travellingsalesman.Views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.travellingsalesman.R;

import java.util.List;
import java.util.prefs.Preferences;

public class LevelMenuActivity extends Activity implements View.OnClickListener{

    private RelativeLayout levelMenuActivity;
    protected List<Button> buttons;
    private SharedPreferences prefs;
    private int level;

    private void init(){

        levelMenuActivity=findViewById(R.id.levelMenuActivity);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        level=prefs.getInt("level",0); // Kayıtlı level
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_menu);
        init();
    }


    @Override
    public void onClick(View v) {

        Intent intent=new Intent(LevelMenuActivity.this,StateMenuActivity.class);
        intent.putExtra("levelSaved",level);//kayıtlı level, kullanıcının en son geldigi level

    }
}

package com.travellingsalesman.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.travellingsalesman.Controllers.Stage;
import com.travellingsalesman.Models.Core;
import com.travellingsalesman.Models.Examples;
import com.travellingsalesman.Models.ScreenSettings;
import com.travellingsalesman.R;

import java.util.List;

public class GameActivity extends Activity {

    private RelativeLayout gameActivity,layoutDraw;
    private Core core;
    private Stage stage;
    private Intent intent;
    private List<ImageButton> buttons,selectedButtons;
    private ImageButton oldButton;
    private ScreenSettings screenSettings;
    private int levelSaved,levelClicked,stateSaved,stateClicked,click_count=0,totalScore=0;

    private void init(){

        gameActivity=findViewById(R.id.gameActivity);
        layoutDraw=findViewById(R.id.layoutDraw);

        core = Examples.getCores()[levelClicked][stateClicked];
        stage = new Stage(this); //seviye atlama işlemi için gerekli

        intent=getIntent();
        levelSaved = intent.getIntExtra("levelSaved",0);
        levelClicked = intent.getIntExtra("levelClicked",0);
        stateSaved = intent.getIntExtra("stateSaved",0);
        stateClicked = intent.getIntExtra("stateClicked",0);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}

package com.travellingsalesman.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.travellingsalesman.Controllers.ButtonCreater;
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

        screenSettings=new ScreenSettings(this);

        Intent intent=getIntent();
        levelSaved = intent.getIntExtra("levelSaved",0);
        levelClicked = intent.getIntExtra("levelClicked",0);
        stateSaved = intent.getIntExtra("stateSaved",0);
        stateClicked = intent.getIntExtra("stateClicked",0);

        stage = new Stage(this); //seviye atlama işlemi için gerekli
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        createLayout();
        setLayout();
    }


    private void createLayout() {

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action((ImageButton) v);
            }
        };

        ButtonCreater buttonCreater=new ButtonCreater(this,
                                                        gameActivity,
                                                        onClickListener,
                                                        screenSettings);
        buttonCreater.create(35); //35 tane button oluşturacak
        buttons=buttonCreater.getGameButonList();//Tüm oluşan butonları aldım.
    }


    private void setLayout() {
        DrawView.setVisible(buttons,core.getCities());
    }

    private void action(ImageButton v) {
    }
}

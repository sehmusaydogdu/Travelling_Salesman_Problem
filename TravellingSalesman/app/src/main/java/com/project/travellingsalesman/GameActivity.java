package com.project.travellingsalesman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class GameActivity extends Activity {

    RelativeLayout layout,layoutDraw;
    Core core;
    Stage stage;
    Intent intent;
    int levelSaved,levelClicked,stateSaved,stateClicked,click_count=0,totalScore=0;
    ArrayList<ImageButton> buttons,selectedButtons;
    ImageButton oldButton;
    private ScreenLayout screenLayout;

    private void init() {

        screenLayout=new ScreenLayout();
        selectedButtons = new ArrayList<>();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        Intent intent = getIntent();
        levelSaved = intent.getIntExtra("levelSaved",0);
        levelClicked = intent.getIntExtra("levelClicked",0);
        stateSaved = intent.getIntExtra("stateSaved",0);
        stateClicked = intent.getIntExtra("stateClicked",0);

        layout = findViewById(R.id.layout);
        layoutDraw = findViewById(R.id.layoutDraw);
        core = Examples.getCores()[levelClicked][stateClicked];
        createLayout();
        setLayout();

        stage = new Stage(this);                //seviye atlama işlemi için gerekli

    }

    //Layoutu olusturma methodu
    private void createLayout() {

        //layout.setBackgroundResource(R.drawable.background1);
        CreatorButtons creatorButtons = new CreatorButtons();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action((ImageButton) v);
            }
        };
        //levelmenu ve statemenu aktiviteleri ile benzer islev, buton olusturma
        buttons = creatorButtons.create(this,
                                                layout,
                                                onClickListener,
                                           35,
                                                screenLayout.point(GameActivity.this));

    }



    private void action(ImageButton button) {

        if(selectedButtons.contains(button))
            DisplayMessage.show(this,"Uyarı","Seçili Şehre Tıkladınız",null);

        else{
            if (oldButton==null){       //ilk kez buton tiklama
                oldButton=button;
                selectedButtons.add(button);
                button.setImageResource(R.mipmap.home1);
                click_count++;
            }
            else{

                int pathCost = PathCosts(core.getCosts(), buttons.indexOf(oldButton), buttons.indexOf(button));

                if(pathCost >0){    //iki buton arsı yol var ise

                    if(click_count != 1) {
                        removeLayoutCosts(oldButton);                      //tiklanan buton 2veya büyükse eskilerin maliyetlerini sil
                        oldButton.setImageResource(R.mipmap.home2);     //eski butonun resmini değiştirme
                    }

                    totalScore+= pathCost;      //toplam yol
                    click_count++;
                    DrawView drawView = new DrawView(this, oldButton, button, Color.RED, 10);
                    layout.addView(drawView);

                    if(click_count==core.getCities().length)    //en son baslanan yola tiklanabilmesi icin ilk butonu tiklananlar listesinden cıkarma islemi
                        selectedButtons.remove(0);

                    showLayoutCosts(button);

                    oldButton=button;
                    button.setImageResource(R.mipmap.home3);
                    selectedButtons.add(button);

                    if (click_count==core.getCities().length+1)
                    {
                        if((levelSaved == levelClicked) && (stateSaved == stateClicked))    //son levelin son sorusu oynandı ise seviye arttır
                            stage.up();

                        if(stateClicked == Examples.getCores()[levelClicked].length-1)                          //oynanan oyun levelin son oyunu ise levelmenuye değilse statemenuye dön
                            intent = new Intent(GameActivity.this,LevelMenuActivity.class);      //levelmenuye geri dön.
                        else {
                            intent = new Intent(GameActivity.this,StateMenuActivity.class);      //statemenuye geri dön.
                            intent.putExtra("levelSaved",levelSaved);        //katıtlı level, kullanıcının en son geldigi level
                            intent.putExtra("levelClicked",levelClicked);  //tiklanan level, kullanıcının oynamak için seçtiği level, kayıtlı ve secili level esit ise 2. aktivite ona gore olusturulacak.
                        }

                        DisplayMessage.show(this,"Oyun","Skorunuz :  "+totalScore+"  Gerçek Skor :  "+core.getSolution(),intent);  //intent = level menu olmalı, display methodunu kodla önce.
                    }
                }
                else
                    DisplayMessage.show(this,"Uyarı","Yol Yoktur",null);
            }
        }
    }

    //Her sorunun maliyetlere eişiyorum.
    public static int PathCosts(int [][] costs,int start,int finish){
        for (int i = 0; i < costs.length; i++) {
            if ((costs[i][0]==start && costs[i][1]==finish) || (costs[i][1]==start && costs[i][0]==finish))
                return costs[i][2];
        }
        return 0;
    }

    //gosterilecek oyuna gore layoutu ayarlama, sehirler, malyetler, cizgiler...
    private void setLayout() {

        CitiesSetter.setInvisible(buttons);
        CitiesSetter.setVisible(buttons,core.getCities());

        CostsSetter.setCosts(this, buttons, layoutDraw, core.getCosts());
        changeBackground();
    }

    private void removeLayoutCosts(ImageButton oldButton) {


        for (CostsSetter.Draw draw: CostsSetter.drawArrayList) {

            if(buttons.get(buttons.indexOf(oldButton)).equals(draw.drawView.getStartView()) || buttons.get(buttons.indexOf(oldButton)).equals(draw.drawView.getEndView())) {

                layoutDraw.removeView(draw.drawView);
                layoutDraw.removeView(draw.textView);
            }
        }
    }

    private void showLayoutCosts(ImageButton button) {


        for (CostsSetter.Draw draw: CostsSetter.drawArrayList) {

            if(buttons.get(buttons.indexOf(button)).equals(draw.drawView.getStartView()) || buttons.get(buttons.indexOf(button)).equals(draw.drawView.getEndView())) {

                if(draw.drawView.getParent() != null && draw.drawView.getStartView() != selectedButtons.get(0)) {


                    layoutDraw.removeView(draw.drawView);
                    layoutDraw.removeView(draw.textView);
                    draw.drawView.setColor(getResources().getColor(R.color.deep_pink));
                    draw.drawView.setWidth(10);
                    draw.textView.setTextColor(getResources().getColor(R.color.black));
                    draw.textView.setTextSize(20);
                    draw.textView.setTypeface(null, Typeface.BOLD);
                    layoutDraw.addView(draw.drawView);
                    layoutDraw.addView(draw.textView);
                }

            }
        }
    }

    int click=0;
    private void changeBackground() {

        Button button = new Button(this);
        button.setX(20);
        button.setY(20);
        button.setText("BG");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                click++;
                if(click%3==0)
                    layout.setBackgroundResource(R.drawable.background1);
                else if(click%3==1)
                    layout.setBackgroundResource(R.drawable.background2);
                else
                    layout.setBackgroundResource(R.drawable.background3);

            }
        };
        button.setOnClickListener(onClickListener);
        layout.addView(button);
    }

}

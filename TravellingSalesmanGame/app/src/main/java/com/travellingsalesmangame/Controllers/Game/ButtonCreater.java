package com.travellingsalesmangame.Controllers.Game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.travellingsalesmangame.Models.Game.ScreenSettings;
import com.travellingsalesmangame.R;

import java.util.ArrayList;
import java.util.List;

public class ButtonCreater {

    private Context context;
    private RelativeLayout layout;
    private View.OnClickListener onClickListener;
    private ScreenSettings settings;

    private List<ImageButton> gameButonList;

    public ButtonCreater(Context context,
                         RelativeLayout layout,
                         View.OnClickListener onClickListener,
                         ScreenSettings settings){
        this.context=context;
        this.layout=layout;
        this.onClickListener=onClickListener;
        this.settings=settings;

        this.layout.setClipToPadding(false);
    }


    //level ve state menuleri icin methot
    public void create(int size,int value){

        ButtonSetter buttonSetter=new ButtonSetter(0,settings);
        for(int i=0;i<size;i++) {

            Button button = new Button(context);
            button.setId(i);
            button.setText(String.valueOf(i+1));
            buttonSetter.setView(0,button);
            button.setOnClickListener(onClickListener);

            if(i<value)
                button.setBackgroundColor(Color.GREEN);
            else if(i==value)
                button.setBackgroundColor(Color.YELLOW);
            else {
                button.setBackgroundColor(Color.LTGRAY);
                button.setEnabled(false);
            }
            layout.addView(button);
        }
    }

    @SuppressLint("NewApi") /////game aktivitesi icin methot
    public void create(int size, int[] cities){

        gameButonList=new ArrayList<>();
        ButtonSetter buttonSetter=new ButtonSetter(1,settings);
        for(int i=0;i<size+5;i++) {

            ImageButton button = new ImageButton(context);
            buttonSetter.setView(1,button);
            button.setVisibility(View.INVISIBLE);
            button.setImageResource(R.mipmap.home0);
            button.setBackground(null);
            button.setPadding(0,0,0,0);
            button.setElevation(24);
            button.setOnClickListener(onClickListener);
            layout.addView(button);
            gameButonList.add(button);
        }

        for (int city : cities)
            gameButonList.get(city).setVisibility(View.VISIBLE);

    }


    //Oyun sırasında oluşan imageButonların Listesini gönderiyorum.
    public List<ImageButton> getGameButonList(){
        return gameButonList;
    }

}


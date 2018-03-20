package com.project.travellingsalesman;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

class CreatorButtons {

    //int alan methot menuler icin, float alan oyun icin

    ArrayList<Button> create(Context context, RelativeLayout layout, View.OnClickListener onClickListener, int size, int value,Point point) {
        //level ve state menuleri icin methot

        ArrayList<Button> buttons = new ArrayList<>();
        ButtonSetter buttonSetter = new ButtonSetter(0,point);

        for(int i=0;i<size;i++) {

            Button button = new Button(context);                                //Şık olması için iöage button yaparız
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
            buttons.add(button);
        }
        return buttons;
    }

    @SuppressLint("NewApi") //game aktivitesi icin methot
    ArrayList<ImageButton> create(Context context, RelativeLayout layout, View.OnClickListener onClickListener, int size, Point point) {

        ButtonSetter buttonSetter = new ButtonSetter(1,point);
        ArrayList<ImageButton> buttons = new ArrayList<>();

        for(int i=0;i<size;i++) {

            ImageButton button = new ImageButton(context);
            buttonSetter.setView(1,button);

            button.setImageResource(R.mipmap.home0);
            button.setBackground(null);
            button.setPadding(0,0,0,0);
            button.setElevation(24);
            button.setOnClickListener(onClickListener);

            layout.addView(button);
            buttons.add(button);
        }
        return buttons;
    }
}

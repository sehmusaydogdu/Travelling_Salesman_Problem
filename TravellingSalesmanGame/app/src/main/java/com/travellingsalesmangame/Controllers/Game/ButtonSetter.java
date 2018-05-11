package com.travellingsalesmangame.Controllers.Game;


import android.graphics.Point;
import android.view.View;
import com.travellingsalesmangame.Models.Game.ScreenSettings;

public class ButtonSetter {

    private int x,y,width,height;
    private int constX,constY;
    private Point point;

    //Her activity için bir kere çalışıyor.
    public ButtonSetter(int i, ScreenSettings screenView){
        point=screenView.getPoint();
        if(i==0) levelStateSetter(screenView);
        if(i==1) imageButtonSetter(screenView);
    }

    private void imageButtonSetter(ScreenSettings screenView) {

        if (screenView.getDensity() > 4.0) {
            //return "xxxhdpi";
        }
        else if (screenView.getDensity() >3.0 && screenView.getDensity()<=4.0) {  //Tmmdır
            //return "xxhdpi";
            constX=30;constY=200; width=248; height=200; x=constX; y=constY;
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<=3.0) {  //Tmmdır
            //"xhdpi";
            constX=30;constY=155; width=175; height=145; x=constX; y=constY;
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) { //Tmmdir
            //return "hdpi";
            constX=30;constY=117; width=115; height=100; x=constX; y=constY;
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {  //Tmmdır
            // return "mdpi";
            constX=30;constY=80; width=58; height=55; x=constX; y=constY;
        }
        //return "ldpi";
    }

    private void gameButtonView(View button) {

        button.setX(x);
        button.setY(y);

        if(constX*2+width+x<point.x)
            x+=constX+width;

        else{
            x=constX;
            y+=constY/4+height;
        }
    }

    private void levelStateSetter(ScreenSettings screenView){

        if (screenView.getDensity() > 4.0) {
            //return "xxxhdpi";
        }
        else if (screenView.getDensity() >= 3.0 && screenView.getDensity()<=4.0) { //Oley
            //return "xxhdpi";
            constX=70;constY=70; width=280; height=250; x=constX; y=constY;
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<3.0) {  //Oley
            //"xhdpi";
            constX=100;constY=50; width=250; height=200; x=constX; y=constY;
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) {  //oley
            //return "hdpi";
            constX=60;constY=60; width=200; height=150; x=constX; y=constY;
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {  //Oley
            // return "mdpi";
            constX=50;constY=30; width=130; height=80; x=constX; y=constY;
        }
        //return "ldpi";
    }

    private void levelStateSetView(View button) {

        button.setX(x);
        button.setY(y);
        if(constX*2+width+x<point.x){
            x=((point.x-(constX*2+width)))+constX;
        }
        else{
            x=constX;
            y+=constY+height;
        }
    }

    //Her button oluştuğunda çağırıyor.
    public void setView(int i, View button) {
        if(i==0) levelStateSetView(button);
        if(i==1) gameButtonView(button);
    }
}

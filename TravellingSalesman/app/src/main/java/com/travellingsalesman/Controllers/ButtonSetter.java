package com.travellingsalesman.Controllers;

import android.graphics.Point;
import android.view.View;
import android.widget.Button;

import com.travellingsalesman.Models.ScreenSettings;

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
            //return "xxxhd-ğipi";
        }
        else if (screenView.getDensity() > 3.0 && screenView.getDensity()<=4.0) {
            //return "xxhdpi";
            constX=30;constY=30; width=280; height=280; x=constX;y=constY;
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<=3.0) {
            //"xhdpi";
            constX=30;constY=30; width=200; height=200; x=constX;y=constY;
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) {
            //return "hdpi";
            constX=30;constY=30; width=120; height=120; x=constX;y=constY;
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {
            // return "mdpi";
            constX=30;constY=30; width=62; height=62; x=constX;y=constY;
        }
        //return "ldpi";
    }

    private void levelStateSetter(ScreenSettings screenView){

        if (screenView.getDensity() > 4.0) {
            //return "xxxhdpi";
        }
        else if (screenView.getDensity() > 3.0 && screenView.getDensity()<=4.0) {
            //return "xxhdpi";
            constX=70;constY=70; width=280; height=280; x=constX;y=constY;
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<=3.0) {
            //"xhdpi";
            constX=50;constY=50; width=200; height=200; x=constX;y=constY;
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) {
            //return "hdpi";
            constX=30;constY=30; width=150; height=150; x=constX;y=constY;
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {
            // return "mdpi";
            constX=30;constY=30; width=80; height=80; x=constX;y=constY;
        }
        //return "ldpi";
    }




    //Her button oluştuğunda çağırıyor.
    public void setView(int i, View button) {
        if(i==0) levelStateSetView(button);
        if(i==1) gameButtonView(button);
    }


    private void gameButtonView(View button) {

        button.setX(x);
        button.setY(y);

        if(constX*2+width+x<point.x)
            x+=constX+width;

        else{
            x=constX;
            y+=constY+height;
        }
    }

    private void levelStateSetView(View button) {

        button.setX(x);
        button.setY(y);
        if(constX*2+width+x<point.x){
            x+=constX+width;
        }
        else{
            x=constX;
            y+=constY+height;
        }

    }

}

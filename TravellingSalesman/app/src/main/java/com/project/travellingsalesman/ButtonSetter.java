package com.project.travellingsalesman;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.view.View;

class ButtonSetter {

    private int x,y,width,height,count;
    private int constX,constY;
    private Point point;



    ButtonSetter(int i,ScreenView screenView) {
        point=screenView.getPoint();
        count=0;
        if(i==0) levelStateSetter(screenView);
        if(i==1) imageButtonSetter(screenView);
    }

    private void imageButtonSetter(ScreenView screenView) {

        if (screenView.getDensity() > 4.0) {
            //return "xxxhdpi";
        }
        else if (screenView.getDensity() > 3.0 && screenView.getDensity()<=4.0) {
            //return "xxhdpi";
            constX=30;constY=30; setWidth(280);setHeight(280);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<=3.0) {
            //"xhdpi";
            constX=30;constY=30; setWidth(200);setHeight(200);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) {
            //return "hdpi";
            constX=30;constY=30; setWidth(120);setHeight(120);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {
            // return "mdpi";
            constX=30;constY=30; setWidth(62);setHeight(62);setX(constX);setY(constY);
        }
        //return "ldpi";
    }


    private void levelStateSetter(ScreenView screenView){

        if (screenView.getDensity() > 4.0) {
            //return "xxxhdpi";
        }
        else if (screenView.getDensity() > 3.0 && screenView.getDensity()<=4.0) {
            //return "xxhdpi";
            constX=70;constY=70; setWidth(280);setHeight(280);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() > 2.0 && screenView.getDensity()<=3.0) {
            //"xhdpi";
            constX=50;constY=50; setWidth(200);setHeight(200);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() > 1.5  && screenView.getDensity()<=2.0) {
            //return "hdpi";
            constX=30;constY=30; setWidth(150);setHeight(150);setX(constX);setY(constY);
        }
        else if (screenView.getDensity() >= 1.0 && screenView.getDensity()<=1.5) {
            // return "mdpi";
            constX=30;constY=30; setWidth(80);setHeight(80);setX(constX);setY(constY);
        }
        //return "ldpi";
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setView(int i,View button) {
        if(i==0) levelStateSetView(button);
        if(i==1) gameButtonView(button);
    }

    private void gameButtonView(View button) {

        count++;

        button.setX(x);
        button.setY(y);

        /*if (count==5){
            count=0;
            x=constX;
            y+=constY+height;
        }
        else
            x+=constX+width;*/

        if(constX*2+width+x<point.x){
            x+=constX+width;
        }
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

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }
}

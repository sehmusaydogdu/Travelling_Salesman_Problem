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
        count=0;
        if(i==0) levelStateSetter(screenView);
        if(i==1) imageButtonSetter(screenView);
    }

    private void imageButtonSetter(ScreenView screenView) {

        point=screenView.getPoint();

        constX=30;
        constY=30;


        setWidth(200);
        setHeight(200);

        setX(constX);
        setY(constY);
    }


    private void levelStateSetter(ScreenView screenView){
        constX=50;
        constY=50;
        point=screenView.getPoint();

        setWidth(200);
        setHeight(200);

        setX(constX);
        setY(constY);
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

package com.project.travellingsalesman;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;


public class ScreenLayout{

    private ScreenView screenView;

    public ScreenLayout(){ screenView=new ScreenView(); }

    //Gönderilen ekranın ihtiyacımız olan özellikleri ScreenView nesnesine doldurulmasını sağlar
    public ScreenView getScreenView(Activity context){

        Display display=context.getWindowManager().getDefaultDisplay();
        Point point=new Point();
        display.getSize(point);

        screenView.setPoint(point);
        screenView.setDensity(context.getResources().getDisplayMetrics().density);

        return screenView;
    }
}

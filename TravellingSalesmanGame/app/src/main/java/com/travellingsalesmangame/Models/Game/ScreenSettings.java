package com.travellingsalesmangame.Models.Game;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class ScreenSettings {

    //Aslında bir MODEL, ekranın çözünürlüğünü alarak istediğimiz gibi kullanmamızı sağlar.
    //density => Ekranın çözünürlüğünü ele alır.
    // Point  => Ekranın X(Genişlik) ve Y(Yükseklik) ele alır.


    private float density;
    private Point point;

    public Point getPoint() {
        return point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }


    public float getDensity() {
        return density;
    }
    public void setDensity(float density) {
        this.density = density;
    }



    public ScreenSettings(Activity context){

        Point point=new Point();//Ekranın genişliğini ve yüksekliğini alıyorum.

        Display display=context.getWindowManager().getDefaultDisplay();
        display.getSize(point);

        this.setDensity(context.getResources().getDisplayMetrics().density);
        this.setPoint(point);
    }

}

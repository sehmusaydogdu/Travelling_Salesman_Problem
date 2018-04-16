package com.project.travellingsalesman;

import android.graphics.Point;


//Aslında bir MODEL, ekranın çözünürlüğünü alarak istediğimiz gibi kullanmamızı sağlar.
//density => Ekranın çözünürlüğünü ele alır.
// Point  => Ekranın X(Genişlik) ve Y(Yükseklik) ele alır.

public class ScreenView {

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
}

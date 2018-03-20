package com.project.travellingsalesman;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class ScreenLayout extends Activity{

     public Point point(Activity context){
         Display display=context.getWindowManager().getDefaultDisplay();
         Point p=new Point();
         display.getSize(p);
         return p;
     }

}

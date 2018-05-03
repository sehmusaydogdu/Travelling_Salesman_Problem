package com.travellingsalesmangame.Views.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

public class DrawView extends View {

    private Paint paint;
    private View startView,endView;

    public DrawView(Context context, View startView, View endView, int color, float width){
        super(context);

        this.startView=startView;
        this.endView=endView;

        paint=new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(width);

    }

    public void onDraw(Canvas canvas){

        canvas.drawLine(startView.getX()+startView.getWidth()/2,
                        startView.getY()+startView.getHeight()/2,
                        endView.getX()+endView.getWidth()/2,
                        endView.getY()+endView.getHeight()/2,paint);
    }

    public View getStartView() { return startView; }

    public View getEndView() { return endView; }

    public void setColor(int color) { paint.setColor(color); }

    public void setWidth(int width) { paint.setStrokeWidth(width); }
}

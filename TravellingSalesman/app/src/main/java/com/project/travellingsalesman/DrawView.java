package com.project.travellingsalesman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


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


    protected View getStartView() { return startView; }

    protected View getEndView() { return endView; }


    protected void setColor(int color) { paint.setColor(color); }

    protected void setWidth(int width) { paint.setStrokeWidth(width); }
}


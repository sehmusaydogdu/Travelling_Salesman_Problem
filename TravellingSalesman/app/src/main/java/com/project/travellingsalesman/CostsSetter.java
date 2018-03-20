package com.project.travellingsalesman;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

class CostsSetter {

    static ArrayList<Draw> drawArrayList;

    CostsSetter(GameActivity gameActivity, ArrayList<ImageButton> buttons, RelativeLayout layout, int[][] costs) {

        setCosts(gameActivity,buttons,layout,costs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    static void setCosts(GameActivity gameActivity, ArrayList<ImageButton> buttons, RelativeLayout layout, int[][] costs) {

        drawArrayList = new ArrayList<>();
        DrawView drawView;
        Draw draw;


        for (int[] cost : costs) {

            TextView textView = new TextView(gameActivity);
            textView.setText(String.valueOf(cost[2]));
            textView.setTextSize(14);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(30,18,0,0);
            textView.setBackground(null);
            //textView.setTypeface(null, Typeface.BOLD);
            //textView.setBackgroundColor(Color.WHITE);                                    //özelleştirmek lazım
            textView.setElevation(24);
            textView.setX((buttons.get(cost[0]).getX() + buttons.get(cost[1]).getX()) / 2);
            textView.setY((buttons.get(cost[0]).getY() + buttons.get(cost[1]).getY()) / 2);

            drawView = new DrawView(gameActivity, buttons.get(cost[0]), buttons.get(cost[1]), Color.LTGRAY, 3);
            draw = new Draw();
            draw.setDrawView(drawView);
            draw.setTextView(textView);

            drawArrayList.add(draw);

            layout.addView(draw.drawView);
            layout.addView(draw.textView);
        }
    }

    static class Draw {

        TextView textView;
        DrawView drawView;

        void setDrawView(DrawView drawView) {
            this.drawView = drawView;
        }

        void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}

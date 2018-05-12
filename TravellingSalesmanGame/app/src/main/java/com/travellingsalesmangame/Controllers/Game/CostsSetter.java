package com.travellingsalesmangame.Controllers.Game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.widget.ImageButton;
import android.widget.TextView;

import com.travellingsalesmangame.Views.Game.DrawView;

import java.util.ArrayList;
import java.util.List;

public class CostsSetter {

    public static List<Draw> drawList;
    private static DrawView drawView;
    private static Draw draw;

    /*
        public CostsSetter(GameActivity gameActivity,
                           List<ImageButton> buttons,
                           RelativeLayout layout, int[][] costs){

            //setCosts(gameActivity,buttons,layout,costs);
        }

        @SuppressLint("NewApi")
        public static void setCosts(Context context,
                                    List<ImageButton> buttons,
                                    RelativeLayout layout, int[][] costs) {

            drawList=new ArrayList<>();
            for (int[] cost : costs) {

                TextView textView = new TextView(context);
                textView.setText(String.valueOf(cost[2]));
                textView.setTextSize(15);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(30,18,0,0);
                textView.setBackground(null);
                textView.setElevation(24);
                textView.setX((buttons.get(cost[0]).getX() + buttons.get(cost[1]).getX()) / 2);
                textView.setY((buttons.get(cost[0]).getY() + buttons.get(cost[1]).getY()) / 2);

                drawView = new DrawView(context, buttons.get(cost[0]), buttons.get(cost[1]), Color.LTGRAY, 3);
                draw = new Draw();
                draw.setDrawView(drawView);
                draw.setTextView(textView);

                drawList.add(draw);

                //layout.addView(draw.drawView);
                //layout.addView(draw.textView);

            }
        }
    */
    @SuppressLint("NewApi")
    public static List<Draw> getDrawList(Context context,
                                         List<ImageButton> buttons,
                                         int[][] costs) {

        drawList=new ArrayList<>();
        for (int[] cost : costs) {

            TextView textView = new TextView(context);
            textView.setText(String.valueOf(cost[2]));
            textView.setTextSize(15);
            textView.setTextColor(Color.WHITE);
            textView.setPadding(30,18,0,0);
            textView.setBackground(null);
            textView.setElevation(24);
            textView.setX((buttons.get(cost[0]).getX() + buttons.get(cost[1]).getX()) / 2);
            textView.setY((buttons.get(cost[0]).getY() + buttons.get(cost[1]).getY()) / 2);

            drawView = new DrawView(context, buttons.get(cost[0]), buttons.get(cost[1]), Color.LTGRAY, 3);
            draw = new Draw();
            draw.setDrawView(drawView);
            draw.setTextView(textView);

            drawList.add(draw);

            //layout.addView(draw.drawView);
            //layout.addView(draw.textView);
        }

        return drawList;
    }

    public static class Draw {

        public TextView textView;
        public DrawView drawView;

        void setDrawView(DrawView drawView) {
            this.drawView = drawView;
        }

        void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}

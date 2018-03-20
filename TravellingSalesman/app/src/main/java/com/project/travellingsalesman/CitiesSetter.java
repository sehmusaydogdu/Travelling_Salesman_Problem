package com.project.travellingsalesman;

import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

class CitiesSetter {

    CitiesSetter(ArrayList<ImageButton> buttons, int[] coreList) {

        setInvisible(buttons);
        setVisible(buttons,coreList);
    }

    static void setVisible(ArrayList<ImageButton> buttons, int[] coreList) {

        for (int cities : coreList)
            buttons.get(cities).setVisibility(View.VISIBLE);
    }

    static void setInvisible(ArrayList<ImageButton> buttons) {

        /*for (ImageButton button :buttons)
            button.setVisibility(View.INVISIBLE);
    */}
}
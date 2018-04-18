package com.travellingsalesman.Views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class DisplayMessage {

    //tam çalışmıyor bakılması gerek.

    //Oyun bitince level menuye dönmeye yarayacak methot
    static void show(final Context context, String title, String text, final Intent intent){

        AlertDialog alertMessage = new AlertDialog.Builder(context,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).create();
        alertMessage.setTitle(title);
        alertMessage.setMessage(text);
        alertMessage.setCancelable(false);
        //alertMessage.setIcon(R.mipmap.information); // icon atanacak
        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"Tamam", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {

                if(intent != null) {
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            }
        });
        alertMessage.show();
    }
}

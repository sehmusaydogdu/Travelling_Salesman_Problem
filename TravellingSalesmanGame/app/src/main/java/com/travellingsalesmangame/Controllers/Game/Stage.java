package com.travellingsalesmangame.Controllers.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.travellingsalesmangame.Models.Game.Examples;

public class Stage {
    private static int level,state;

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private boolean isFinished=false; //butun oyunun bitme durumu = true

    public Stage(Context context) {
        super();

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        level = prefs.getInt("level",0);
        state = prefs.getInt("state",0);
    }

    public void up() {                  //seviye atlama işlevi,

        prefsEditor = prefs.edit();

        if(Examples.getCores()[level].length-1 == state) {  //bulunan levelde baska soru yoksa, yani geçilen soru son soru ise level atla
            levelUp();
        }
        else {                                      //değil ise state arttır, sıradaki state-soru sonraki soru olsun, level aynı kalsın.
            state++;
            prefsEditor.putInt("state",state);
            prefsEditor.apply();
        }
    }

    private void levelUp() {

        if(level < ((Examples.getCores().length))) {    //level, olusturulan örneklerdeki level sayısı kadar artabilsin,level artınca state 0 lancak, yeni level yeni sorular***
            //Normalde length-1 olması lazım yoksa Examples.getCores(level).length sınırı aşar ancak 1 - bu kod kullanılmıyor, 2 - son state veya son level sarı kalmasın diye böyle.
            state=0;
            level++;
            prefsEditor.putInt("state",state);
            prefsEditor.putInt("level",level);
            prefsEditor.apply();
        }
        if(level == Examples.getCores().length) //bütün oyunun bitme durumu.
            isFinished = true;
    }

    public boolean isFinished() {   //***get metodu yerine tebrikler yazan aktivite çağırılabilinir.****
        return isFinished;
    }
}

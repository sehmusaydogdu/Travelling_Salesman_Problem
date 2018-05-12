package com.travellingsalesmangame.Views.Game;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.travellingsalesmangame.Controllers.Game.ButtonCreater;
import com.travellingsalesmangame.Controllers.Game.CostsSetter;
import com.travellingsalesmangame.Controllers.Game.Stage;
import com.travellingsalesmangame.Models.Game.Core;
import com.travellingsalesmangame.Models.Game.Examples;
import com.travellingsalesmangame.Models.Game.Result;
import com.travellingsalesmangame.Models.Game.ScreenSettings;
import com.travellingsalesmangame.Models.Game.TimeSpan;
import com.travellingsalesmangame.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GameActivity_Fragment extends Fragment {

    private RelativeLayout gameActivity,layoutDraw;
    private Core core;
    private Stage stage;
    private List<ImageButton> buttons,selectedButtons;
    private List<CostsSetter.Draw> drawList;
    private ImageButton oldButton;
    private ScreenSettings screenSettings;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private DisplayMessage message;

    private View view;
    private int levelSaved,levelClicked,stateSaved,stateClicked,click_count=0;
    private double totalScore=0;

    private Calendar startDate,endDate;
    private Handler handler=new Handler();
    private boolean sureDoldu=false,stop=false;
    private ProgressBar progressBar;

    private TextView txtTimeSpan,txtSkorPuan;
    private boolean level_state_belirle=false;  //Eğer False ise Level Menuye Geri Dön
                                                //Eğer True ise State Menuye Geri Dön

    private void init() {

        Bundle bundle=getArguments();
        levelSaved=bundle.getInt("levelSaved",0);
        levelClicked=bundle.getInt("levelClicked",0);
        stateSaved=bundle.getInt("stateSaved",0);
        stateClicked=bundle.getInt("stateClicked",0);

        core= Examples.getCores()[levelClicked][stateClicked];

        //Zaman -- 1
        progressBar=view.findViewById(R.id.progressBar);
        progressBar.setMax(core.getSolution());
        txtTimeSpan=view.findViewById(R.id.txtTimeSpan);
        txtSkorPuan=view.findViewById(R.id.txtSkorPuan);
        startDate=Calendar.getInstance();
        //Zaman  -- 2

        message=new DisplayMessage();
        screenSettings=new ScreenSettings(getActivity());
        selectedButtons=new ArrayList<>();

        gameActivity=view.findViewById(R.id.gameActivity);
        layoutDraw=view.findViewById(R.id.layoutDraw);


        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action((ImageButton)v);
            }
        };
        ButtonCreater buttonCreater=new ButtonCreater(getActivity(),
                                                        gameActivity,
                                                        onClickListener,
                                                        screenSettings);
        buttonCreater.create(35,core.getCities()); //35 tane button oluşturacak
        buttons=buttonCreater.getGameButonList();//Tüm oluşan butonları aldım.

        drawList = CostsSetter.getDrawList(getActivity(), buttons, core.getCosts());
    }

    private void action(ImageButton button) {

        if(selectedButtons.contains(button))
            message.show(gameActivity,"Uyarı","Seçili Şehre Tıkladınız");

        else {

            if(oldButton==null){
                showLayoutCosts(button);
                button.setImageResource(R.mipmap.home1);
                oldButton=button;
                selectedButtons.add(button);
                click_count++;
            }
            else {

                int pathCost= Examples.PathCosts(core.getCosts(),buttons.indexOf(oldButton),buttons.indexOf(button));
                if(pathCost>0){

                    totalScore+=pathCost;
                    click_count++;

                    DrawView drawView = new DrawView(getActivity(),oldButton,button, R.color.dark_orchid,10);
                    layoutDraw.addView(drawView);

                    if(click_count==core.getCities().length)
                        selectedButtons.remove(0);

                    removeLayoutCosts(oldButton);
                    showLayoutCosts(button);

                    oldButton=button;
                    button.setImageResource(R.mipmap.home3);
                    selectedButtons.add(button);

                    //bütün butonlara tıklanma, yani oyunun bitiş durumu
                    if (click_count==core.getCities().length+1)
                        levelStateBelirle();
                }
                else
                    message.show(gameActivity,"Uyarı","Yol Yoktur");
            }
        }

    }

    private void levelStateBelirle(){

            if((levelSaved == levelClicked) && (stateSaved == stateClicked))    //son levelin son sorusu oynandı ise seviye arttır
                stage.up();

            //oynanan oyun levelin son oyunu ise levelmenuye değilse statemenuye dön
            if(stateClicked == Examples.getCores()[levelClicked].length-1)
                   level_state_belirle=false;  //Level Menuye dön

            else
                level_state_belirle=true; //State Menuye dön

            sureDoldu=true;
    }

    private void timeStart(){
        new Thread(new Runnable() {

            public void run() {
                while (!stop) {

                    handler.post(new Runnable() {
                        public void run() {

                            long milisaniye = 0;
                            if(!sureDoldu){
                                endDate=Calendar.getInstance();
                                milisaniye=endDate.getTimeInMillis()-startDate.getTimeInMillis();
                                TimeSpan span=new TimeSpan((int) milisaniye);
                                StringBuilder sp=new StringBuilder();
                                sp.append(span.getHours()+" : ");
                                sp.append(span.getMinutes()+" : ");
                                sp.append(span.getSeconds());
                                txtTimeSpan.setText(String.valueOf(sp));
                            }
                            int maliyet= (int) (core.getSolution()-totalScore);
                            progressBar.setProgress(maliyet);
                            txtSkorPuan.setText(String.valueOf("Benzin Miktarı : "+maliyet));

                            if(sureDoldu){
                                stop=true;
                                Result result=new Result();
                                result.setPuan((int) (core.getSolution()/totalScore*100));
                                result.setSure(milisaniye);
                                result.setSureTxt(txtTimeSpan.getText().toString());
                                result.setLevelSaved(levelSaved);
                                result.setLevelClicked(levelClicked);
                                result.setLevel_state_durum(level_state_belirle);

                                if (core.getSolution()==totalScore)
                                    result.setOyun_durumu(true);
                                else
                                    result.setOyun_durumu(false);

                                Bundle bundle=new Bundle();
                                bundle.putSerializable("result", result);

                                Game_result gameResult=new Game_result();
                                gameResult.setArguments(bundle);

                                fragmentManager=getFragmentManager();
                                transaction=fragmentManager.beginTransaction();
                                transaction.replace(R.id.context_main,gameResult);
                                transaction.commit();
                            }
                        }
                    });
                    try { Thread.sleep(100); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }

        }).start();
    }

    @Override   // timeStart();
    public void onResume() {
        super.onResume();
        timeStart();
    }

    @Override @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_game,container,false);
        init();
        stage=new Stage(view.getContext());
        return view;
    }

    private void removeLayoutCosts(ImageButton oldButton){

        for (CostsSetter.Draw draw : drawList)

            if (buttons.get(buttons.indexOf(oldButton)).equals(draw.drawView.getStartView())
                    || buttons.get(buttons.indexOf(oldButton)).equals(draw.drawView.getEndView())) {

                layoutDraw.removeView(draw.drawView);
                layoutDraw.removeView(draw.textView);
            }
    }

    private void showLayoutCosts(ImageButton button) {

        for (CostsSetter.Draw draw: drawList)

            if (!(!(buttons.get(buttons.indexOf(button)).equals(draw.drawView.getStartView()) || buttons.get(buttons.indexOf(button)).equals(draw.drawView.getEndView()))
                    || selectedButtons.contains(draw.drawView.getStartView()) || selectedButtons.contains(draw.drawView.getEndView()))) {

                layoutDraw.removeView(draw.drawView);
                layoutDraw.removeView(draw.textView);
                draw.drawView.setColor(getResources().getColor(R.color.deep_pink));
                draw.drawView.setWidth(10);
                draw.textView.setTextColor(getResources().getColor(R.color.black));
                draw.textView.setTextSize(20);
                draw.textView.setTypeface(null, Typeface.BOLD);
                layoutDraw.addView(draw.drawView);
                layoutDraw.addView(draw.textView);
            }
    }
}

package com.travellingsalesmangame.Views.Game;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.travellingsalesmangame.Kategori_Secimi;
import com.travellingsalesmangame.R;
import com.travellingsalesmangame.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PopActivity extends Fragment implements View.OnClickListener{

    private View view;
    private TextView textView;
    private List<String[]> list;
    private int sayac=0;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private void init(){

        textView=view.findViewById(R.id.textView);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;


        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        param.width = (int) (width * .8);
        param.height=(int) (height * .7);
        param.gravity=Gravity.CENTER;

        this.getView().setLayoutParams(param);

        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        this.getActivity().getWindow().setAttributes(params);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_pop,container,false);
        view.findViewById(R.id.btn_ileri).setOnClickListener(this);
        view.findViewById(R.id.btn_geri).setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        readFile();
        textView.setText(list.get(0)[0].toString());
        super.onActivityCreated(savedInstanceState);
    }

    private void readFile() {
        list = new ArrayList<>();
        try {

            InputStream inputStream = view.getContext().getAssets().open("konular.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String satir = "";
            while ((satir = bufferedReader.readLine()) != null) {
                list.add(satir.split("!"));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        int id=v.getId();
        if(id== R.id.btn_ileri) {
            if(sayac==5){

                AlertDialog.Builder alertMessage = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                alertMessage.setTitle("Bilgi").
                             setMessage("Eğitiminiz tamamlanmıştır. Test kısmına geçerek puan kazanmak ister misiniz?").
                             setCancelable(false).
                             setIcon(R.mipmap.information); // icon atanacak
                alertMessage.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manager=getFragmentManager();
                        transaction=manager.beginTransaction();
                        transaction.replace(R.id.context_main,new Test());
                        transaction.commit();
                    }
                });
                alertMessage.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        manager=getFragmentManager();
                        transaction=manager.beginTransaction();
                        transaction.replace(R.id.context_main,new Kategori_Secimi());
                        transaction.commit();
                    }
                });
                alertMessage.show();
            }
            else{
                sayac++;
                textView.setText(list.get(sayac)[0].toString());
            }
        }

        if(id== R.id.btn_geri){
            if(sayac==0){
                textView.setText(list.get(sayac)[0].toString());}
            else{
                sayac--;
                textView.setText(list.get(sayac)[0].toString());}
        }

    }
}

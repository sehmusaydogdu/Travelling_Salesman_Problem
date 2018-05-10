package com.travellingsalesmangame;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.travellingsalesmangame.Models.Login.User;

public class Profil extends Fragment {

    private View view;
    private SharedPreferences prefs;
    private User user;
    private Gson gson;
    private TextView txtKullaniciAdi,txtEmail;


    private void init(){
        gson=new Gson();
        getActivity().setTitle("Profil Bilgileri");
        txtKullaniciAdi=view.findViewById(R.id.txtKullaniciAdi);
        txtEmail=view.findViewById(R.id.txtEmail);
        prefs= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String json=prefs.getString("user","");
        user=new User(gson.fromJson(json,User.class));
        txtKullaniciAdi.setText(user.getUserName());
        txtEmail.setText(user.getEmail());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_profil,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        super.onActivityCreated(savedInstanceState);
    }
}

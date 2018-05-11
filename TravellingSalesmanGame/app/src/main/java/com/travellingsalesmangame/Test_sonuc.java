package com.travellingsalesmangame;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Test_sonuc extends Fragment {

    private View view;
    private TextView txtView,txtCozulen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_test_sonuc,container,false);
        txtView=view.findViewById(R.id.txtView);
        txtCozulen=view.findViewById(R.id.txtCozulen);
        Bundle bundle=getArguments();
        int test_sonucu=bundle.getInt("test_sonucu",0);
        int soru_sayisi=bundle.getInt("soru_sayisi",0);
        txtView.setText(String.valueOf(test_sonucu));
        txtCozulen.setText(String.valueOf(soru_sayisi));
        return view;
    }
}

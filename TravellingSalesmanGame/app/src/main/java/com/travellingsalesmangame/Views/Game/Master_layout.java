package com.travellingsalesmangame.Views.Game;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.travellingsalesmangame.Controllers.Login.Encode;
import com.travellingsalesmangame.MasterYonlendir;
import com.travellingsalesmangame.Profil;
import com.travellingsalesmangame.Views.Login.LoginActivity;
import com.travellingsalesmangame.Models.Login.User;
import com.travellingsalesmangame.R;


public class Master_layout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private DrawerLayout master_layout;
    private NavigationView nav_view;
    private ActionBarDrawerToggle toggle;

    private User user;
    private ValueEventListener listenerCookie ;                          //Tablo adı
    private final DatabaseReference users = FirebaseDatabase.getInstance().getReference("User_b327a12217d490250cc533b28ddf2be79d3e6c5591a96ec3");

    private SharedPreferences prefs;
    private TextView nameTxt,emailTxt;

    private void init(){

        manager=getFragmentManager();

        nav_view=findViewById(R.id.nav_view);
        master_layout=findViewById(R.id.drawer_master);

        toggle=new ActionBarDrawerToggle(this,master_layout,R.string.open,R.string.close);
        master_layout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav_view.setNavigationItemSelectedListener(this);
    }

    private void initListener(){

        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        listenerCookie=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.exists() ||
                        !dataSnapshot.child("password").exists() ||
                        dataSnapshot.child("password").getValue(String.class) == null ||
                        !dataSnapshot.child("password").getValue(String.class).equals(user.getPassword()))
                    login_in();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    private void readIfAlreadyLogin(){
        Gson gson=new Gson();
        String json=prefs.getString("user","");
        if(json.equals(""))
           login_in();

        else
        {
            user=new User(gson.fromJson(json,User.class));
            users.child(Encode.encode(user.getEmail())).addValueEventListener(listenerCookie);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_layout);
        initListener();
        readIfAlreadyLogin();
        init();

        Master_layout.this.setTitle("Anasayfa");

    }

    @Override
    protected void onResume() {
        super.onResume();

        View layout= View.inflate(this,R.layout.nav_header_item,nav_view);
        nameTxt=layout.findViewById(R.id.nameTxt);
        emailTxt=layout.findViewById(R.id.emailTxt);
        nameTxt.setText(user.getUserName());
        emailTxt.setText(user.getEmail());

        MasterYonlendir masterYonlendir= new MasterYonlendir();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.context_main,masterYonlendir);
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id==R.id.anasayfa){
            MasterYonlendir fragmentA= new MasterYonlendir();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.context_main,fragmentA);
            transaction.commit();
        }

        if(id==R.id.cikis)
            login_out();

        if(id==R.id.istatistik){
            Istatistik istatistik=new Istatistik();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.context_main,istatistik);
            transaction.commit();
        }

        if (id==R.id.profil){
            Profil profil=new Profil();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.context_main,profil);
            transaction.commit();
        }

        master_layout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void login_in(){

        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor=prefs.edit();
        prefEditor.putString("user","");
        prefEditor.apply();

        Intent intent=new Intent(Master_layout.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void login_out() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle(R.string.logout).setMessage(R.string.login_msg).setIcon(R.mipmap.information);
        builder.setNegativeButton(R.string.login_hayir, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton(R.string.login_evet, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                login_in();
            }
        });
        builder.show();

    }


    //@Override
   /* public void onBackPressed() {
        //super.onBackPressed();
        master_layout.closeDrawer(GravityCompat.START);
    }*/
}

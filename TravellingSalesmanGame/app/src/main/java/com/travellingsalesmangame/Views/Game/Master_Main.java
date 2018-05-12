package com.travellingsalesmangame.Views.Game;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.travellingsalesmangame.Controllers.Login.Encode;
import com.travellingsalesmangame.Master_Acilis;
import com.travellingsalesmangame.Models.Game.GameInfo;
import com.travellingsalesmangame.Profil;
import com.travellingsalesmangame.Views.Login.LoginActivity;
import com.travellingsalesmangame.Models.Login.User;
import com.travellingsalesmangame.R;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class Master_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private DrawerLayout master_layout;
    private NavigationView nav_view;
    private ActionBarDrawerToggle toggle;
    private TextView nameTxt;
    private TextView emailTxt;

    private ImageView profileImageView;

    private User user;
    private GameInfo gameInfo;
    private ValueEventListener listenerCookie ;                          //Tablo adı
    private ValueEventListener listenerGameInfo ;
    private final DatabaseReference users = FirebaseDatabase.getInstance().getReference("User_b327a12217d490250cc533b28ddf2be79d3e6c5591a96ec3");
    private final DatabaseReference games = FirebaseDatabase.getInstance().getReference("Game_eee653b64ab2ff1051e13c092396179e9d29bbc7ed6aa4a8");

    private SharedPreferences prefs;

    private FirebaseStorage fStorage;

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
                else{

                    games.child(Encode.encode(user.getEmail())).addValueEventListener(listenerGameInfo);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        listenerGameInfo=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(!dataSnapshot.exists())
                    login_in();
                else{
                    gameInfo = dataSnapshot.getValue(GameInfo.class);
                }
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
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_main);
        initListener();
        readIfAlreadyLogin();
        init();

        Master_Acilis fragmentA= new Master_Acilis();
        transaction=manager.beginTransaction();
        transaction.replace(R.id.context_main,fragmentA);
        transaction.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();

        Gson gson=new Gson();
        String json=prefs.getString("user","");
        user=new User(gson.fromJson(json,User.class));

        View view = View.inflate(this,R.layout.nav_header_item,nav_view);
        nameTxt = view.findViewById(R.id.nameTxt);
        emailTxt = view.findViewById(R.id.emailTxt);
        nameTxt.setText(user.getUserName());
        emailTxt.setText(user.getEmail());

        profileImageView=view.findViewById(R.id.profileImageView);
        fStorage = FirebaseStorage.getInstance();
        StorageReference storageRef = fStorage.getReference().child("images").child(user.getEmail());
        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

            @Override
            public void onSuccess(Uri uri) {

                try {
                    URL url = new URL(uri.toString());
                    Bitmap bitImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    profileImageView.setImageBitmap(bitImage);
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        if (id== R.id.anasayfa){
            Master_Acilis fragmentA= new Master_Acilis();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.context_main,fragmentA);
            transaction.commit();
        }

        if(id== R.id.cikis)
            login_out();

        if(id== R.id.istatistik){
            Istatistik istatistik=new Istatistik();
            transaction=manager.beginTransaction();
            transaction.replace(R.id.context_main,istatistik);
            transaction.commit();
        }

        if (id== R.id.profil){
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

        Intent intent=new Intent(Master_Main.this, LoginActivity.class);
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }
}
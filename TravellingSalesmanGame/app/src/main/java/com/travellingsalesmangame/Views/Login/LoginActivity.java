package com.travellingsalesmangame.Views.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.travellingsalesmangame.Controllers.Login.Encode;
import com.travellingsalesmangame.Controllers.Login.UserRules;
import com.travellingsalesmangame.Models.Hash192.MyHash;
import com.travellingsalesmangame.Models.Login.User;
import com.travellingsalesmangame.R;
import com.travellingsalesmangame.Views.Game.Master_layout;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email,et_password;
    private TextView login_error;
    private String email,password;
    private ValueEventListener listenerUser,listenerSalt;
    private String salt;
    private final DatabaseReference users = FirebaseDatabase.getInstance().getReference("User_b327a12217d490250cc533b28ddf2be79d3e6c5591a96ec3");
    private final DatabaseReference salts = FirebaseDatabase.getInstance().getReference("Salt_8ff2ba9c135413f689dc257d70a4a75091110497a69c5b3c");
    private User user;

    private void init(){

        et_email = findViewById(R.id.login_email);
        et_password = findViewById(R.id.login_password);
        login_error = findViewById(R.id.login_error);

        final MyHash myHash = new MyHash();

        // Read salt from the database
        listenerSalt = new ValueEventListener() {       //veri tabanı dinleyicisi
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    salt = dataSnapshot.getValue(String.class);

                    if(salt != null && salt.length() == 48)
                        users.child(Encode.encode(email)).addValueEventListener(listenerUser);
                    else
                        login_error.setText(R.string.error_wrong_password);
                }
                else
                    login_error.setText(R.string.error_wrong_password);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                login_error.setText(R.string.error_database_read);
            }
        };

        // Read user from the database
        listenerUser = new ValueEventListener() {       //veri tabanı dinleyicisi
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String saltedHashedPassword = myHash.hash(myHash.hash(password)+salt);

                if(dataSnapshot.child("password").exists()
                        && dataSnapshot.child("password").getValue() != null
                        && dataSnapshot.child("password").getValue(String.class).length() == 48
                        ){                                                                          //geçerli kullanıcı olup olmama durumu

                    user = new User();
                    user.setEmail(dataSnapshot.child("email").getValue(String.class));
                    user.setUserName(dataSnapshot.child("userName").getValue(String.class));
                    user.setPassword(dataSnapshot.child("password").getValue(String.class));

                    if(user.getPassword().equals(saltedHashedPassword)) {//şifrelerin eşleşmeme durumu

                        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor preEditor = pref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        preEditor.putString("user", json);
                        preEditor.apply();

                        //bellekteki verileri silme, güvenlik için
                        user = new User();
                        saltedHashedPassword = null;

                        Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, Master_layout.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        login_error.setText(R.string.error_wrong_password);
                        user = new User();
                    }
                }
                else {
                    login_error.setText(R.string.error_wrong_password);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                login_error.setText(R.string.error_database_read);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private boolean ruleChecker() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean result = true;

        if(cm.getActiveNetworkInfo() == null) {

            login_error.setText(R.string.error_network);
            result = false;
        }
        else{

            if(password == null || password.equals("")) {                   //sifrenin bos olma durumu

                login_error.setText(getString(R.string.error_no_password));
                result = false;
            }
            else if(!UserRules.check_password(password)){                        //sifrenin kurallara uymama durumu (kurala uymuyorsa veri tabanına gitmesine gerek yok)
                login_error.setText(getString(R.string.error_wrong_password));
                result = false;
            }
            if(!UserRules.check_email(email)) {                                 //girilen mail adresin desteklenmemesi olma durumu
                login_error.setText(R.string.error_invalid_email);
                result = false;
            }
        }
        return result;
    }

    public void login_sign_onclick(View view) {

        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void login_onclick(View view) {

        email = String.valueOf(et_email.getText());
        password = String.valueOf(et_password.getText());

        if(ruleChecker()){

            login_error.setText("");
            salts.child(Encode.encode(email)).addValueEventListener(listenerSalt);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

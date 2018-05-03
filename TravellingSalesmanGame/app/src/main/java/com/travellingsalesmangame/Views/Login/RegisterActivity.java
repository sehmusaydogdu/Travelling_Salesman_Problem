package com.travellingsalesmangame.Views.Login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.travellingsalesmangame.Controllers.Login.Encode;
import com.travellingsalesmangame.Controllers.Login.UserRules;
import com.travellingsalesmangame.Models.Hash192.MyHash;
import com.travellingsalesmangame.Models.Login.User;
import com.travellingsalesmangame.R;

import java.util.Random;


public class RegisterActivity extends AppCompatActivity {

    private EditText et_userName,et_email,et_password,et_passwordConfirm;   //veri girisi yapilan editText'ler
    private TextView reg_error;    //tv_error = hata mesaji, err_* = ilgili verinin yanlis oldugunu gosteren isaret.
    private final DatabaseReference users = FirebaseDatabase.getInstance().getReference("User_b327a12217d490250cc533b28ddf2be79d3e6c5591a96ec3");
    private final DatabaseReference salts = FirebaseDatabase.getInstance().getReference("Salt_8ff2ba9c135413f689dc257d70a4a75091110497a69c5b3c");
    private ValueEventListener listenerUser;
    private User new_user;
    private String passwordConfirm;

    private void init(){

        et_userName = findViewById(R.id.reg_userName);
        et_email = findViewById(R.id.reg_email);
        et_password = findViewById(R.id.reg_password);
        et_passwordConfirm = findViewById(R.id.reg_passwordConfirm);
        reg_error = findViewById(R.id.reg_error);

        listenerUser = new ValueEventListener() {       //veri tabanı dinleyicisi
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {                                               //kayit olmak istenilen email adresin zaten kullanilmis olma durumu
                    reg_error.setText(R.string.error_registered_email);
                }
                else {

                    String salt = createRandomSalt(); //rastgele 48 karakter tuz uretiyoruz
                    MyHash myHash = new MyHash();

                    //Asagida once parolanin hashini aliyoruz, sonra tuzu ile hashlenmis parolaya ekliyoruz, sonra birlestirilmis bu verinin bir daha hash'ini aliyoruz
                    //Bu bizim veri tabaninda gorunecek parolamiz oluyor
                    new_user.setPassword(myHash.hash(myHash.hash(new_user.getPassword())+salt));

                    users.child(Encode.encode(new_user.getEmail())).setValue(new_user);
                    salts.child(Encode.encode(new_user.getEmail())).setValue(salt);                                             //tuzu da ekliyoruz veri tabanimiza (kendi tablosuna)

                    users.child(Encode.encode(new_user.getEmail())).removeEventListener(listenerUser);                      //isimiz bittiten sonra dinleyiciyi silip giris ekranina geri donuyoruz

                    //Bellekteki verileri silme, güvenlik için
                    new_user = new User();
                    salt = null;

                    Toast.makeText(RegisterActivity.this,"Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                reg_error.setText(R.string.error_database_read);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.register_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id==R.id.profile){

            return true;
        }
        if(id==R.id.setting){

            return true;
        }
        return true;
    }
*/
    //rastgele tuz üretimi; 16'lik sistemde 48 karakter (farkli da olabilirdi, hash ciktim ile ayni olsun istedim)
    private String createRandomSalt() {

        Random rand = new Random();
        StringBuilder salt=new StringBuilder();
        for(int i=0; i<48; i++)
             salt.append(Integer.toHexString(rand.nextInt(16)));

        return salt.toString();
    }

    private boolean ruleChecker() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean result = true;

        if(cm.getActiveNetworkInfo() == null) {

            reg_error.setText(R.string.error_network);
            result = false;
        }
        else{

            if(new_user.getPassword() == null || new_user.getPassword().equals("")) { //sifrenin bos olma durumu

                reg_error.setText(getString(R.string.error_no_password));
                result = false;
            }
            else {

                if(!UserRules.check_password(new_user.getPassword())){ //sifrenin kurallara uymama durumu
                    reg_error.setText(getString(R.string.error_invalid_password));
                    result = false;
                }
                if(!new_user.getPassword().equals(passwordConfirm)) {     //parola ve parolaonaylanin farkli olma durumu
                    reg_error.setText(R.string.error_wrong_passwordConfirm);
                    result = false;
                }
            }
            if(!UserRules.check_email(new_user.getEmail())) { //girilen mail adresin desteklenmemesi olma durumu
                reg_error.setText(R.string.error_invalid_email);
                result = false;
            }
            if(!UserRules.check_name(new_user.getUserName())) {     //kullanici adinin kurallara uymama durumu
                reg_error.setText(R.string.error_invalid_username);
            }
        }
        return result;
    }

    public void register_onclick(View view) {

        passwordConfirm = String.valueOf(et_passwordConfirm.getText());
        new_user = new User(String.valueOf(et_userName.getText()), String.valueOf(et_email.getText()), String.valueOf(et_password.getText()));

        if(ruleChecker()){

            reg_error.setText("");
            users.child(Encode.encode(new_user.getEmail())).addValueEventListener(listenerUser);  //emaile girilen degere ait veritabanındaki referansa giris kosullarini iceren listener'ı atıyoruz. email yoksa null donuyor
        }
    }

    public void cancel_onclick(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

package com.travellingsalesmangame;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.travellingsalesmangame.Models.Login.User;
import com.travellingsalesmangame.Views.Game.Master_Main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


import static android.app.Activity.RESULT_OK;

public class Profil extends Fragment{

    private View view;
    private SharedPreferences prefs;
    private User user;
    private Gson gson;
    private EditText txtKullaniciAdi,txtEmail;

    private Button btnChose;
    private Uri filePath;
    private ImageView profileImageView;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private FirebaseStorage fStorage;


    private void init(){
        gson=new Gson();
        getActivity().setTitle("Profil Bilgileri");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        prefs= PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String json=prefs.getString("user","");

        txtKullaniciAdi=view.findViewById(R.id.txtKullaniciAdi);
        txtEmail=view.findViewById(R.id.txtEmail);
        btnChose=view.findViewById(R.id.btnChoose);
        profileImageView=view.findViewById(R.id.imgProfil);

        user=new User(gson.fromJson(json,User.class));
        txtKullaniciAdi.setText(user.getUserName());
        txtEmail.setText(user.getEmail());
        txtEmail.setEnabled(false);
        txtEmail.setKeyListener(null);


        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

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

    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ user.getEmail());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) { progressDialog.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profileImageView.setImageBitmap(bitmap);
                Toast.makeText(getActivity(),"Resim alındı",Toast.LENGTH_SHORT).show();
                uploadImage();
                Intent intent=new Intent(getActivity(), Master_Main.class);
                startActivity(intent);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        view=inflater.inflate(R.layout.activity_profil,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        init();
        super.onActivityCreated(savedInstanceState);
    }
}

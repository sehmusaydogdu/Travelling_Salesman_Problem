package com.travellingsalesmangame.Models.Login;

//Kullanici sinifi, veri tabanina direk bu sinifin nesneleri kayit edilir.

public class User {

    private String email,name,password;

    public User() {}

    public User(User user) {
        this.email=user.email;
        this.name=user.name;
        this.password=user.password;
    }

    public User(String name, String email, String password) {

        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    //Kullanici degerleri degisirse diye, bu projede kullanilmayacak.
    public void setEmail(String email) { this.email = email; }
    public void setUserName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

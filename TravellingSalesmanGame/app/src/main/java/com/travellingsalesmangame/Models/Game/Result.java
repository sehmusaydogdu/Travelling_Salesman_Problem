package com.travellingsalesmangame.Models.Game;

import java.io.Serializable;

public class Result implements Serializable {


    private boolean oyun_durumu; //Kazandınız kaybettiniz (true - false)
    private int puan;            // Kullanıcının kazandığı puan
    private long sure;           //Kullanıcı ne kadar sureden çözdü? (milisaniye)
    private String sureTxt;      //Kullanıcı ne kadar sureden çözdü? (Text formatında) (0 : 0: 0)
    private int levelSaved;      //Kayıtlı level
    private int levelClicked;    //kayıtlı state
    private boolean level_state_durum;  //Level mi State mi gitsin.

    public boolean isLevel_state_durum() {
        return level_state_durum;
    }

    public void setLevel_state_durum(boolean level_state_durum) {
        this.level_state_durum = level_state_durum;
    }

    public int getLevelSaved() {
        return levelSaved;
    }

    public void setLevelSaved(int levelSaved) {
        this.levelSaved = levelSaved;
    }

    public int getLevelClicked() {
        return levelClicked;
    }

    public void setLevelClicked(int levelClicked) {
        this.levelClicked = levelClicked;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public long getSure() {
        return sure;
    }

    public void setSure(long sure) {
        this.sure = sure;
    }

    public String getSureTxt() {
        return sureTxt;
    }

    public void setSureTxt(String sureTxt) {
        this.sureTxt = sureTxt;
    }

    public boolean isOyun_durumu() {
        return oyun_durumu;
    }

    public void setOyun_durumu(boolean oyun_durumu) {
        this.oyun_durumu = oyun_durumu;
    }
}

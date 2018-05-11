package com.travellingsalesmangame.Models.Game;

import java.io.Serializable;

public class Result implements Serializable {

    private String message;
    private int puan;
    private long sure;
    private String sureTxt;
    private int levelSaved;
    private int levelClicked;

    public boolean isLevel_state_durum() {
        return level_state_durum;
    }

    public void setLevel_state_durum(boolean level_state_durum) {
        this.level_state_durum = level_state_durum;
    }

    private boolean level_state_durum;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}

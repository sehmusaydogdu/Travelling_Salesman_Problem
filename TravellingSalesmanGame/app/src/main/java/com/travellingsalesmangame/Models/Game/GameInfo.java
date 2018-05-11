package com.travellingsalesmangame.Models.Game;


import java.util.ArrayList;
import java.util.List;

public class GameInfo{

    private int level,state;

    private List<Integer> testScores;
    private List<List<List<Integer>>> gameScores;

    private void init() {

        level = 0;
        state = 0;
        testScores = new ArrayList<>();
        gameScores = new ArrayList<>();

        for(int i=0; i<Examples.getCores().length; i++)
            gameScores.add(new ArrayList<List<Integer>>());

        List<Integer> temp = new ArrayList();
        for(int i=0; i<gameScores.size(); i++)
            for(int j=0; j<Examples.getCores()[i].length; j++){

                temp = new ArrayList<>();
                temp.add(0);
                temp.add(0);
                gameScores.get(i).add(new ArrayList<>(temp));
            }
    }


    public GameInfo() {

        init();
    }


    public GameInfo(GameInfo gameInfo) {

        level = gameInfo.getLevel();
        state = gameInfo.getState();
        testScores = gameInfo.getTestScores();
        gameScores = gameInfo.getGameScores();

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Integer> getTestScores() {
        return testScores;
    }

    public void setTestScores(List<Integer> testScores) {
        this.testScores = testScores;
    }

    public List<List<List<Integer>>> getGameScores() {
        return gameScores;
    }

    public void setGameScores(List<List<List<Integer>>> gameScores) {
        this.gameScores = gameScores;
    }
}

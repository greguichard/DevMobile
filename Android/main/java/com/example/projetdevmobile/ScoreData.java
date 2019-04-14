package com.example.projetdevmobile;

import java.util.Date;

public class ScoreData {

    private int idScore;
    private String name;
    private int score;
    private String date;


    public ScoreData(int idScore, String name, int score, String date) {
        this.idScore = idScore;
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public int getIdScore() {
        return idScore;
    }

    public void setIdScore(int idScore) {
        this.idScore = idScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

package com.example.ubet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("homeTeam")
    @Expose
    String firstTeam;

    @SerializedName("awayTeam")
    @Expose
    String secondTeam;

    @SerializedName("homeOdd")
    @Expose
    double firstTeamCoef;

    @SerializedName("awayOdd")
    @Expose
    double secondTeamCoef;

    @SerializedName("draw")
    @Expose
    double drawCoef;

    @SerializedName("homeScore")
    @Expose
    int firstTeamScore;

    @SerializedName("awayScore")
    @Expose
    int secondTeamScore;

    @SerializedName("liveResult")
    @Expose
    String liveResult;

    @SerializedName("href")
    @Expose
    String href;

    public Game() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public double getFirstTeamCoef() {
        return firstTeamCoef;
    }

    public void setFirstTeamCoef(double firstTeamCoef) {
        this.firstTeamCoef = firstTeamCoef;
    }

    public double getSecondTeamCoef() {
        return secondTeamCoef;
    }

    public void setSecondTeamCoef(double secondTeamCoef) {
        this.secondTeamCoef = secondTeamCoef;
    }

    public double getDrawCoef() {
        return drawCoef;
    }

    public void setDrawCoef(double drawCoef) {
        this.drawCoef = drawCoef;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public String getLiveResult() {
        return liveResult;
    }

    public void setLiveResult(String liveResult) {
        this.liveResult = liveResult;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

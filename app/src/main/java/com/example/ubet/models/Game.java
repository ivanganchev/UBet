package com.example.ubet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("firstTeam")
    @Expose
    Team firstTeam;
    @SerializedName("secondTeam")
    @Expose
    Team secondTeam;
    @SerializedName("firstTeamCoef")
    @Expose
    double firstTeamCoef;
    @SerializedName("secondTeamCoef")
    @Expose
    double secondTeamCoef;
    @SerializedName("drawCoef")
    @Expose
    double drawCoef;
    @SerializedName("startingHour")
    @Expose
    String startingHour;

    public Game() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
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

    public String getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(String startingHour) {
        this.startingHour = startingHour;
    }
}

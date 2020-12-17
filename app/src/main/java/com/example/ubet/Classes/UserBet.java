package com.example.ubet.Classes;

public class UserBet {
    static int currId = 0;
    int id;
    double betCoef;
    double moneyBet;
    String teamName;
    double approxWin;

    public UserBet(double betCoef, double moneyBet, String teamName, double approxWin) {
        this.id = currId;
        this.betCoef = betCoef;
        this.moneyBet = moneyBet;
        this.teamName = teamName;
        this.approxWin = approxWin;
        currId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBetCoef() {
        return betCoef;
    }

    public void setBetCoef(double betCoef) {
        this.betCoef = betCoef;
    }

    public double getMoneyBet() {
        return moneyBet;
    }

    public void setMoneyBet(double moneyBet) {
        this.moneyBet = moneyBet;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public double getApproxWin() {
        return approxWin;
    }

    public void setApproxWin(double approxWin) {
        this.approxWin = approxWin;
    }
}

package com.example.ubet.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBet implements Parcelable {
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

    protected UserBet(Parcel in) {
        id = in.readInt();
        betCoef = in.readDouble();
        moneyBet = in.readDouble();
        teamName = in.readString();
        approxWin = in.readDouble();
    }

    public static final Creator<UserBet> CREATOR = new Creator<UserBet>() {
        @Override
        public UserBet createFromParcel(Parcel in) {
            return new UserBet(in);
        }

        @Override
        public UserBet[] newArray(int size) {
            return new UserBet[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(betCoef);
        dest.writeDouble(moneyBet);
        dest.writeString(teamName);
        dest.writeDouble(approxWin);
    }
}

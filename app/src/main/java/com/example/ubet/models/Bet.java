package com.example.ubet.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bet implements Parcelable {
    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("matchId")
    @Expose
    int matchId;

    @SerializedName("team")
    @Expose
    int team;

    @SerializedName("amount")
    @Expose
    double amountBet;

    @SerializedName("odd")
    @Expose
    double odd;

    public Bet() {

    }

    public Bet(int matchId, int team, double amountBet, double odd) {
        this.matchId = matchId;
        this.team = team;
        this.amountBet = amountBet;
        this.odd = odd;
    }

    protected Bet(Parcel in) {
        id = in.readInt();
        matchId = in.readInt();
        team = in.readInt();
        amountBet = in.readDouble();
        odd = in.readDouble();
    }

    public static final Creator<Bet> CREATOR = new Creator<Bet>() {
        @Override
        public Bet createFromParcel(Parcel in) {
            return new Bet(in);
        }

        @Override
        public Bet[] newArray(int size) {
            return new Bet[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public double getAmountBet() {
        return amountBet;
    }

    public void setAmountBet(double amountBet) {
        this.amountBet = amountBet;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(matchId);
        dest.writeInt(team);
        dest.writeDouble(amountBet);
        dest.writeDouble(odd);
    }
}

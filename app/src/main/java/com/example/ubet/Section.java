package com.example.ubet;

import com.example.ubet.models.Game;

import java.util.List;

public class Section {

    String header;
    List<Game> games;

    public Section(String header, List<Game> games) {
        this.header = header;
        this.games = games;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

}

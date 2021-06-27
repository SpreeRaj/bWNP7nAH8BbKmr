package com.game.connect.five.connectfiveserver.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Players {

    private String player1Name;
    private String player1TokenColor;
    private String player1Status;

    private String player2Name;
    private String player2TokenColor;
    private String player2Status;

    public Players() {

    }

    public Players(String player1Name, String player1TokenColor, String player1Status, String player2Name,
            String player2TokenColor, String player2Status) {
        this.player1Name = player1Name;
        this.player1TokenColor = player1TokenColor;
        this.player1Status = player1Status;
        this.player2Name = player2Name;
        this.player2TokenColor = player2TokenColor;
        this.player2Status = player2Status;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer1TokenColor() {
        return player1TokenColor;
    }

    public void setPlayer1TokenColor(String player1TokenColor) {
        this.player1TokenColor = player1TokenColor;
    }

    public String getPlayer1Status() {
        return player1Status;
    }

    public void setPlayer1Status(String player1Status) {
        this.player1Status = player1Status;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getPlayer2TokenColor() {
        return player2TokenColor;
    }

    public void setPlayer2TokenColor(String player2TokenColor) {
        this.player2TokenColor = player2TokenColor;
    }

    public String getPlayer2Status() {
        return player2Status;
    }

    public void setPlayer2Status(String player2Status) {
        this.player2Status = player2Status;
    }

}

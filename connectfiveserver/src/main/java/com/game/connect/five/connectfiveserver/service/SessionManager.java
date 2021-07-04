package com.game.connect.five.connectfiveserver.service;

import com.game.connect.five.connectfiveserver.model.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    @Autowired
    Game game;


    public String updateOpponentStatus(String playerId) {
        if(this.game.getPlayer1().getUniqueID().equals(playerId)){
            this.game.getPlayer2().setPlayerStatus("Inactive");
        }
        else if(this.game.getPlayer2().getUniqueID().equals(playerId)){
            this.game.getPlayer1().setPlayerStatus("Inactive");
        }
        else
            return "Update Error";
        return "Done";
    }

    public String updatecurrentPlayerStatus(String playerId) {
        if(this.game.getPlayer1().getUniqueID().equals(playerId)){
            this.game.getPlayer1().setPlayerStatus("Active");
        }
        else if(this.game.getPlayer2().getUniqueID().equals(playerId)){
            this.game.getPlayer2().setPlayerStatus("Active");
        }
        else
            return "Update Error";
        return "Done";
    }
    
}

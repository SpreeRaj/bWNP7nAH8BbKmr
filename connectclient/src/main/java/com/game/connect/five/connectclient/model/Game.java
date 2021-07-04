package com.game.connect.five.connectclient.model;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

   private String player1uniqueID;
   private String player1Name;
   private String player1TokenColor;
   private String player1Status;

   private String player2uniqueID;
   private String player2Name;
   private String player2TokenColor;
   private String player2Status;

   private String currentPlayer;
   private String clientPlayerID;
   private String winningPlayerId;

   private String gameState;
   
    
}

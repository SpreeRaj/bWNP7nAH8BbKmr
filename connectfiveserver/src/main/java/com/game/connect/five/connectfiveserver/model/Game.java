package com.game.connect.five.connectfiveserver.model;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GameBoard gameBoard;
    @Autowired
    Player player1;
    @Autowired
    Player player2;
    
}

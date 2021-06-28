package com.game.connect.five.connectfiveserver.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameBoard extends GameBoardSize {

    private String board[][];
    //this is a connect 5 game therefore win size is set default 5
    //Change as per need
    private int winSize=5;

}

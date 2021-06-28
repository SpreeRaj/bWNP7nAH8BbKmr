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

}

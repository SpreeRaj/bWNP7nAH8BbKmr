package com.game.connect.five.connectfiveserver.model;

import org.springframework.stereotype.Component;

@Component
public class GameBoard {

    private String board[][];

    public GameBoard() {

    }

    public GameBoard(String[][] board) {
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

}

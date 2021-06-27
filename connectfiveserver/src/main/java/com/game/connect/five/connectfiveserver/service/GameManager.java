package com.game.connect.five.connectfiveserver.service;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.GameBoard;
import com.game.connect.five.connectfiveserver.model.Players;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameManager {

    @Autowired
    GameBoard gameBoard;
    @Autowired
    Players players;
    String GameStatus;

    public GameManager() {
    }

    public GameManager(GameBoard gameBoard, Players players, String gameStatus) {
        this.gameBoard = gameBoard;
        this.players = players;
        GameStatus = gameStatus;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }

    public String getGameStatus() {
        return GameStatus;
    }

    public void setGameStatus(String gameStatus) {
        GameStatus = gameStatus;
    }

    public void startNewGame(String playerName, String playerToken, int boardRow, int boardColumn) {

    }

}

package com.game.connect.five.connectfiveserver.service;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.GameBoardSize;
import com.game.connect.five.connectfiveserver.model.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameManager {

    @Autowired
    Game game;
    String GameStatus;

    public String startNewGame(GameBoardSize gameBoardSize) {

        this.game.getGameBoard().setColumn(gameBoardSize.getColumn());
        this.game.getGameBoard().setRow(gameBoardSize.getRow());
        this.game.getGameBoard().setBoard(new String[gameBoardSize.getRow()][gameBoardSize.getColumn()]);

        String[][] board = this.game.getGameBoard().getBoard();
        for(int i=0 ; i<gameBoardSize.getRow() ; i++)
            for(int j=0 ; j<gameBoardSize.getColumn() ; j++)
                board[i][j]="";

        return "Game created with Board size "+gameBoardSize.getRow()+" X "+gameBoardSize.getColumn();
    }

    public Player addPlayerDetails(String playerName, String playerTokenColor) {
        Player player=null;
        if(this.game.getPlayer1().getPlayerName()==null){
            this.game.getPlayer1().setPlayerName(playerName);
            this.game.getPlayer1().setPlayerTokenColor(playerTokenColor);
            this.game.getPlayer1().setPlayerStatus("Active");
            player=this.game.getPlayer1();
        }
        else if(this.game.getPlayer2().getPlayerName()==null){
            this.game.getPlayer2().setPlayerName(playerName);
            this.game.getPlayer2().setPlayerTokenColor(playerTokenColor);
            this.game.getPlayer2().setPlayerStatus("Active");
            player=this.game.getPlayer2();
        }
        return player;
    }

    public String updateBoard(int column) {
        return null;
    }

}

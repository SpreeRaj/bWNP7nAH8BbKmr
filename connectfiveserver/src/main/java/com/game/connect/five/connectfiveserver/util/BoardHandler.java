package com.game.connect.five.connectfiveserver.util;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.game.connect.five.connectfiveserver.config.GameConfigEnum.*;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardHandler {

    @Autowired
    private Game game;
    private int boardTop[];
    private Player currentPlayer;
    private String GameStatus;
    private long boardCapacity;
    
    private boolean winnerFlag=false;

    public String addToken(int column, String playerId) {

        String returnValue = "";
        String[][] board = this.game.getGameBoard().getBoard();

        

        if (this.boardCapacity > 0) {
            if (this.boardTop[column - 1] <= this.game.getGameBoard().getRow() - 1) {
                board[this.boardTop[column - 1]][column - 1] = this.currentPlayer.getPlayerTokenColor();
                this.winnerFlag = this.calculateWinner(this.boardTop[column - 1], column - 1);
                this.boardTop[column - 1]++;
                this.boardCapacity--; 
                return AddTokenResponse.TOKEN_ADDED.name();
            } else {
                return AddTokenResponse.COLUMN_FULL.name();
            }
        }

        return AddTokenResponse.UPDATE_ERROR.name();
    }

    private boolean calculateWinner(int row, int column) {
        boolean winnerFlag = false;
        if (this.horizontalWin(row, column) || this.verticalWin(row, column) || this.rightDiagonalWin(row, column)
                || this.leftDiagonalWin(row, column))
            winnerFlag = true;
        return winnerFlag;
    }

    private boolean leftDiagonalWin(int row, int column) {
        int winSize = this.game.getGameBoard().getWinSize();
        // current token counted
        winSize--;
        for (int i = column + 1, j = row-1; i < this.game.getGameBoard().getColumn(); i++,j--) {
            if(j<0)
                break;
            if (this.game.getGameBoard().getBoard()[j][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("\\ left diagonal top -->" + winSize);
        for (int i = column - 1, j= row + 1; i >= 0; i--,j++) {
            if(j>this.game.getGameBoard().getRow()-1)
                break;
            if (this.game.getGameBoard().getBoard()[j][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("\\ left diagonal bottom -->" + winSize);
        return false;
    }
    
    private boolean rightDiagonalWin(int row, int column) {
        int winSize = this.game.getGameBoard().getWinSize();
        // current token counted
        winSize--;
        for (int i = column + 1, j = row+1; i < this.game.getGameBoard().getColumn(); i++,j++) {
            if(j>this.game.getGameBoard().getRow()-1)
                break;
            if (this.game.getGameBoard().getBoard()[j][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("/ right diagonal top -->" + winSize);
        for (int i = column - 1, j= row - 1; i >= 0; i--,j--) {
            if(j<0)
                break;
            if (this.game.getGameBoard().getBoard()[j][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("/ right diagonal bottom -->" + winSize);
        System.out.println();
        return false;
    }

    private boolean verticalWin(int row, int column) {
        int winSize = this.game.getGameBoard().getWinSize();
        // current token counted
        winSize--;
        for (int i = row - 1; i >= 0; i--) {
            if (this.game.getGameBoard().getBoard()[i][column].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
            
        }
        System.out.println("Vertical bottom -->" + winSize);
        System.out.println();
        return false;
    }

    private boolean horizontalWin(int row, int column) {
        int winSize = this.game.getGameBoard().getWinSize();
        // current token counted
        winSize--;
        for (int i = column + 1; i < this.game.getGameBoard().getColumn(); i++) {
            if (this.game.getGameBoard().getBoard()[row][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("horizontal right -->" + winSize);
        for (int i = column - 1; i > 0; i--) {
            if (this.game.getGameBoard().getBoard()[row][i].equals(this.currentPlayer.getPlayerTokenColor())) {
                winSize--;
            }
            else{
                break;
            }
            if (winSize == 0) {
                return true;
            }
        }
        System.out.println("horizontal left -->" + winSize);
        System.out.println();
        return false;
    }

}

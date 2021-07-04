package com.game.connect.five.connectfiveserver.service;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.GameBoard;
import com.game.connect.five.connectfiveserver.model.GameBoardSize;
import com.game.connect.five.connectfiveserver.model.Player;
import com.game.connect.five.connectfiveserver.util.BoardHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    Player player1=new Player("Ron", "RED", "Active", 5);
    Player player2=new Player("Harry", "test", "Active", 5);
    GameBoard gameBoard=new GameBoard(new String[3][3], 5);

    Game game;
    BoardHandler boardHandler;

    @BeforeEach
    void setUp() {

        game=new Game(gameBoard, player1, player2);
        boardHandler=new BoardHandler(game, new int[3], player1, null, 9, false, new Player());
    }

    @Test
    void noArgsTestGameManager() {
        GameManager gameManager=new GameManager();
        assertNotNull(gameManager);
    }

    //Start New Game
    @Test
    void startNewGameTest() {
        GameBoardSize gameBoardSize=new GameBoardSize(3, 3);
        GameManager gameManager=new GameManager(game, boardHandler);
        String response=gameManager.startNewGame(gameBoardSize);

        assertEquals("Game created with Board size 3 X 3", response);
    }

    //Add player Details
    @Test
    void addPlayer1DetailsTest() {
        game=new Game(gameBoard, new Player(), new Player());
        GameManager gameManager=new GameManager(game, boardHandler);
        Player p=gameManager.addPlayerDetails("Raj", "Red");
        assertEquals(p.getPlayerName(), "Raj");
    }

    @Test
    void addPlayer2DetailsTest() {
        game=new Game(gameBoard, player1, new Player());
        GameManager gameManager=new GameManager(game, boardHandler);
        Player p=gameManager.addPlayerDetails("Raj", "Red");
        assertEquals(p.getPlayerName(), "Raj");
    }


    //update Board
    @Test
    void updateBoardwithIncorrectClientId() {
        GameManager gameManager=new GameManager(game, boardHandler);
        String response=gameManager.updateBoard(1, "RandomID");
        assertEquals("INVALID_PLAYER", response);
    }

    @Test
    void updateBoardWithIllegalTurn() {
        GameManager gameManager=new GameManager(game, boardHandler);
        String response=gameManager.updateBoard(1, player2.getUniqueID());
        assertEquals("WRONG_TURN", response);
    }

    @Test
    void updateBoardWithInvalidValidColum() {
        GameManager gameManager=new GameManager(game, boardHandler);
        String response=gameManager.updateBoard(1, player1.getUniqueID());
        assertEquals("INVALID_COLUMN", response);
    }

    @Test
    void updateBoardWithWinnerResponse() {
        this.game.getGameBoard().setColumn(3);
        this.game.getGameBoard().setRow(3);
        this.game.setGameBoard(gameBoard);
        this.game.getGameBoard().setWinSize(3);
        String[][] board = this.game.getGameBoard().getBoard();
        for(int i=0 ; i<3 ; i++)
            for(int j=0 ; j<3 ; j++)
                board[i][j]="";
        board[0][1]="RED";
        board[0][2]="RED";        
        GameManager gameManager=new GameManager(game, boardHandler);
        gameManager.getBoardHandler().setWinnerFlag(true);
        String response=gameManager.updateBoard(1, player1.getUniqueID());
        assertEquals("WINNER", response);
    }

    @Test
    void updateBoardWithUpdateAddTokenResponse() {
        this.game.getGameBoard().setColumn(3);
        this.game.getGameBoard().setRow(3);
        this.game.setGameBoard(gameBoard);
        String[][] board = this.game.getGameBoard().getBoard();
        for(int i=0 ; i<3 ; i++)
            for(int j=0 ; j<3 ; j++)
                board[i][j]="";
        GameManager gameManager=new GameManager(game, boardHandler);
        gameManager.getBoardHandler().setWinnerFlag(true);
        String response=gameManager.updateBoard(1, player1.getUniqueID());
        assertEquals("TOKEN_ADDED", response);
    }

    @Test
    void updateBoardWithUpdateAddTokenResponsePlayer2() {
        this.game.getGameBoard().setColumn(3);
        this.game.getGameBoard().setRow(3);
        this.game.setGameBoard(gameBoard);
        this.boardHandler.setCurrentPlayer(this.game.getPlayer2());
        String[][] board = this.game.getGameBoard().getBoard();
        for(int i=0 ; i<3 ; i++)
            for(int j=0 ; j<3 ; j++)
                board[i][j]="";
        GameManager gameManager=new GameManager(game, boardHandler);
        gameManager.getBoardHandler().setWinnerFlag(true);
        gameManager.getGame();
        String response=gameManager.updateBoard(1, player2.getUniqueID());
        assertEquals("TOKEN_ADDED", response);
    }


    @Test
    void getGame() {
    }

    @Test
    void getBoardHandler() {
    }

    @Test
    void setGame() {
    }

    @Test
    void setBoardHandler() {
    }
}
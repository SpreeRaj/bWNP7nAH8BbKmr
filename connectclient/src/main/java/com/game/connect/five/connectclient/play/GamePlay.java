package com.game.connect.five.connectclient.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.game.connect.five.connectclient.config.GameConfig;
import com.game.connect.five.connectclient.config.GameConfigEnum.*;
import com.game.connect.five.connectclient.model.Board;
import com.game.connect.five.connectclient.model.Game;
import com.game.connect.five.connectclient.service.GetCalls;
import com.game.connect.five.connectclient.service.PutCalls;
import com.game.connect.five.connectclient.util.ResponseMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

@Component
public class GamePlay {

    @Autowired
    private GetCalls getCalls;
    @Autowired
    private PutCalls putCalls;
    @Autowired
    private ResponseMapper responseMapper;
    @Autowired
    private Game game;
    @Autowired
    GameConfig gameConfig;
    @Autowired
    Board board;

    private Scanner sc = new Scanner(System.in);

    public void gamePlayHandler() throws Exception {

        // Step 1 loadWelcomeScreen
        this.loadWelcomeScreen();

        // Step 2 identifyGameStatus
        this.game.setGameState(this.identifyGameStatus());

        // Step 3 Add player details
        if (this.game.getGameState().equals(GameState.NEW.name())) {
            this.startNewGame();
            this.addNewPlayerDetails();
            this.game.setGameState(GameState.WAITING.name());
        }
        if (this.game.getGameState().equals(GameState.WAITING.name())) {
            if (this.game.getClientPlayerID() == null)
                this.addNewPlayerDetails();
            else
                this.pollPlayer2();
        }
        if (this.game.getGameState().equals(GameState.FULL.name())) {
            System.out.println("Already two players in game");
        }
        if (this.game.getGameState().equals(GameState.INVALID.name())) {
            System.out.println("Game status invalid check or restart server");
        }
        for (int i = 3; i > 0; i--) {
            Thread.sleep(1000);
            System.out.println("Starting Game in " + i + "...");
        }
        // Step 4 Play game
        String winner = null;
        int i = 0;
        do {
            System.out.println();
            this.displayBoard();
            System.out.println();
            
            while (true) {

                
                String currentPlayerStatus = this.pollCurrentPlayer();
                if (currentPlayerStatus.equals(this.game.getClientPlayerID())) {
                    break;
                }
                Thread.sleep(1000);
                if(i%10==0){
                    System.out.println("Waiting for opponent to make the move ...");
                }
                i++;

            }

            System.out.print("Make your move --> Enter Column : ");
            String move = String.valueOf(sc.next().charAt(0));
            String updateResponse = this.updateBoard(move);
            System.out.println(updateResponse);
        } while (winner == null);
    }

    private String updateBoard(String move) throws IOException {
        String response = putCalls.callMakeMoveApi(move, this.game.getClientPlayerID());
        return response;
    }

    private String pollCurrentPlayer() throws Exception {
        String playerId = responseMapper.getCurrentPlayer(getCalls.callBoardStatusApi());
        return playerId;
    }

    private void displayBoard() throws IOException {
        responseMapper.printBoard(getCalls.callGameStatusApi());
    }

    private void pollPlayer2() throws Exception {
        int i = 0;
        while (true) {
            if (i % 10 == 0)
                System.out.println("WAting for player 2 .....");
            responseMapper.updateGameObject(getCalls.callGameStatusApi());
            Thread.sleep(1000);
            i++;
            if (game.getPlayer2Status() != null)
                break;
        }
    }

    private void startNewGame() throws Exception {
        System.out.println("Game board size is  set default to Number of Rows: 6 and Column: 9");
        putCalls.setupGame(6, 9);
    }

    private void addNewPlayerDetails() throws Exception {
        if (this.game.getGameState().equals(GameState.NEW.name())) {
            System.out.println("Welcome Player 1");
            this.game.setClientPlayerID(this.game.getPlayer1uniqueID());
        }
        if (this.game.getGameState().equals(GameState.WAITING.name())) {
            System.out.println("Welcome Player 2");
            this.game.setClientPlayerID(this.game.getPlayer2uniqueID());
        }
        System.out.print("Enter Name : ");
        String playerName = sc.next();
        if (this.game.getGameState().equals(GameState.NEW.name())) {
            for (int i = 1; i <= gameConfig.getPlayerToken().length; i++)
                System.out.println(i + ". " + gameConfig.getPlayerToken()[i - 1]);
        }
        ArrayList<String> tokenList = new ArrayList<>();
        if (this.game.getGameState().equals(GameState.WAITING.name())) {

            for (int i = 0; i < gameConfig.getPlayerToken().length; i++) {
                if (!(this.game.getPlayer1TokenColor().equals(gameConfig.getPlayerToken()[i])))
                    tokenList.add(gameConfig.getPlayerToken()[i]);
            }

            for (int i = 1; i <= tokenList.size(); i++)
                System.out.println(i + ". " + tokenList.get(i - 1));
        }
        System.out.print("Choose token Colour: ");
        String choice = String.valueOf(sc.next().charAt(0));
        String playerToken = "";
        if (this.game.getGameState().equals(GameState.NEW.name()))
            playerToken = gameConfig.getPlayerToken()[Integer.parseInt(choice) - 1];
        if (this.game.getGameState().equals(GameState.WAITING.name()))
            playerToken = tokenList.get(Integer.parseInt(choice) - 1);
        putCalls.addNewPlayer(playerName.trim(), playerToken);
    }

    private void loadWelcomeScreen() throws IOException {
        // Starting Game
        System.out.println("*********************************");
        System.out.print("*    ");
        System.out.print(getCalls.callWelcomeApi());
        System.out.println("      *");
        System.out.println("*********************************");
    }

    private String identifyGameStatus() throws IOException {
        responseMapper.updateGameObject(getCalls.callGameStatusApi());

        if (game.getPlayer1Status() == null && game.getPlayer2Status() == null)
            return GameState.NEW.name();
        if (game.getPlayer1Status() != null && game.getPlayer2Status() == null)
            return GameState.WAITING.name();
        if (game.getPlayer1Status() != null && game.getPlayer2Status() != null)
            return GameState.FULL.name();

        return GameState.INVALID.name();
    }

}

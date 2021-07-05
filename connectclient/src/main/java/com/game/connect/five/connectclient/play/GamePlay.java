package com.game.connect.five.connectclient.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.game.connect.five.connectclient.config.GameConfig;
import com.game.connect.five.connectclient.config.GameConfigEnum.GameState;
import com.game.connect.five.connectclient.model.Game;
import com.game.connect.five.connectclient.service.GetCalls;
import com.game.connect.five.connectclient.service.PutCalls;
import com.game.connect.five.connectclient.util.ResponseMapper;
import com.game.connect.five.connectclient.util.UpdateCurrentPlayerStatusThread;

import org.springframework.beans.factory.annotation.Autowired;
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

    private Scanner sc = new Scanner(System.in);

    public void gamePlayHandler() throws Exception {

        // Step 1 loadWelcomeScreen
        try {
            this.loadWelcomeScreen();
        } catch (Exception e) {
            System.out.println("Error Connecting to Server : Please check server Status");
            System.exit(0);
        }

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
            System.exit(0);
        }
        if (this.game.getGameState().equals(GameState.INVALID.name())) {
            System.out.println("Game status invalid check or restart server");
            System.exit(0);
        }
        // this.startPlayerSession(30);
        for (int i = 3; i > 0; i--) {
            Thread.sleep(1000);
            System.out.println("Starting Game in " + i + "...");
        }
        // Step 4 Play game
        boolean winnerFlag = false;
        int i = 0, j = 0;
        while (winnerFlag == false) {
            i = 0;
            j = 0;
            Thread thread = new Thread(
                    new UpdateCurrentPlayerStatusThread(this.putCalls, this.game.getClientPlayerID()));
            try
            {
                thread.start();
            }catch(Exception e){}
            while (true) {

                String response = this.pollGame();
                String currentPlayerId = responseMapper.getCurrentPlayer(response);
                String playerStatus = responseMapper.getCurrentPlayerStatus(response);
                this.updateOpponentStatus();
                if (this.game.getWinningPlayerId() != null) {
                    winnerFlag = true;
                    break;
                }
                if (j == 2) {
                    this.game.setWinningPlayerId(this.game.getClientPlayerID());
                    System.out.println("Opponent Disconnected !!!!");
                    winnerFlag = true;
                    break;
                }
                if (currentPlayerId.equals(this.game.getClientPlayerID())) {
                    break;
                }
                Thread.sleep(2000);
                if (i % 10 == 0) {
                    System.out.println("Waiting for opponent to make the move ...");
                }
                if (playerStatus.equals("Inactive")) {
                    j++;
                }
                i++;

            }
            if (winnerFlag) {
                break;
            }

            System.out.println();
            this.displayBoard();
            System.out.println();

            do {

                System.out.print("Make your move --> Enter Column : ");
                String move = String.valueOf(sc.next());
                try {
                    Integer.parseInt(move);
                } catch (Exception e) {
                    System.out.println("Error in Input : Enter a number  ");
                    continue;
                }

                String updateResponse = this.updateBoard(move);
                System.out.println(updateResponse);
                if (updateResponse.equals("INVALID_COLUMN")) {
                    System.out.println("Invalid column input : Re-Enter : ");
                    continue;
                } else if (updateResponse.equals("WINNER")) {
                    this.pollGame();
                    winnerFlag = true;
                }
                break;
            } while (true);

            System.out.println();
            this.displayBoard();
            System.out.println();
        }
        // Step 5 Display winner
        this.printWinner();
    }

    private void updateOpponentStatus() throws Exception {
        this.putCalls.callOpponentUpdateStatus(this.game.getClientPlayerID());
    }

    private void printWinner() {
        System.out.println(this.game.getClientPlayerID());
       
        if (this.game.getClientPlayerID().equals(this.game.getWinningPlayerId())) {
            System.out.println(this.game.getWinningPlayerId());
            System.out.println("You Win !!!");
            System.exit(0);
        }else if(this.game.getWinningPlayerId()==null){
            System.out.println("You Win !!! :))");
            System.exit(0);
        }
         else {
            System.out.println("You Lose !!!");
            System.exit(0);
        }
    }

    private String updateBoard(String move) throws IOException {
        String response = putCalls.callMakeMoveApi(move, this.game.getClientPlayerID());
        return response;
    }

    private String pollGame() throws Exception {
        String response;
        do {
            response = getCalls.callBoardStatusApi();
        } while (response == null);
        return response;
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
        ArrayList<String> tokenList = new ArrayList<>();
        if (this.game.getGameState().equals(GameState.NEW.name())) {
            for (int i = 0; i < gameConfig.getPlayerToken().length; i++)
                tokenList.add(gameConfig.getPlayerToken()[i]);
            // System.out.println(i + ". " + gameConfig.getPlayerToken()[i - 1]);
        }

        if (this.game.getGameState().equals(GameState.WAITING.name())) {
            for (int i = 0; i < gameConfig.getPlayerToken().length; i++) {
                if (!(this.game.getPlayer1TokenColor().equals(gameConfig.getPlayerToken()[i])))
                    tokenList.add(gameConfig.getPlayerToken()[i]);
            }

        }
        String choice = "";
        do {
            for (int i = 1; i <= tokenList.size(); i++)
                System.out.println(i + ". " + tokenList.get(i - 1));
            System.out.print("Choose token Colour: ");
            choice = String.valueOf(sc.next().charAt(0));
            try {
                if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= tokenList.size())
                    break;
            } catch (Exception e) {
            }
            System.out.println("Incorrect Choice: Please choose correct option from list");
        } while (true);
        String playerToken = "";
        if (this.game.getGameState().equals(GameState.NEW.name()))
            playerToken = gameConfig.getPlayerToken()[Integer.parseInt(choice) - 1];
        if (this.game.getGameState().equals(GameState.WAITING.name()))
            playerToken = tokenList.get(Integer.parseInt(choice) - 1);
        putCalls.addNewPlayer(playerName.trim(), playerToken);
    }

    private void loadWelcomeScreen() throws IOException {
        // Starting Game
        String response = getCalls.callWelcomeApi();
        System.out.println("*********************************");
        System.out.print("*    ");
        System.out.print(response);
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

package com.game.connect.five;

import java.io.IOException;
import java.util.Scanner;

import com.game.connect.five.gameplay.Game;
import com.game.connect.five.rest.calls.GetCalls;
import com.game.connect.five.rest.calls.PostCalls;

public final class App {

    Game game;

    private App() {
    }

    public static void main(String[] args) throws IOException {
        GetCalls getCalls = new GetCalls();
        PostCalls postCalls = new PostCalls();
        Game game = new Game(getCalls, postCalls);

        // Starting Game
        System.out.println("*********************************");
        System.out.print("*    ");
        System.out.print(getCalls.callWelcomeApi());
        System.out.println("      *");
        System.out.println("*********************************");

        // Setting up client input
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {

            try {
                System.out.println();
                System.out.println("1. Start a new game as Player-1");
                System.out.println("2. Join current game as Player-2");
                System.out.println("3. Exit game");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input !! Enter integer");
                continue;
            }
            if (choice == 3) {

                System.out.println("*********************************");
                System.out.println("*           Thank you !!        *");
                System.out.println("*        See you Later!! :)     *");
                System.out.println("*********************************");
                sc.close();
                System.exit(0);

            }

            // startNewgame
            if (choice == 1) {
                newGame(game, sc);
            }

        } while (true);

    }

    private static void newGame(Game game, Scanner sc) {
        System.out.println("\n\n\n");
        System.out.println("*********************************");
        System.out.println("*       Starting New Game       *");
        System.out.println("*********************************");

        do {
            System.out.println();
            System.out.print("Player 1 enter your name : ");
            String playerName = sc.next();
            System.out.print("Enter choice of character to indicatre your token : ");
            String playerToken = String.valueOf(sc.next().charAt(0));
            System.out
                    .println("Starting game with \n Player name: " + playerName + "\n Token indicator: " + playerToken);
            System.out.println("Starting game in ...");
            for (int i = 3; i > 0; i--) {
                System.out.println(i + "...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            game.startNewGame(playerName, playerToken);

            System.out.println("Game started \n Your Game ID : " + game.);
            System.out.println("Waiting for Player-2 to join (Share Game ID with player 2) ....");

            // Polling player 2 until he joins
            game.pollPlayer2(gameId);
            break;
        } while (true);
    }

}

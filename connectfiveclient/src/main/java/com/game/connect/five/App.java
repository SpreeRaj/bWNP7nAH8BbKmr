package com.game.connect.five;

import java.io.IOException;
import java.util.Scanner;

import com.game.connect.five.calls.GetCalls;
import com.game.connect.five.calls.PostCalls;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
        GetCalls getCalls = new GetCalls();
        PostCalls postCalls = new PostCalls();

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
                System.out.println("1. Start a new game");
                System.out.println("2. Join current game");
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

        } while (true);

    }
}

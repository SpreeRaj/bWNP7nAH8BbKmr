package com.game.connect.five;

import java.io.IOException;

import com.game.connect.five.calls.GetCalls;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        GetCalls getCalls = new GetCalls();
        getCalls.callWelcomeApi();
    }
}

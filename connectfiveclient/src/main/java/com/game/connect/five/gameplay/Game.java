package com.game.connect.five.gameplay;

import com.game.connect.five.gameplay.model.GameDetails;
import com.game.connect.five.rest.calls.GetCalls;
import com.game.connect.five.rest.calls.PostCalls;

public class Game {

    public GetCalls getCalls;
    public PostCalls postCalls;

    public GameDetails gameDetails;

    public Game(GetCalls getCalls, PostCalls postCalls) {
        this.getCalls = getCalls;
        this.postCalls = postCalls;
    }

    public void startNewGame(String playerName, String playerToken) {
        this.playerOneName = playerName;
        this.playerOneToken = playerToken;

    }

    public void pollPlayer2(String gameId) {

    }

}

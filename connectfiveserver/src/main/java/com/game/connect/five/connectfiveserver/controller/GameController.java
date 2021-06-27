package com.game.connect.five.connectfiveserver.controller;

import com.game.connect.five.connectfiveserver.model.GameBoard;
import com.game.connect.five.connectfiveserver.service.GameManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameManager gameManager;
    GameBoard gameBoard;

    @GetMapping("/welcome")
    public String helloWorld() {
        return "Welocome to Connect 5";
    }

    @GetMapping("/getGameStatus")
    public @ResponseBody GameManager getGameStatus() {
        return gameManager;

    }

    @PostMapping(value = "/startNewGame", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody GameManager StartNewGame(String playerName, String PlayerToken, int boardRow,
            int boardColumn) {
        gameManager.startNewGame(playerName, PlayerToken, boardRow, boardColumn);
        return gameManager;

    }

}

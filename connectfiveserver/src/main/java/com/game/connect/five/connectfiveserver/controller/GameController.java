package com.game.connect.five.connectfiveserver.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.GameBoardSize;
import com.game.connect.five.connectfiveserver.model.Player;
import com.game.connect.five.connectfiveserver.service.GameManager;
import com.game.connect.five.connectfiveserver.util.BoardHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameManager gameManager;
    @Autowired
    Game game;
    @Autowired
    BoardHandler boardHandler;

    @GetMapping("/welcome")
    public String helloWorld() {
        return "Welocome to Connect 5";
    }

    @GetMapping("/getGameStatus")
    public @ResponseBody Game getGameStatus() {
        return game;

    }

    @GetMapping("/getBoardStatus")
    public @ResponseBody BoardHandler getBoardStatus() {
        return boardHandler;

    }

    @PutMapping("/startNewGame")
    public @ResponseBody Game StartNewGame(@RequestParam int row, @RequestParam int column) {
        String status = gameManager.startNewGame(new GameBoardSize(row,column));
        return game;
    }

    @PutMapping("/addPlayerDetails")
    public @ResponseBody String AddPlayerDetails(@RequestParam String playerName,
            @RequestParam String playerTokenColor) {
        Player player = gameManager.addPlayerDetails(playerName, playerTokenColor);
        if (player != null)
            return player.getUniqueID();
        else
            return "Game Full";
    }

    @PutMapping("/move/{playerId}")
    public @ResponseBody String makeMove(@RequestParam int column, @PathVariable String playerId) {
        String status = gameManager.updateBoard(column,playerId);
        return status;
    }

}

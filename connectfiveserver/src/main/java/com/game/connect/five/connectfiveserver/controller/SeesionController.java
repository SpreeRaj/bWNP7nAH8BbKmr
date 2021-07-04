package com.game.connect.five.connectfiveserver.controller;

import com.game.connect.five.connectfiveserver.service.SessionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game/session")
public class SeesionController {
    
    
    @Autowired
    private SessionManager sessionManager;

    @PutMapping("/currentPlayerStatus/{playerId}")
    public @ResponseBody String updateCurrentPlayer(@PathVariable String playerId) {
        String status=this.sessionManager.updatecurrentPlayerStatus(playerId);
        return status;
    }

    @PutMapping("/updateOpponentStatus/{playerId}")
    public @ResponseBody String updateOppnent(@PathVariable String playerId) {
        String status=this.sessionManager.updateOpponentStatus(playerId);
        return status;
    }
}
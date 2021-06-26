package com.game.connect.five.connectfiveserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @GetMapping("/welcome")
    public String helloWorld() {
        return "Welocome to Connect 5";
    }
}

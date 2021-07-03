package com.game.connect.five.connectclient.config;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class GameConfig {
    public final String[] PlayerToken= {"RED", "BLUE", "GREEN", "YELLOW"};
}

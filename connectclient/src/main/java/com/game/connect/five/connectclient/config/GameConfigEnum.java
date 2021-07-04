package com.game.connect.five.connectclient.config;

import org.springframework.stereotype.Component;



@Component
public class GameConfigEnum {

    public enum GameState {NEW, WAITING, FULL, COMPLETE, INVALID}
    public enum PlayerState {ACTIVE, INACTIVE}
    
    
}

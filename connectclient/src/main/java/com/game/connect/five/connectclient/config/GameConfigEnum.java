package com.game.connect.five.connectclient.config;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class GameConfigEnum {

    public enum GameState {NEW, WAITING, FULL, COMPLETE, INVALID}
    public enum PlayerState {ACTIVE, INACTIVE}
    
    
}

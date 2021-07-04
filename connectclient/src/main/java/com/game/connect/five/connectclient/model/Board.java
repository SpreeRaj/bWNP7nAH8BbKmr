package com.game.connect.five.connectclient.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Board {
    
    private ArrayList board[][];

    public void setBoard(ArrayList updatedBoard) {
    }
}

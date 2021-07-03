package com.game.connect.five.connectclient.play;

import java.io.IOException;

import com.game.connect.five.connectclient.service.GetCalls;
import com.game.connect.five.connectclient.service.PostCalls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamePlay {

    @Autowired
    private GetCalls getCalls;
    @Autowired
    private PostCalls postCalls;


    public void gamePlayHandler() {
        try{
            this.loadWelcomeScreen();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void loadWelcomeScreen() throws IOException {
        // Starting Game
        System.out.println("*********************************");
        System.out.print("*    ");
        System.out.print(getCalls.callWelcomeApi());
        System.out.println("      *");
        System.out.println("*********************************");
    }
}

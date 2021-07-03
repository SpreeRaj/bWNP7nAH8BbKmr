package com.game.connect.five.connectclient.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.game.connect.five.connectclient.model.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    @Autowired
    Game game;

    public void updateGameObject(String response) {

        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        Map<String, Object> responseMap = jsonParser.parseMap(response);
        LinkedHashMap player1=(LinkedHashMap) responseMap.get("player1");
        LinkedHashMap player2=(LinkedHashMap) responseMap.get("player2");

        game.setPlayer1Name(player1.get("playerName")!=null?player1.get("playerName").toString():null);
        game.setPlayer1Status(player1.get("playerStatus")!=null?player1.get("playerStatus").toString():null);
        game.setPlayer1TokenColor(player1.get("playerTokenColor")!=null?player1.get("playerTokenColor").toString():null);
        game.setPlayer1uniqueID(player1.get("uniqueID")!=null?player1.get("uniqueID").toString():"test");

        game.setPlayer2Name(player2.get("playerName")!=null?player2.get("playerName").toString():null);
        game.setPlayer2Status(player2.get("playerStatus")!=null?player2.get("playerStatus").toString():null);
        game.setPlayer2TokenColor(player2.get("playerTokenColor")!=null?player2.get("playerTokenColor").toString():null);
        game.setPlayer2uniqueID(player2.get("uniqueID")!=null?player2.get("uniqueID").toString():null);
    }
    
}

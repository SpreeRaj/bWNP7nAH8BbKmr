package com.game.connect.five.connectclient.util;


import java.util.LinkedHashMap;
import java.util.Map;

import com.game.connect.five.connectclient.model.Game;

import org.json.JSONArray;
import org.json.JSONObject;
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
        LinkedHashMap player1 = (LinkedHashMap) responseMap.get("player1");
        LinkedHashMap player2 = (LinkedHashMap) responseMap.get("player2");

        game.setPlayer1Name(player1.get("playerName") != null ? player1.get("playerName").toString() : null);
        game.setPlayer1Status(player1.get("playerStatus") != null ? player1.get("playerStatus").toString() : null);
        game.setPlayer1TokenColor(
                player1.get("playerTokenColor") != null ? player1.get("playerTokenColor").toString() : null);
        game.setPlayer1uniqueID(player1.get("uniqueID") != null ? player1.get("uniqueID").toString() : "test");

        game.setPlayer2Name(player2.get("playerName") != null ? player2.get("playerName").toString() : null);
        game.setPlayer2Status(player2.get("playerStatus") != null ? player2.get("playerStatus").toString() : null);
        game.setPlayer2TokenColor(
                player2.get("playerTokenColor") != null ? player2.get("playerTokenColor").toString() : null);
        game.setPlayer2uniqueID(player2.get("uniqueID") != null ? player2.get("uniqueID").toString() : null);

        
    }

    public void printBoard(String response) {
        JSONObject json = new JSONObject(response);
        JSONObject data = json.getJSONObject("gameBoard");
        JSONArray board = data.getJSONArray("board");
        String token="";
        for (int i = board.length()-1; i>=0; i--) {
            JSONArray boardRow = board.getJSONArray(i);
            for (int j = 0; j < boardRow.length(); j++) {
                if (boardRow.get(j).equals(""))
                    System.out.print("' ' ");
                else{
                    token=(String) boardRow.get(j);
                    System.out.print("'"+token.charAt(0)+"' ");
                }
            }
            System.out.println();
        }
        JSONArray boardRow = board.getJSONArray(0);
        for(int i=1 ; i<=boardRow.length() ; i++)
            System.out.print("|"+i+"| ");

    }

    public String getCurrentPlayer(String response) {
        JSONObject json = new JSONObject(response);
        JSONObject data = json.getJSONObject("currentPlayer");
        String playerId=(String) data.get("uniqueID");
        try{
            JSONObject data2 = json.getJSONObject("winningPlayer");
            String winningPlayerId=(String) data2.get("uniqueID");
            this.game.setWinningPlayerId(winningPlayerId);
        }catch(Exception e){}
        return playerId.toString();
    }

    public String getCurrentPlayerStatus(String response) {
        JSONObject json = new JSONObject(response);
        JSONObject data = json.getJSONObject("currentPlayer");
        String playerStatus=(String) data.get("playerStatus");
      //  System.out.println(playerStatus);
        
        return playerStatus.toString();
    }

}

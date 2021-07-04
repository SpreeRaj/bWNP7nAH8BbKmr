package com.game.connect.five.connectclient.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.game.connect.five.connectclient.model.RequestSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@Getter
@Setter
public class PutCalls {
    @Autowired
    RequestSetup requestSetup;

    //URLEncoder.encode(pair.getName(), "UTF-8")

    public String setupGame(int row, int column) throws Exception {
        requestSetup.setUrl(new URL(requestSetup.getHost() + "startNewGame?row="+row+"&column="+column));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("PUT");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        con.disconnect();
        //System.out.println(response.toString());
        return response.toString();
    }

    public String addNewPlayer(String playerName, String playerToken)throws Exception {
        requestSetup.setUrl(new URL(requestSetup.getHost() + "addPlayerDetails?playerName="+playerName+"&playerTokenColor="+playerToken));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("PUT");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        con.disconnect();
        System.out.println(response.toString());
        return response.toString();
    }

    public String callMakeMoveApi(String move, String playerId) throws IOException {
        requestSetup.setUrl(new URL(requestSetup.getHost() +"/move/"+playerId+ "?column="+move));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("PUT");
        con.setConnectTimeout(500000);
        con.setReadTimeout(500000);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        con.disconnect();
        //System.out.println(response.toString());
        return response.toString();
    }

    

    




}

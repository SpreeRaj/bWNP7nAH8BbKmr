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
public class GetCalls {

    @Autowired
    RequestSetup requestSetup;

    public String callWelcomeApi() throws IOException {

        requestSetup.setUrl(new URL(requestSetup.getHost() + "welcome"));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("GET");
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
        return response.toString();
    }

    public String callGameStatusApi() throws IOException {
        requestSetup.setUrl(new URL(requestSetup.getHost() + "getGameStatus"));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("GET");
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
       // System.out.println(response.toString());
        return response.toString();
    }

    public String callBoardStatusApi() throws Exception {
        requestSetup.setUrl(new URL(requestSetup.getHost() + "getBoardStatus"));
        requestSetup.setCon((HttpURLConnection) requestSetup.getUrl().openConnection());
        HttpURLConnection con = requestSetup.getCon();
        con.setRequestMethod("GET");
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
       // System.out.println(response.toString());
        return response.toString();
    }

}

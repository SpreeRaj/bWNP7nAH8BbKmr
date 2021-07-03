package com.game.connect.five.connectclient.model;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class RequestSetup {

    private URL url;
    private HttpURLConnection con;
    private String host="http://127.0.0.1:8080/game/";

    //public void callSetup()
    
}

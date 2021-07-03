package com.game.connect.five.connectclient;

import com.game.connect.five.connectclient.play.GamePlay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConnectclientApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(ConnectclientApplication.class, args);
		GamePlay gamePlay = context.getBean(GamePlay.class);
		gamePlay.gamePlayHandler();
	}

}

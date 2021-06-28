package com.game.connect.five.connectfiveserver.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
public class Player {

   private final String uniqueID = UUID.randomUUID().toString();
   private String playerName;
   private String playerTokenColor;
   private String playerStatus;
    
}

package com.game.connect.five.connectclient.service;

import com.game.connect.five.connectclient.model.RequestSetup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@Getter@Setter
public class PostCalls {
    @Autowired
    RequestSetup requestSetup;
}

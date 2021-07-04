package com.game.connect.five.connectfiveserver.service;

import com.game.connect.five.connectfiveserver.model.Game;
import com.game.connect.five.connectfiveserver.model.GameBoard;
import com.game.connect.five.connectfiveserver.model.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    Player player1=new Player("Ron", "RED", "Active", 5);
    Player player2=new Player("Harry", "test", "Active", 5);
    GameBoard gameBoard=new GameBoard(new String[6][9], 5);
    Game game;
    @BeforeEach
    void setUp() {
        game=new Game(gameBoard, player1, player2);
        
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sessionManagerNoargsCheck() {
        SessionManager sessionManager=new SessionManager();
        assertNotNull(sessionManager);
    }

    @Test
    void updatePlayer2StatusByPlayer1() {
        SessionManager sessionManager=new SessionManager(game);
        sessionManager.updateOpponentStatus(game.getPlayer1().getUniqueID());
        assertEquals("Inactive", game.getPlayer2().getPlayerStatus());
    }

    @Test
    void updatePlayer1StatusByPlayer2() {
        SessionManager sessionManager=new SessionManager(game);
        sessionManager.updateOpponentStatus(game.getPlayer2().getUniqueID());
        assertEquals("Inactive", game.getPlayer1().getPlayerStatus());
    }

    @Test
    void updatePlayer1StatusByIncorrectPlayer() {
        SessionManager sessionManager=new SessionManager(game);
        String s=sessionManager.updateOpponentStatus("incorrect unique ID");
        assertEquals("Update Error",s);
    }

    @Test
    void updatePlayer1StatusByPlayer1() {
        SessionManager sessionManager=new SessionManager(game);
        sessionManager.updatecurrentPlayerStatus(game.getPlayer1().getUniqueID());
        assertEquals("Active", game.getPlayer1().getPlayerStatus());
    }

    @Test
    void updatePlayer2StatusByPlayer2() {
        SessionManager sessionManager=new SessionManager(game);
        sessionManager.updatecurrentPlayerStatus(game.getPlayer2().getUniqueID());
        assertEquals("Active", game.getPlayer2().getPlayerStatus());
    }

    @Test
    void updateCurrentPlayerStatusByIncorrectPlayer() {
        SessionManager sessionManager=new SessionManager(game);
        String s=sessionManager.updatecurrentPlayerStatus("incorrect unique ID");
        assertEquals("Update Error",s);
    }

}
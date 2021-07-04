package com.game.connect.five.connectclient.util;

import com.game.connect.five.connectclient.service.PutCalls;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCurrentPlayerStatusThread implements Runnable {

    private PutCalls putCalls;
    private String clientPlayerID;

    @Override
    public void run() {

        try {
            while (true) {
                this.putCalls.callCurrentPlayerUpdateStatus(this.clientPlayerID);
                Thread.sleep(1000);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}

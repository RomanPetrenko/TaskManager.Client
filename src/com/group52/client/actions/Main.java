package com.group52.client.actions;

import com.group52.client.view.MainPanel;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Handler.class);
        log.info("Start");

        Notificator notificator = new Notificator();
        Thread notificationThread = new Thread(notificator);
        notificationThread.start();

        MainPanel theView = new MainPanel();

        ServerDialog serverDialog = null;
        try {
            serverDialog = new ServerDialog(theView.getServerAddress());
        } catch (IOException ioe) {
            theView.displayErrorMessage(ioe.getMessage());
            log.error("InputOutput exception: ", ioe);
            ioe.printStackTrace();
        }
        new Handler(theView, serverDialog, notificator);


        /*
        ServerDialog serverDialog = new ServerDialog();//for testing
        new Handler(theView, serverDialog, notificator); //for testing
        theView.setVisible(true); //for testing
        */
    }
}

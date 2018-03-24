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

        MainPanel mainPanel = new MainPanel();

        ServerDialog serverDialog = null;
        try {
            serverDialog = new ServerDialog(mainPanel.getServerAddress());
        } catch (IOException ioe) {
            mainPanel.displayErrorMessage(ioe.getMessage());
            log.error("InputOutput exception: ", ioe);
            ioe.printStackTrace();
        }
        new Handler(mainPanel, serverDialog, notificator);


        /*
        ServerDialog serverDialog = new ServerDialog();//for testing
        new Handler(theView, serverDialog, notificator); //for testing
        */
        //mainPanel.setVisible(true); //for testing
    }
}

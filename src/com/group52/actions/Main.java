package com.group52.actions;

import com.group52.view.MainPanel;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Handler.class);
        log.info("Start");

        MainPanel theView = new MainPanel();

        ServerDialog serverDialog = null;
        try {
            serverDialog = new ServerDialog(theView.getServerAddress());
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }

        Handler handler = new Handler(theView, serverDialog);
        theView.setVisible(true);

    }
}

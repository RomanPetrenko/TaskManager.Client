package com.group52.actions;

import com.group52.view.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Handler.class);
        log.info("Start");

        MainPanel theView = new MainPanel();

        /*
        ServerDialog serverDialog = null;
        try {
            serverDialog = new ServerDialog(theView.getServerAddress());
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }
        */

        ServerDialog serverDialog = new ServerDialog();

        Handler handler = new Handler(theView, serverDialog);
        //handler.showTaskList();


        theView.setVisible(true);
    }
}

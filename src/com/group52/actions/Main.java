package com.group52.actions;

import com.group52.view.*;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Handler.class);
        log.info("Start");

        MainPanel theView = new MainPanel();
        /*
        try {
            ServerDialog serverDialog = new ServerDialog(theView.getServerAddress());
            new Handler(theView, serverDialog);
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }
        */

        ServerDialog serverDialog = new ServerDialog();
        new Handler(theView, serverDialog);

    }
}

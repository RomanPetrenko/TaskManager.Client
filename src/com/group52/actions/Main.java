package com.group52.actions;

import com.group52.view.MainPanel;
import org.apache.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(ActionsController.class);
        log.info("Start");

        MainPanel theView = new MainPanel();
        Controller theController = new Controller(); //Natalya's part

        ActionsController actionsController = new ActionsController(theView, theController);
        theView.setVisible(true);
    }
}

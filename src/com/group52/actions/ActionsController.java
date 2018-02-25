package com.group52.actions;
import com.group52.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.*;
import java.util.*;

import org.apache.log4j.*;

public class ActionsController implements ActionListener {

    private Logger log = Logger.getLogger(ActionsController.class);
    private MainPanel mainPanel;
    private Controller controller;

    public ActionsController(MainPanel mainPanel, Controller controller) {
        this.mainPanel = mainPanel;
        this.controller = controller;
    }

    private void editTasksToComboBox () { }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(mainPanel.createTaskFormButton)) {
                CreateTaskForm createTaskForm = new CreateTaskForm();
                controller.createTask(createTaskForm.getTitle());
            }

            if (e.getSource().equals(mainPanel.exitButton)) {
                log.info("Exit");
                mainPanel.setVisible(false);
                mainPanel.dispose();
                System.exit(0);
            }
        }
        catch (IllegalArgumentException iae){
            mainPanel.displayErrorMessage(iae.getMessage());
            log.error("IllegalArgumentException: ", iae);
        }
        catch (IOException io) {
            mainPanel.displayErrorMessage(io.getMessage());
            log.error("IOException: ", io);
        }
        catch (NullPointerException npe) {
            mainPanel.displayErrorMessage(npe.getMessage());
            log.error("NullPointerException: ", npe);
        }
        catch (IndexOutOfBoundsException ioe) {
            mainPanel.displayErrorMessage(ioe.getMessage());
            log.error("IndexOutOfBoundsException: ", ioe);
        }
        catch (NoSuchElementException nse) {
            mainPanel.displayErrorMessage(nse.getMessage());
            log.error("NoSuchElementException: ", nse);
        }
        catch (ParseException pe) {
            mainPanel.displayErrorMessage(pe.getMessage());
            log.error("Incorrect format: ", pe);
        }
        catch (Exception ex){
            mainPanel.displayErrorMessage(ex.getMessage());
            log.error("Exception: ", ex);
        }
    }

}





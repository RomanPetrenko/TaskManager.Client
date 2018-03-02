package com.group52.actions;
import com.group52.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import org.apache.log4j.*;

public class Handler implements ActionListener {

    private Logger log = Logger.getLogger(Handler.class);
    private MainPanel mainPanel;
    private ServerDialog serverDialog;

    public Handler(MainPanel mainPanel, ServerDialog serverDialog) {
        this.mainPanel = mainPanel;
        this.serverDialog = serverDialog;
    }

    private void editTasksToComboBox () { }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(mainPanel.createTaskFormButton)) {
                CreateTaskForm taskForm = new CreateTaskForm();
                serverDialog.sendXMLRequest("create");
                if (serverDialog.getResponseFromServer().equals("OK")){
                    String title = taskForm.getTitle();
                    String description = taskForm.getDescription();
                    String time = taskForm.getTime();
                    String contact = taskForm.getContact();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML(title,description,time,contact));
                }

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
        catch (Exception ex){
            mainPanel.displayErrorMessage(ex.getMessage());
            log.error("Exception: ", ex);
        }
    }

}





package com.group52.actions;
import com.group52.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.group52.view.Calendar;
import org.apache.log4j.*;

public class Handler {

    private Logger log = Logger.getLogger(Handler.class);
    private MainPanel mainPanel;
    private ServerDialog serverDialog;

    public Handler(MainPanel mainPanel, ServerDialog serverDialog) {
        this.mainPanel = mainPanel;
        this.serverDialog = serverDialog;
        new Handler.Listener();
    }

    public void showTaskList () {
        serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("View"));
        mainPanel.showTaskList(XMLParse.parseTasksFromXML(serverDialog.getResponseFromServer()));
    }

    private void editTasksToComboBox () { }



    public class Listener implements ActionListener {

        private WelcomeForm welcomeForm = new WelcomeForm();
        private NewUserForm newUserForm = new NewUserForm();
        private AuthorizationForm authorizationForm = new AuthorizationForm();
        private AddTaskForm addTaskForm = new AddTaskForm("Unrepeatable");

        public Listener() {
            Listener listener = this;
            mainPanel.addListener(listener);
            welcomeForm.addListener(listener);
            newUserForm.addListener(listener);
            authorizationForm.addListener(listener);
            addTaskForm.addListener(listener);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource().equals(welcomeForm.signUpButton)) newUserForm.open();
                if (e.getSource().equals(newUserForm.cancelButton)) newUserForm.close();

                if (e.getSource().equals(welcomeForm.signInButton)) authorizationForm.open();
                if (e.getSource().equals(authorizationForm.cancelButton)) authorizationForm.close();

                if (e.getSource().equals(mainPanel.unrepeatableTaskFormButton)) addTaskForm.open();
                if (e.getSource().equals(mainPanel.cancelButton)) addTaskForm.close();

                if (e.getSource().equals(addTaskForm.unrepeatableTaskButton)) {
                    String title = addTaskForm.getTitle();
                    String description = addTaskForm.getDescription();
                    String time = addTaskForm.getTime();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("Create", title, description, time));
                }


                if (e.getSource().equals(mainPanel.calendarFormButton)) {
                    new Calendar(false);
                }
                if (e.getSource().equals(mainPanel.exitButton)) {
                    System.out.println("Bye");
                    log.info("Exit");
                    //serverDialog.close();
                    mainPanel.setVisible(false);
                    mainPanel.dispose();
                    System.exit(0);
                }
            } catch (IllegalArgumentException iae) {
                mainPanel.displayErrorMessage(iae.getMessage());
                log.error("IllegalArgumentException: ", iae);
            } catch (NullPointerException npe) {
                mainPanel.displayErrorMessage(npe.getMessage());
                log.error("NullPointerException: ", npe);
                npe.printStackTrace();
            } catch (IndexOutOfBoundsException ioe) {
                mainPanel.displayErrorMessage(ioe.getMessage());
                log.error("IndexOutOfBoundsException: ", ioe);
            } catch (NoSuchElementException nse) {
                mainPanel.displayErrorMessage(nse.getMessage());
                log.error("NoSuchElementException: ", nse);
            } catch (Exception ex) {
                mainPanel.displayErrorMessage(ex.getMessage());
                log.error("Exception: ", ex);
                ex.printStackTrace();
            }
        }
    }
}





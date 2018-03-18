package com.group52.actions;
import com.group52.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.group52.view.Calendar;
import org.apache.log4j.*;

import javax.xml.bind.JAXBException;

public class Handler {

    private Logger log = Logger.getLogger(Handler.class);
    private MainPanel mainPanel;
    private ServerDialog serverDialog;

    public Handler(MainPanel mainPanel, ServerDialog serverDialog) {
        this.mainPanel = mainPanel;
        this.serverDialog = serverDialog;
        new Handler.Listener();
    }

    private void showTaskList () {
        try {
            serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("view"));
            mainPanel.showTaskList(XMLParse.getTaskFromXML(serverDialog.getResponseFromServer()));
        } catch (JAXBException e) {
            mainPanel.displayErrorMessage("parse exception");
            log.error(e);
            e.printStackTrace();
        }

    }

    private void editTasksToComboBox () { }

    public class Listener implements ActionListener {

        private WelcomeForm welcomeForm = new WelcomeForm();
        private SignUpForm signUpForm = new SignUpForm();
        private SignInForm signInForm = new SignInForm();
        private AddTaskForm addTaskForm = new AddTaskForm("Unrepeatable");

        public Listener() {
            Listener listener = this;
            mainPanel.addListener(listener);
            welcomeForm.addListener(listener);
            signUpForm.addListener(listener);
            signInForm.addListener(listener);
            addTaskForm.addListener(listener);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                if (e.getSource().equals(welcomeForm.signUpButton)) signUpForm.open();
                if (e.getSource().equals(signUpForm.cancelButton)) signUpForm.close();

                if (e.getSource().equals(welcomeForm.signInButton)) signInForm.open();
                if (e.getSource().equals(signInForm.cancelButton)) signInForm.close();

                if (e.getSource().equals(mainPanel.unrepeatableTaskFormButton)) addTaskForm.open();
                if (e.getSource().equals(mainPanel.cancelButton)) addTaskForm.close();

                if (e.getSource().equals(signUpForm.confirmButton)) {
                    String login = signUpForm.getLogin();
                    String password =  signUpForm.getPassword();
                    String repeatedPassword = signUpForm.getRepeatedPassword();

                    if (!password.equals(repeatedPassword))
                        mainPanel.displayMessage("Password false");
                    else XMLParse.createClient(login, Integer.parseInt(password), 0);

                    serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("Client"));
                   // int code = XMLParse.getCodeFromXML(serverDialog.getResponseFromServer());
                    //if (code == 200) {
                        mainPanel.displayMessage("Successful");
                        signUpForm.close();
                        welcomeForm.close();
                        showTaskList();
                        mainPanel.open();
                   // }
                }

                if (e.getSource().equals(signInForm.confirmButton)) {
                    String login = signInForm.getLogin();
                    String password =  signInForm.getPassword();
                    XMLParse.createClient(login, Integer.parseInt(password), 0);
                    serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("oneMoreUser"));
                    XMLParse.createClient(login, Integer.parseInt(password), 0);

                    //int code = XMLParse.getCodeFromXML(serverDialog.getResponseFromServer());
                   // if (code == 200) {
                        mainPanel.displayMessage("Avtorized");
                        signInForm.close();
                        welcomeForm.close();
                        showTaskList();
                        mainPanel.open();
                    //}
                }

                if (e.getSource().equals(addTaskForm.unrepeatableTaskButton)) {
                    String title = addTaskForm.getTitle();
                    String description = addTaskForm.getDescription();
                    String time = addTaskForm.getTime();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("Create", title, description, time, true));
                }

                if (e.getSource().equals(mainPanel.calendarFormButton)) {
                    new Calendar(false);
                }
                if (e.getSource().equals(mainPanel.exitButton)) {
                    log.info("Exit");
                    //serverDialog.close();
                    mainPanel.setVisible(false);
                    mainPanel.dispose();
                    System.exit(0);
                    //Main.main(new String[]{});
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





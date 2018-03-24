package com.group52.client.actions;
import com.group52.client.view.*;
import com.group52.client.view.Calendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.*;

import org.apache.log4j.*;

import javax.swing.*;
import javax.xml.bind.JAXBException;

public class Handler {

    private Logger log = Logger.getLogger(Handler.class);
    private MainPanel mainPanel;
    private ServerDialog serverDialog;
    private Notificator notificator;

    public Handler(MainPanel mainPanel, ServerDialog serverDialog, Notificator notificator) {
        this.mainPanel = mainPanel;
        this.serverDialog = serverDialog;
        this.notificator = notificator;
        new Handler.Listener();
    }

    private File getResponseFromServer() throws ServerException, JAXBException {
        File file = serverDialog.getResponseFromServer();
        int code = XMLParse.getCodeFromXML(file);
        String status = XMLParse.getStatusFromXML(file);
        if (code == 400 || code == 401 || code == 404 || code == 405 || code ==415 || code == 500)
            throw new ServerException(status);
        return file;
    }

    private void updateTaskList () throws ServerException, JAXBException {
            serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("view"));
            File file = getResponseFromServer();
            if (XMLParse.getActionFromXML(file).equals("view"))
            mainPanel.showTaskList(XMLParse.getTasksFromXML((file)));

            serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("notification"));
            file = getResponseFromServer();
            if (XMLParse.getActionFromXML(file).equals("notification"))
                notificator.setTaskList(XMLParse.getTasks(file));
    }


    public class Listener implements ActionListener {

        private WelcomeForm welcomeForm = new WelcomeForm();
        private SignUpForm signUpForm = new SignUpForm();
        private SignInForm signInForm = new SignInForm();
        private AddTaskForm unrepeatableTaskForm = new AddTaskForm("Unrepeatable");
        private AddTaskForm repeatableTaskForm = new AddTaskForm("Repeatable");
        private EditTaskForm editTaskForm = new EditTaskForm();
        private DeleteTaskForm deleteTaskForm = new DeleteTaskForm();
        private NotificationForm notificationForm = new NotificationForm();

        public Listener() {
            Listener listener = this;
            mainPanel.addListener(listener);
            welcomeForm.addListener(listener);
            signUpForm.addListener(listener);
            signInForm.addListener(listener);
            unrepeatableTaskForm.addListener(listener);
            repeatableTaskForm.addListener(listener);
            editTaskForm.addListener(listener);
            deleteTaskForm.addListener(listener);
            notificationForm.addListener(listener);
        }

        private void editTasksToComboBox (JComboBox comboBox) throws ServerException, JAXBException {
            serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("view"));
            List<XMLParse.Task> tasks = XMLParse.getTasks(getResponseFromServer());
            comboBox.removeAllItems();
            for (XMLParse.Task task: tasks) {
                comboBox.addItem(task);
            }
        }

        public void actionPerformed(ActionEvent e) {
            notificator.setNotificationForm(notificationForm);
            try {
                if (e.getSource().equals(signUpForm.confirmButton)) {
                    String login = signUpForm.getLogin();
                    String password = signUpForm.getPassword();
                    String repeatedPassword = signUpForm.getRepeatedPassword();

                    if (!password.equals(repeatedPassword))
                        throw new IOException("Passwords don't match");
                    else XMLParse.createClient(login, password, 0);

                    serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("oneMoreUser"));
                    File response = getResponseFromServer();
                    int code = XMLParse.getCodeFromXML(response);
                    String status = XMLParse.getStatusFromXML(response);
                    if(code == 200 || code == 201 || code == 202) {
                        mainPanel.displayMessage(status);
                        XMLParse.setId(XMLParse.getUserIdFromXML(response));
                        signUpForm.close();
                        welcomeForm.close();
                        updateTaskList();
                        mainPanel.open();
                    }
                }

                if (e.getSource().equals(signInForm.confirmButton)) {
                    String login = signInForm.getLogin();
                    String password = signInForm.getPassword();
                    XMLParse.createClient(login, password, 0);
                    serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("user"));
                    XMLParse.createClient(login, password, 0);

                    File response = getResponseFromServer();
                    int code = XMLParse.getCodeFromXML(response);
                    String status = XMLParse.getStatusFromXML(response);
                    if(code == 200 || code == 201 || code == 202) {
                        mainPanel.displayMessage(status);
                        XMLParse.setId(XMLParse.getUserIdFromXML(response));
                        signInForm.close();
                        welcomeForm.close();
                        updateTaskList();
                        mainPanel.open();
                    }
                }
                if (e.getSource().equals(unrepeatableTaskForm.unrepeatableTaskButton)) {
                    String title = unrepeatableTaskForm.getTitle();
                    String description = unrepeatableTaskForm.getDescription();
                    long time = unrepeatableTaskForm.getStartTime();
                    boolean active = unrepeatableTaskForm.activeBox.isSelected();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("add",
                            title, description, time, 0, 0, 0, active));
                    updateTaskList();
                    unrepeatableTaskForm.close();
                }

                if (e.getSource().equals(repeatableTaskForm.repeatableTaskButton)) {
                    String title = repeatableTaskForm.getTitle();
                    String description = repeatableTaskForm.getDescription();
                    long start = repeatableTaskForm.getStartTime();
                    long end = repeatableTaskForm.getEndTime();
                    int interval = repeatableTaskForm.getInterval();
                    boolean active = repeatableTaskForm.activeBox.isSelected();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("add",
                            title, description, 0, start, end, interval, active));
                    updateTaskList();
                    repeatableTaskForm.close();
                }

                if (e.getSource().equals(editTaskForm.editTaskButton)) {
                    XMLParse.Task oldTask = (XMLParse.Task) editTaskForm.comboBox.getModel().getSelectedItem();
                    String title = editTaskForm.getTitle();
                    String description = editTaskForm.getDescription();
                    long time = 0;
                    long start = 0;
                    long end = 0;
                    int interval = 0;
                    if (oldTask.getInterval() != 0) {
                        start = editTaskForm.getStartTime();
                        end = editTaskForm.getEndTime();
                        interval = editTaskForm.getInterval();
                    } else time = editTaskForm.getStartTime();
                    boolean active = editTaskForm.activeBox.isSelected();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("edit",
                            oldTask, title, description, time, start, end, interval, active));
                    updateTaskList();
                    editTaskForm.close();
                }

                if (e.getSource().equals(deleteTaskForm.deleteTaskButton)) {
                    XMLParse.Task task = (XMLParse.Task) deleteTaskForm.comboBox.getModel().getSelectedItem();
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("delete", task));
                    updateTaskList();
                    deleteTaskForm.close();
                }

                if (e.getSource().equals(notificationForm.postponeTaskButton)) {
                    XMLParse.Task task = notificator.getTaskToPostpone();
                    long time = 0;
                    long start = 0;
                    if (task.getInterval() != 0)  time = task.getTime() + 300000;
                    else start = task.getStart() + 300000;
                    serverDialog.sendXMLToServer(XMLParse.parseTaskToXML("edit",
                            task, task.getTitle(), task.getDescription(), time,
                            start, task.getEnd(), task.getInterval(), task.isActive()));
                    updateTaskList();
                    notificationForm.close();
                }

                if (e.getSource().equals(mainPanel.calendarFormButton)) {
                    new Calendar(true);
                }
                if (e.getSource().equals(welcomeForm.signUpButton)) signUpForm.open();
                if (e.getSource().equals(signUpForm.cancelButton)) signUpForm.close();

                if (e.getSource().equals(welcomeForm.signInButton)) signInForm.open();
                if (e.getSource().equals(signInForm.cancelButton)) signInForm.close();

                if (e.getSource().equals(mainPanel.unrepeatableTaskFormButton)) unrepeatableTaskForm.open();
                if (e.getSource().equals(unrepeatableTaskForm.cancelButton)) unrepeatableTaskForm.close();

                if (e.getSource().equals(mainPanel.repeatableTaskFormButton)) repeatableTaskForm.open();
                if (e.getSource().equals(repeatableTaskForm.cancelButton)) repeatableTaskForm.close();

                if (e.getSource().equals(mainPanel.editTaskFormButton)) {
                    editTasksToComboBox(editTaskForm.comboBox);
                    editTaskForm.open();
                }
                if (e.getSource().equals(editTaskForm.cancelButton)) editTaskForm.close();

                if (e.getSource().equals(editTaskForm.comboBox)) {
                    XMLParse.Task task = (XMLParse.Task) editTaskForm.comboBox.getModel().getSelectedItem();
                    if (task.getInterval() == 0) editTaskForm.removeRepeatableFields();
                    else editTaskForm.addRepeatableFields();
                }

                if (e.getSource().equals(mainPanel.deleteTaskFormButton)) {
                    editTasksToComboBox(deleteTaskForm.comboBox);
                    deleteTaskForm.open();
                }
                if (e.getSource().equals(deleteTaskForm.cancelButton)) deleteTaskForm.close();

                if (e.getSource().equals(notificationForm.closeTaskButton)) notificationForm.close();

                if (e.getSource().equals(mainPanel.exitButton)) {
                    log.info("Logout");
                    serverDialog.sendXMLToServer(XMLParse.parseRequestToXML("close"));
                    serverDialog.close();
                    mainPanel.setVisible(false);
                    mainPanel.dispose();
                    //System.exit(0);
                    Main.main(new String[]{});
                }
            }
            catch (JAXBException jaxb) {
                mainPanel.displayErrorMessage("Parse exception");
                log.error(jaxb);
                jaxb.printStackTrace();
            } catch (ServerException se) {
                mainPanel.displayErrorMessage(se.getMessage());
                log.error(se);
                se.printStackTrace();
            } catch (IllegalArgumentException iae) {
                mainPanel.displayErrorMessage("Fields can't be empty");
                log.error("IllegalArgumentException: ", iae);
            } catch (NullPointerException npe) {
                mainPanel.displayErrorMessage("NullPointerException");
                log.error("NullPointerException: ", npe);
                npe.printStackTrace();
            } catch (IndexOutOfBoundsException ioe) {
                mainPanel.displayErrorMessage("IndexOutOfBoundsException");
                log.error("IndexOutOfBoundsException: ", ioe);
            } catch (NoSuchElementException nse) {
                mainPanel.displayErrorMessage(nse.getMessage());
                log.error("NoSuchElementException: ", nse);
            } catch (IOException io) {
                mainPanel.displayErrorMessage(io.getMessage());
                log.error("IOException: ", io);
            } catch (Exception ex) {
                mainPanel.displayErrorMessage(ex.getMessage());
                log.error("Exception: ", ex);
                ex.printStackTrace();
            }
        }
    }
}





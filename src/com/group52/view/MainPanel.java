package com.group52.view;

import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPanel extends JFrame implements Listenable, Closeable {


    public JButton createTaskFormButton = new JButton("Create task");
    public JButton exitButton = new JButton("Exit");

    public MainPanel() {
        JPanel mainPanel = new JPanel();
        this.setTitle("Task Manager");
        this.setSize(810,450);

        createTaskFormButton.setBounds(30,350,200,40);
        exitButton.setBounds(570,350,200,40);

        mainPanel.add(createTaskFormButton);
        mainPanel.add(exitButton);

        mainPanel.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
    }

    public String getServerAddress() {
        return JOptionPane.showInputDialog(
                new JFrame("IP Address"),
                "Enter IP Address of the Server:",
                "Welcome to the Task Manager",
                JOptionPane.QUESTION_MESSAGE);
    }

    public void showTaskList(String taskList) { }

    public void flushFields() { }

    public void close () {
        this.setVisible(false);
    }

    public void addTasksListener(ActionListener actionListener) { }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

}


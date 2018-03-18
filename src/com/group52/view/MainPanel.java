package com.group52.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainPanel extends JFrame implements Listenable, Closeable {

    private JTextArea tasksField = new JTextArea();

    public JTextField titleField = new JTextField(20);
    public JTextField descriptionField = new JTextField(20);
    public JTextField loginField = new JTextField(20);
    public JTextField calendarField = new JTextField();

    public JLabel titleLabel = new JLabel("Title");
    public JLabel descriptionLabel = new JLabel("Description");
    public JLabel loginLabel = new JLabel("Login");

    public JButton confirmButton = new JButton("Confirm");
    public JButton unrepeatableTaskFormButton = new JButton("Create task");
    public JButton repeatableTaskFormButton = new JButton("Create repeatable task");
    public JButton editTaskFormButton = new JButton("Edit Task");
    public JButton deleteTaskFormButton = new JButton("Delete Task");
    public JButton calendarFormButton = new JButton("Show calendar");

    public JButton cancelButton = new JButton("Cancel");
    public JButton exitButton = new JButton("Exit");

    public JCheckBox activeBox = new JCheckBox("is Active");
    public JSpinner spinner = new JSpinner();

    public MainPanel() {
        JPanel mainPanel = new JPanel();
        this.setTitle("Task Manager");
        this.setSize(810,450);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/welcome.png"));
        tasksField.setEditable(false);

        JScrollPane areaScrollPane = new JScrollPane(tasksField);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setBounds(30,10, 740, 280);
        getContentPane().add(areaScrollPane);

        titleLabel.setBounds(150,5,280,15);
        descriptionLabel.setBounds(150,55,280,15);
        loginLabel.setBounds(150,5,280,15);

        titleField.setBounds(30,20,280,30);
        descriptionField.setBounds(30,70,280,30);
        loginField.setBounds(30,20,280,30);
        calendarField.setBounds(30,120,280,30);

        activeBox.setBounds(30, 230, 80, 30);
        spinner.setBounds(30, 200, 80, 30);

        confirmButton.setBounds(30,300,280,40);
        unrepeatableTaskFormButton.setBounds(30,300,200,40);
        repeatableTaskFormButton.setBounds(30,350,200,40);
        editTaskFormButton.setBounds(300,300,200,40);
        deleteTaskFormButton.setBounds(300,350,200,40);
        calendarFormButton.setBounds(570,300,200,40);

        cancelButton.setBounds(30,350,280,40);
        exitButton.setBounds(570,350,200,40);

        mainPanel.setBackground(Color.green);
        mainPanel.add(unrepeatableTaskFormButton);
        mainPanel.add(repeatableTaskFormButton);
        mainPanel.add(editTaskFormButton);
        mainPanel.add(deleteTaskFormButton);
        mainPanel.add(calendarFormButton);
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

    public void showTaskList(String taskList) {
        tasksField.setText(taskList);
    }

    public void flushFields() {
        titleField.setText("");
        descriptionField.setText("");
    }

    public void open() {
        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
    }

    public void addListener(ActionListener actionListener) {
        unrepeatableTaskFormButton.addActionListener(actionListener);
        repeatableTaskFormButton.addActionListener(actionListener);
        activeBox.addActionListener(actionListener);
        editTaskFormButton.addActionListener(actionListener);
        deleteTaskFormButton.addActionListener(actionListener);

        cancelButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        calendarFormButton.addActionListener(actionListener);
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}


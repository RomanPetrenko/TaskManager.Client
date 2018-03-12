package com.group52.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddTaskForm extends MainPanel implements Listenable {

    private JFrame taskForm;
    public JButton unrepeatableTaskButton = new JButton("Create task");
    public JButton repeatableTaskButton = new JButton("Create task");

    public AddTaskForm(String taskType) throws NullPointerException {
        taskForm = new JFrame();
        taskForm.setSize(350,450);
        taskForm.setLocationRelativeTo(null);
        taskForm.setResizable(false);

        flushFields();
        activeBox.setSelected(true);
        taskForm.add(titleField);
        taskForm.add(descriptionField);

        //new Calendar(true);
        //taskForm.add(calendarField);

        taskForm.add(activeBox);
        taskForm.add(titleLabel);
        taskForm.add(descriptionLabel);
        taskForm.add(cancelButton);
        taskForm.setLayout(null);

        if (taskType.equals("Unrepeatable")) {
            unrepeatableTaskButton.setBounds(30,300,280,40);
            taskForm.remove(repeatableTaskButton);
            taskForm.add(unrepeatableTaskButton);
        }
        else if (taskType.equals("Repeatable")) {
            repeatableTaskButton.setBounds(30,300,280,40);
            taskForm.remove(unrepeatableTaskButton);
            taskForm.add(repeatableTaskButton);
        }

    }

    public String getTitle() throws NullPointerException {
        return titleField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public String getTime() {
        return "date + time >> format to Date()";
    }

    public void open() {
        taskForm.setVisible(true);
    }
    public void close() {
        taskForm.setVisible(false);
    }

    public void addListener(ActionListener actionListener) {
        unrepeatableTaskButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);
    }
}

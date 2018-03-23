package com.group52.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteTaskForm extends MainPanel implements Listenable {

    private JFrame taskForm;
    public JButton deleteTaskButton = new JButton("Delete task");

    public DeleteTaskForm() {
        taskForm = new JFrame("Delete task");
        taskForm.setSize(350,450);
        taskForm.setLocationRelativeTo(null);
        taskForm.setResizable(false);
        taskForm.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));

        JScrollPane scrollPane = new JScrollPane(comboBox);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(30, 160, 300,100);
        taskForm.getContentPane().add(scrollPane);

        JLabel img;
        img = new JLabel(new ImageIcon("img/delete.png"));
        img.setBounds(30, 15, 300,120);
        taskForm.add(img);

        deleteTaskButton.setBounds(30,300,280,40);
        taskForm.add(deleteTaskButton);
        taskForm.add(cancelButton);
        taskForm.setLayout(null);
    }

    public void open() {
        taskForm.setVisible(true);
    }

    public void close() {
        taskForm.setVisible(false);
    }

    public void addListener(ActionListener actionListener) {
        deleteTaskButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);
    }
}

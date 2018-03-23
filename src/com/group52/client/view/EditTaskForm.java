package com.group52.client.view;

import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

public class EditTaskForm extends MainPanel implements Listenable {

    private JFrame taskForm;
    private JDatePickerImpl startDatePicker = new Calendar(false).getDatePicker();
    private JDatePickerImpl endDatePicker = new Calendar(false).getDatePicker();
    public JButton editTaskButton = new JButton("Edit task");


    public EditTaskForm() {
        taskForm = new JFrame("Edit task");
        taskForm.setSize(700,450);
        taskForm.setLocationRelativeTo(null);
        taskForm.setResizable(false);
        taskForm.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));

        startDatePicker.setBounds(30,120,140,30);
        endDatePicker.setBounds(30,170,140,30);

        activeBox.setSelected(true);
        taskForm.add(titleField);
        taskForm.add(descriptionField);

        JScrollPane areaScrollPane = new JScrollPane(comboBox);
        areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaScrollPane.setBounds(350, 150, 300,100);
        taskForm.getContentPane().add(areaScrollPane);

        taskForm.add(startDatePicker);
        taskForm.add(startHoursSpinner);
        taskForm.add(startMinutesSpinner);
        taskForm.add(startDateLabel);
        taskForm.add(startHoursLabel);
        taskForm.add(startMinutesLabel);

        taskForm.add(endDatePicker);
        taskForm.add(endHoursSpinner);
        taskForm.add(endMinutesSpinner);
        taskForm.add(endDateLabel);
        taskForm.add(endHoursLabel);
        taskForm.add(endMinutesLabel);

        taskForm.add(intervalField);
        taskForm.add(intervalLabel);

        taskForm.add(activeBox);
        taskForm.add(titleLabel);
        taskForm.add(descriptionLabel);

        JLabel img;
        img = new JLabel(new ImageIcon("img/choose.gif"));
        img.setBounds(350, 30, 300,100);
        taskForm.add(img);

        editTaskButton.setBounds(30,300,280,40);
        taskForm.add(editTaskButton);
        taskForm.add(cancelButton);
        taskForm.setLayout(null);
    }

    public String getTitle() throws NullPointerException {
        return titleField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public long getStartTime() throws ParseException {
        Date parsingDate = parseTimeToDate(startDatePicker, startHoursSpinner, startMinutesSpinner);
        return parsingDate.getTime();
}

    public long getEndTime() throws ParseException {
        Date parsingDate = parseTimeToDate(endDatePicker, endHoursSpinner, endMinutesSpinner);
        return parsingDate.getTime();
    }

    public int getInterval()  {
        return Integer.parseInt(intervalField.getText());
    }

    public void addRepeatableFields() {
        endDatePicker.setVisible(true);
        endHoursSpinner.setVisible(true);
        endMinutesSpinner.setVisible(true);
        intervalField.setVisible(true);

        endDateLabel.setVisible(true);
        endDateLabel.setVisible(true);
        endHoursLabel.setVisible(true);
        endMinutesLabel.setVisible(true);
        intervalLabel.setVisible(true);
    }

    public void removeRepeatableFields() {
        endDatePicker.setVisible(false);
        endHoursSpinner.setVisible(false);
        endMinutesSpinner.setVisible(false);
        intervalField.setVisible(false);;

        endDateLabel.setVisible(false);
        endDateLabel.setVisible(false);
        endHoursLabel.setVisible(false);
        endMinutesLabel.setVisible(false);
        intervalLabel.setVisible(false);
    }

    public void open() {
        flushFields();
        taskForm.setVisible(true);
    }

    public void close() {
        taskForm.setVisible(false);
    }

    public void addListener(ActionListener actionListener) {
        editTaskButton.addActionListener(actionListener);
        cancelButton.addActionListener(actionListener);
        comboBox.addActionListener(actionListener);
    }
}

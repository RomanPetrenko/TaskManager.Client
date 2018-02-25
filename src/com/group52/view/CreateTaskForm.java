package com.group52.view;

import java.awt.event.ActionListener;

public class CreateTaskForm implements Listenable, Closeable {

    public CreateTaskForm() { }

    public String getTitle() {
        return "title";
    }

    public String getDescription() {
        return "description";
    }

    public String getTime() {
        return new Calendar().getDate();
    }

    public String getContact() {
        return "contact";
    }

    public void close() { }

    public void addTasksListener(ActionListener actionListener) { }
}
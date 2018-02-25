package com.group52.view;

import java.awt.event.ActionListener;

public class Calendar implements Listenable, Closeable {

    private String date;

    public Calendar() { }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void close() { }

    public void addTasksListener(ActionListener actionListener) { }
}

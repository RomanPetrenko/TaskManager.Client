package com.group52.view;

import java.awt.event.ActionListener;

public class Calendar implements Listenable, Closeable {

    private String time;

    public Calendar() { }

    public String getTime() {
        return time;
    }

    public void setTime(String date) {
        this.time = date;
    }

    public void close() { }

    public void addListener(ActionListener actionListener) { }
}

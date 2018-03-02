package com.group52.view;

import java.awt.event.ActionListener;

public class EditTaskForm implements Listenable, Closeable {

    public EditTaskForm() { }

    public void showOldTitle(String title) { }

    public String getNewTitle() {
        return "title";
    }

    public void showOldTime(String time) { }

    public String getNewTime() {
        return new Calendar().getDate();
    }

    public void showOldDescription(String time) { }

    public String getNewDescription() {
        return "Description";
    }

    public void showContact(String time) { }

    public String getContact() {
        return "Contact";
    }

    public void close () { }

    public void addListener(ActionListener actionListener) { }

}

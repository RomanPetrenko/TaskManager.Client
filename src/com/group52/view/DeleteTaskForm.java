package com.group52.view;

import java.awt.event.ActionListener;

public class DeleteTaskForm implements Listenable, Closeable {

    public DeleteTaskForm() { }

    public String getSelectedTask() {
        return "task";
    }

    public void close () { }

    public void addListener(ActionListener actionListener) { }
}

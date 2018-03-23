package com.group52.client.actions;

import com.group52.client.view.NotificationForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notificator extends Thread {

    private NotificationForm notificationForm;
    private List<XMLParse.Task> taskList = new ArrayList<>();
    private XMLParse.Task taskToPostpone = new XMLParse.Task();

    @Override
    public void run() {
        while (true) {
            long curTime = System.currentTimeMillis();
            if (taskList != null) {
                for (XMLParse.Task task : taskList) {
                    if (task != null) {
                        if (task.getTime() == curTime) {
                            notificationForm.showTask("Time for doing:\n" +
                                    task.getTitle() + " ,time: " + new Date(task.getTime()) + "\n");
                            setTaskToPostpone(task);
                            notificationForm.open();
                            System.out.println(task.getTitle());
                        }
                    }
                }
            }
        }
    }

    public XMLParse.Task getTaskToPostpone() {
        return taskToPostpone;
    }

    public void setTaskToPostpone(XMLParse.Task taskToPostpone) {
        this.taskToPostpone = taskToPostpone;
    }

    public List<XMLParse.Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<XMLParse.Task> taskList) {
        this.taskList = taskList;
    }

    public NotificationForm getNotificationForm() {
        return notificationForm;
    }

    public void setNotificationForm(NotificationForm notificationForm) {
        this.notificationForm = notificationForm;
    }
}

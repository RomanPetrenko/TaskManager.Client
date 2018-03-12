package com.group52.actions;

import java.io.File;

public class XMLParse {

    public static File parseRequestToXML(String request) {
        return new File("output.xml");
    }

    public static File parseUserToXML(String request, String login, String password) {
        return new File("output.xml");
    }

    public static File  parseTaskToXML(String request, String title, String description, String time) {
        return new File("output.xml");
    }

    public static String parseFromXML(File file) {
        return "string";
    }

    public static String parseTasksFromXML(File file) {
        return "string";
    }
}

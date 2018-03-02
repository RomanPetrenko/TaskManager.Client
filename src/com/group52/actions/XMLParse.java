package com.group52.actions;

import java.io.File;

public class XMLParse {

    public static File parseUserToXML(String login, String password) {
        return new File("output.xml");
    }

    public static File  parseTaskToXML(String title, String description, String time, String contact) {
        return new File("output.xml");
    }

    public static File  parseRequestToXML(String title) {
        return new File("output.xml");
    }

    public static String parseFromXML(File file) {
        return "string";
    }

}

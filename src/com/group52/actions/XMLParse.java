package com.group52.actions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;

public class XMLParse {

    private static Client client;

    @XmlRootElement(name = "client")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Client {

        @XmlAttribute(name = "login")
        String login;
        @XmlAttribute(name = "password")
        int password;
        @XmlElement
        int id;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getPassword() {
            return password;
        }

        public void setPassword(int password) {
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        private Client(){}

        private Client(String login, int password, int id) {
            setLogin(login);
            setPassword(password);
            setId(id);
        }

    }

    @XmlRootElement (name = "socket")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Socket {

        @XmlElement
        Client client;

        @XmlElement
        String action;

        @XmlElement
        int code;

        @XmlElement
        String status;

        @XmlElement(name = "task")
        Task task;

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private Socket(){}

        private Socket(Client client, String action){
            setClient(client);
            setAction(action);
        }

    }

    @XmlRootElement (name = "task")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class Task {
        @XmlAttribute(name = "title")
        String title;

        @XmlAttribute(name = "time")
        long time;

        @XmlAttribute(name = "start")
        long start;

        @XmlAttribute(name = "end")
        long end;

        @XmlAttribute(name = "interval")
        int interval;

        @XmlAttribute(name = "active")
        boolean active;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public int getInterval() {
            return interval;
        }

        public void setInterval(int interval) {
            this.interval = interval;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        private Task(){}
        
        private Task(String title, long time, long start, long end, int interval, boolean active) {
            setTitle(title);
            setTime(time);
            setStart(start);
            setEnd(end);
            setInterval(interval);
            setActive(active);
        }
    }

    private static Marshaller createMarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XMLParse.Socket.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                "\n<!DOCTYPE socket SYSTEM  \"xml\\client.dtd\">");
        return marshaller;
    }

    private static Unmarshaller createUnmarshaller() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XMLParse.Socket.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //unmarshaller.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
        return unmarshaller;
    }

    public static void createClient(String login, int password, int id) {
        client = new Client(login,password, id);
    }

    public static File parseRequestToXML(String request) throws JAXBException {
        File file = new File("xml/" + request + ".xml");
        Socket socket = new Socket(client, request);
        createMarshaller().marshal(socket, file);
        return file;
    }

    public static File parseTaskToXML(String request, String title, String description, String time, boolean active)
            throws JAXBException {
        File file = new File("xml/" + request + ".xml");
        Socket socket = new Socket(client, request);
        socket.setTask(new Task(title, 2,2,2,2,active));
        createMarshaller().marshal(socket, file);
        return file;
    }

    public static int getCodeFromXML(File file) throws JAXBException {
        XMLParse.Socket socket = (XMLParse.Socket) createUnmarshaller().unmarshal(file);
        return socket.getCode();
    }

    public static String getUserFromXML(File file) throws JAXBException {
        XMLParse.Socket socket = (XMLParse.Socket) createUnmarshaller().unmarshal(file);
        return "login: " + socket.getClient().getLogin() + "\nPass: " + socket.getClient().getPassword();
    }

    public static String getTaskFromXML(File file) throws JAXBException {
        XMLParse.Socket socket = (XMLParse.Socket) createUnmarshaller().unmarshal(file);
        Task task = socket.getTask();
        return task.getTitle();
    }

    public static String getActionFromXML(File file) throws JAXBException {
        XMLParse.Socket socket = (XMLParse.Socket) createUnmarshaller().unmarshal(file);
        return socket.getAction();
    }

}
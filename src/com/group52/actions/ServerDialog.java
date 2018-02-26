package com.group52.actions;

import java.io.*;
import java.net.Socket;

public class ServerDialog {

    private BufferedReader in;
    private PrintWriter out;

    public ServerDialog(String serverAddress) throws IOException {
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void sendXMLRequest(String type) {
        if (type.equals("Create")) {
            //out.sendRequest
        }
    }

    public void sendXMLToServer(File xml) {
            //out.sendXMLToServer
    }

    public String getResponseFromServer() {
        return new String("OK");
        //or string = "got Task"
    }
}

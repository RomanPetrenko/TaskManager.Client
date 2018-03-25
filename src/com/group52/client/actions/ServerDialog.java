package com.group52.client.actions;

import org.apache.log4j.Logger;
import java.io.*;
import java.net.Socket;

/**
 * class ServerDialog is class for send and get xml files
 */
public class ServerDialog {

    private Logger log = Logger.getLogger(Handler.class);
    private BufferedInputStream input;
    private BufferedOutputStream output;
    private Socket socket;

    /**
     * creating server dialog constructor
     * @see ServerDialog
     * @param serverAddress is server address
     */
    public ServerDialog(String serverAddress) throws IOException {
        socket = new Socket(serverAddress, 9001);
        //socket = new Socket("127.0.0.1", 9001);
        input = new BufferedInputStream(socket.getInputStream());
        output = new BufferedOutputStream(socket.getOutputStream());
    }

    /**
     * empty constructor only for testing
     * @see ServerDialog
     */
    public ServerDialog() { //is needed to be delete after testing
        File input_file = new File("xml/socket_input.xml");
        File output_file = new File("xml/socket_output.xml");
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(input_file);
            fos = new FileOutputStream(output_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fis != null) {
            input = new BufferedInputStream(fis);
        }
        if (fos != null) {
            output = new BufferedOutputStream(fos);
        }
    }

    /**
     * method where we send XML to server
     * @param file is input file
     */
    public void sendXMLToServer(File file) {
        try {
            sendFile(output, file);
        } catch (IOException e) {
            log.error("IO Exception: ", e);
            close();
        }
    }

    /**
     * method where we get response from server
     * @return file is output file
     */
    public File getResponseFromServer() {
        File file = null;
        try {
            file = getFile(input);
        } catch (IOException e) {
            log.error("IO Exception: ", e);
            close();
        }
        return file;
    }

    /**
     * method where we transform file to stream
     * @param file is input file
     * @param output is output stream
     * @throws IOException if get input/output mistake
     */
    private void sendFile(BufferedOutputStream output, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        int length;
        while((length = bis.read()) != -1) {
            output.write(length); //sending file to socket
        }
        output.flush();
        bis.close();
    }

    /**
     * method where we transform stream to file
     * @param input is input stream
     * @throws IOException if get input/output mistake
     * @return file is output file
     */
    private File getFile(BufferedInputStream input) throws IOException {
        File file = new File("xml/answer.xml");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        int length;
        while((length = input.read()) != -1) { //getting file from socket
            bos.write(length);
        }
        bos.close();
        return file;
    }

    /**
     * method for close all streams and socket
     */
    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            log.error("IO Exception: ", e);
        }
    }

}

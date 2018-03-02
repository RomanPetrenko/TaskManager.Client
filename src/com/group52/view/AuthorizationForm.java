package com.group52.view;

import java.awt.event.ActionListener;

public class AuthorizationForm implements Listenable, Closeable {

    private String login;
    private String password;

    public AuthorizationForm() { }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void close () { }

    public void addListener(ActionListener actionListener) { }

}

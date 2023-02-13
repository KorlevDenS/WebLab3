package com.example.web3;


import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class Lab3SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        System.out.println("New session created, session id: " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}

package model;

import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, Contact> contacts;

    private DBConnection connection;

    public PhoneBook(String url) {
        connection = new DBConnection(url);
    }

    public void connect() {

        try {
            connection.connect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            connection.disconnect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connection.isConnected();
    }
}
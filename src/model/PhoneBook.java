package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, Contact> contacts;

    private DBConnection connection;

    public PhoneBook(String url) {
        connection = new DBConnection(url);

        contacts = new HashMap<String, Contact>();
    }

    public void connect() {

        try {
            connection.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connection.isConnected();
    }

    public void loadDataFromDb() {
        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM CONTACTS");

            resultSet.next();

            do {

                String name = resultSet.getString(1);
                String number = resultSet.getString(2);
                String address = resultSet.getString(3);

                Contact contact = new Contact(name, number, address, true);

                contacts.put(contact.getName(), contact);

            } while (resultSet.next());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String key : contacts.keySet()) {

            System.out.println(contacts.get(key).getName());
        }

    }

    public void saveDataInDb() {

    }

    public void checkAndConfigureDb() {

    }
}
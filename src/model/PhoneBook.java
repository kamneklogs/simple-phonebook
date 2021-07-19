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

            // checkAndConfigureDb();

            while (resultSet.next()) {

                System.out.println("Entró al while");
                String name = resultSet.getString(1);
                String number = resultSet.getString(2);
                String address = resultSet.getString(3);
                int isBestFriend = resultSet.getInt(4);

                Contact contact = new Contact(name, number, address, isBestFriend == 0);

                contacts.put(contact.getName(), contact);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (String key : contacts.keySet()) {

            System.out.println(contacts.get(key).getName());
        }

    }

    public void saveDataInDb() {

    }

    public void checkAndConfigureDb() throws SQLException {

        connection.verifyTable();

    }

    public void saveNewContact(String name, String phoneNumber, String address, int isBestFriend) throws SQLException {

        Contact newContact = new Contact(name, phoneNumber, address, isBestFriend == 0);

       

        connection.executeUpdate(new String[] { name, phoneNumber, address, isBestFriend + "" });

        contacts.put(name, newContact);

    }

    public void test() {
        ResultSet resultSet;
        try {
            resultSet = connection.executeQuery("SELECT * FROM CONTACTS");

            resultSet.next();

            // checkAndConfigureDb();

            while (resultSet.next()) {

                System.out.println("Entró al while");
                String name = resultSet.getString(1);
                String number = resultSet.getString(2);
                String address = resultSet.getString(3);
                int isBestFriend = resultSet.getInt(4);

                Contact contact = new Contact(name, number, address, isBestFriend == 0);

                System.out.println(contact.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhoneBook {

    private DBConnection connection;

    public PhoneBook(String url) {
        connection = new DBConnection(url);

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

    public void checkAndConfigureDb() throws SQLException {

        connection.verifyTable();

    }

    public void saveNewContact(String name, String phoneNumber, String address, int isBestFriend) throws SQLException {

        connection.executeUpdate(new String[] { name, phoneNumber, address, isBestFriend + "" });

    }

    public void test() {
        ResultSet resultSet;
        try {
            resultSet = connection.executeQuery("SELECT * FROM CONTACTS");

            resultSet.next();

            while (resultSet.next()) {

                System.out.println("Entró al while");
                String name = resultSet.getString(1);
                String number = resultSet.getString(2);
                String address = resultSet.getString(3);
                int isBestFriend = resultSet.getInt(4);

                Contact contact = new Contact(name, number, address, isBestFriend == 1);

                System.out.println(contact.toString());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean contactExists(String name) throws SQLException {

        return connection.recordExists(name);

    }

    public ArrayList<String> allContactNames() throws SQLException {
        ArrayList<String> allContactNames = connection.getAllKeyRecords();

        return allContactNames;

    }

}
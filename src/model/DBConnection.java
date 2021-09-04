package model;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private Boolean isConnected;

    private String url;

    private Connection connection;

    private static final String[] queryExists = new String[] {
            "select case when exists (select 1 from CONTACTS where name = '",
            "') then 'Y' else 'N' end as rec_exists from dual" };

    public DBConnection(String url) {
        this.url = url;

        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean connect() throws Exception {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

            String[] infoSesion = url.split(",");

            connection = DriverManager.getConnection(infoSesion[0], infoSesion[1], infoSesion[2]);

            isConnected = true;

        } catch (ClassNotFoundException e) {
            throw new Exception("Internat error");
        } catch (SQLException e) {

            e.printStackTrace();
            throw new Exception("Error with the connection");
        }

        return isConnected;
    }

    public void disconnect() throws Exception {

        try {

            if (connection != null)
                connection.close();
            isConnected = false;
        } catch (SQLException e) {
            throw new Exception("Error with the connection");
        }

    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(query);
    }

    public void executeUpdate(String[] strings) throws SQLException {

        String query = "INSERT INTO CONTACTS VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, strings[0]);
        statement.setString(2, strings[1]);
        statement.setString(3, strings[2]);
        statement.setInt(4, Integer.parseInt(strings[3]));

        statement.executeUpdate();

    }

    public void verifyTable() throws SQLException {
        // desc mytable

        ResultSet resultSet = executeQuery("desc CONTACTS");

        System.out.println(resultSet.toString());

    }

    public boolean recordExists(String name) throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(queryExists[0] + name + queryExists[1]);

        resultSet.next();

        return resultSet.getString(1).equals("Y");
    }

    public ArrayList<String> getAllKeyRecords() throws SQLException {

        ArrayList<String> keys = new ArrayList<String>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT NAME FROM CONTACTS");

        while (resultSet.next()) {

            keys.add(resultSet.getString(1));
        }

        return keys;
    }
}
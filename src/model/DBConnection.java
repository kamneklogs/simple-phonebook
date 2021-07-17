package model;

import java.sql.*;

public class DBConnection {

    private boolean isConnected;

    private String url;

    private Connection connection;

    public DBConnection(String url) {
        this.url = url;
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

            connection = DriverManager.getConnection(url);
            isConnected = connection.isValid(10);


        } catch (ClassNotFoundException e) {
            throw new Exception("Internat error");
        } catch (SQLException e) {
            throw new Exception("Error with the connection");
        }

        return isConnected;
    }

    public void disconnect() throws Exception {

        try {
            connection.close();
        } catch (SQLException e) {
            throw new Exception("Error with the connection");
        }

    }
}
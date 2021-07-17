package model;

import java.sql.*;

public class DBConnection {

    private Boolean isConnected;

    private String url;

    private Connection connection;

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

            if(connection != null)
            connection.close();
            isConnected = false;
        } catch (SQLException e) {
            throw new Exception("Error with the connection");
        }

    }
}
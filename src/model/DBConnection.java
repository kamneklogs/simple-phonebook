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

        // INSERT INTO DEPARTMENT
        // VALUES ('DEP004', 'D. Administrativo', null);

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
}
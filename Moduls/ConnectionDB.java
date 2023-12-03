package org.example.Moduls;
import java.sql.*;


public class ConnectionDB {
    private final String USER_NAME;
    private final String PASSWORD;
    private final String URL;
    public Statement statement;
    public Connection connection;

    public ConnectionDB() {
        this.USER_NAME = "root";
        this.PASSWORD = "Zaza_2023!";
        this.URL = "jdbc:mysql://localhost:3306/humanfriends";
    }

    public Connection connectionDB(){
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return connection;
        } catch (SQLException trowables) {
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Statement connectionDBstmt(Connection conn){
        try {
            statement = conn.createStatement();
            // System.out.println("Соединение установлено!");
            return statement;
        } catch (SQLException trowables1) {
            trowables1.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void connectionClose(Connection conn, Statement stmt, ResultSet resSet) {
        try{
            conn.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }

        try{
            stmt.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }

        try{
            resSet.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException();
        }
    }
}

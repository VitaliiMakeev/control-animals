package org.example.Moduls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Animals {
    public Animals() {
    }

    public void getAllAnimals() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery("select * from all_animals;");
        while (resaltSet.next()) {
            System.out.println(resaltSet.getString(1) + " " + resaltSet.getString(2) + " " + resaltSet.getString(3) + " " + resaltSet.getString(4) + " " + resaltSet.getString(5));
        }
        connectionDb.connectionClose(connect, statement, resaltSet);
    }

    public String countAnimals() throws ClassNotFoundException, SQLException {
        String result = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery("select count(*) from all_animals;");
        resaltSet.next();
        result = resaltSet.getString(1);
        connectionDb.connectionClose(connect, statement, resaltSet);
        return "Общее кол-во животных: " + result;
    }
}

package org.example;


import org.example.Controller.Controller;
import org.example.Moduls.Animals;
import org.example.Moduls.Cat;
import org.example.Moduls.Dog;
import org.example.Moduls.Pets;
import org.example.View.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        View view = new View();
        view.start();
    }

}
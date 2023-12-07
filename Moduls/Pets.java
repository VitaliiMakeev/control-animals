package org.example.Moduls;

import org.example.Controller.IControllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pets extends Animals implements IControllers {
    public String type;
    public String name;
    public String birthDay;
    public String commands;
    public List<String> samCommand;
    public Pets() {
    }

    public Pets(String name, String birthDay, String commands) {
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
    }

    /**
     * Выводит в консоль всех питомцев, которые живет дома.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void getAllPets() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery("select * from all_animals where type_anim = 'cat' or type_anim = 'dog' or type_anim = 'hamster';");
        while (resaltSet.next()) {
            System.out.println(resaltSet.getString(1) + " " + resaltSet.getString(2) + " " + resaltSet.getString(3) + " " + resaltSet.getString(4) + " " + resaltSet.getString(5));
        }
        connectionDb.connectionClose(connect, statement, resaltSet);
    }

    /**
     * Выводит в консоль всех питомцев данного типа, которые живут дома. Метод нужно вызыват у потомков.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void getAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery(String.format("select * from all_animals where type_anim = '%s';", this.type));
        while (resaltSet.next()) {
            System.out.println(resaltSet.getString(1) + " " + resaltSet.getString(2) + " " + resaltSet.getString(3) + " " + resaltSet.getString(4) + " " + resaltSet.getString(5));
        }
        connectionDb.connectionClose(connect, statement, resaltSet);
    }

    /**
     * Добавляем нового домашнего питомца.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void setNew() throws SQLException, ClassNotFoundException {
        if (this.checkDuble() != 0) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionDB connectionDb = new ConnectionDB();
            Connection connect = connectionDb.connectionDB();
            Statement statement = connectionDb.connectionDBstmt(connect);
            statement.executeUpdate(String.format("insert into all_animals (name, type_anim, birth_day, commands) values ('%s', '%s', '%s', '%s');", this.name, this.type, this.birthDay, this.commands));
            this.checkListCommand(this.commands);
            connect.close();
            statement.close();
            System.out.println("Зверушка " + this.name + " добавлена в базу!");
        } else {
            System.out.println("Такая зверюга уже есть!");
        }

    }

    /**
     * Вспомогательный метод выведет в консоль те команды, которые не знает данное животное. Метод вызывается у потомка.
     * @param com строка с командами, через зхапятую
     */
    private void checkListCommand(String com){
        List<String> commSet = Arrays.asList(com.split(","));
        List<String> tmp = new ArrayList<>(this.samCommand);
        for (int i = 0; i < this.samCommand.size(); i++) {
            for (int j = 0; j < commSet.size(); j++) {
                if (this.samCommand.get(i).replaceAll("\\s+", "").toLowerCase().equals(commSet.get(j).replaceAll("\\s+", "").toLowerCase())) {
                    tmp.remove(this.samCommand.get(i));
                    break;
                }
            }
        }
        if (tmp.size() != 0) {
            System.out.println("Зверюга может быть обучена еще вот этим командам:");
            for (int i = 0; i < tmp.size(); i++) {
                System.out.println(tmp.get(i));
            }
        } else {
            System.out.println("Какая умная зверюга, все знает!");
        }
    }

    /**
     * Вспомогательный метод предотвращает внесение одного и того же животного в базу.
     * @return 1 если нет дублей, 0 если есть.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private int checkDuble() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery(String.format("select * from all_animals where name = '%s' and birth_day = '%s';", this.name, this.birthDay));
        if (resaltSet.isBeforeFirst()) {
            connectionDb.connectionClose(connect, statement, resaltSet);
            return 0;
        } else {
            connectionDb.connectionClose(connect, statement, resaltSet);
            return 1;
        }
    }

    /**
     * Вспомогательный метод для получения команд, которые знает животное.
     * @param number id животного
     * @return список команд (строки)
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private List<String> getCommand(int number) throws ClassNotFoundException, SQLException {
        List<String> comm = new ArrayList<>();
        List<String> tmp;
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery(String.format("select commands from all_animals where id = '%d';", number));
        resaltSet.next();
        tmp = List.of(resaltSet.getString(1).split(","));
        for (int i = 0; i < tmp.size(); i++) {
            comm.add(tmp.get(i).replaceAll("\\s+", ""));
        }
        connectionDb.connectionClose(connect, statement, resaltSet);
        return comm;
    }

    /**
     * Вспомогательный метод проверяет на дубликаты новые команды.
     * @param newCommand новая команда
     * @param idDog id животного
     * @return 0 если такую команду уже знает животное, 1 если нет.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private int checkCommand(String newCommand, int idDog) throws SQLException, ClassNotFoundException {
        List<String> comm = new ArrayList<>(this.getCommand(idDog));
        int tmp = 0;
        for (int i = 0; i < comm.size(); i++) {
            if (comm.get(i).toLowerCase().equals(newCommand.toLowerCase().replaceAll("\\s+", ""))) {
                tmp = 1;
                break;
            }
        }
        if (tmp == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Метод добавления новой команды животному
     * @param newCommand новая команда
     * @param id id животного
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public void setNewCommand(String newCommand, int id) throws SQLException, ClassNotFoundException {
        if (this.checkCommand(newCommand, id) != 0) {
            String tmpCommands = "";
            List<String> tmp = new ArrayList<>(this.getCommand(id));
            for (int i = 0; i < tmp.size(); i++) {
                tmpCommands = tmpCommands + " " + tmp.get(i) + ",";
            }
            String newCommandsDog = (tmpCommands + " " + newCommand).trim();
            Class.forName("com.mysql.cj.jdbc.Driver");
            ConnectionDB connectionDb = new ConnectionDB();
            Connection connect = connectionDb.connectionDB();
            Statement statement = connectionDb.connectionDBstmt(connect);
            statement.executeUpdate(String.format("update all_animals set commands = '%s' where id = %d;", newCommandsDog, id));
            connect.close();
            statement.close();
            System.out.println("Ура! Новые фичи у зверюги!");
        } else {
            System.out.println("Зверюга это уже умеет!");
        }
    }

    /**
     * Метод проверяет, есть ли такой id в базе
     * @param id id животного
     * @return true если такое животное есть в базе и false - если нет.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean getAnimId(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery(String.format("select * from all_animals where id = %d and (type_anim = 'cat' or type_anim = 'dog' or type_anim = 'hamster');", id));
        if (resaltSet.isBeforeFirst()) {
            connectionDb.connectionClose(connect, statement, resaltSet);
            return true;
        } else {
            connectionDb.connectionClose(connect, statement, resaltSet);
            return false;
        }
    }

    /**
     * Выводит в консоль всех домашних питомцев, начиная с самого младшего.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void getAllOrderBy() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        ConnectionDB connectionDb = new ConnectionDB();
        Connection connect = connectionDb.connectionDB();
        Statement statement = connectionDb.connectionDBstmt(connect);
        ResultSet resaltSet = statement.executeQuery("select * from all_animals where type_anim = 'cat' or type_anim = 'dog' or type_anim = 'hamster' order by birth_day;");
        while (resaltSet.next()) {
            System.out.println(resaltSet.getString(1) + " " + resaltSet.getString(2) + " " + resaltSet.getString(3) + " " + resaltSet.getString(4) + " " + resaltSet.getString(5));
        }
        connectionDb.connectionClose(connect, statement, resaltSet);
    }
}

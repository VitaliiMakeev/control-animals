package org.example.View;

import org.example.Controller.Controller;
import org.example.Moduls.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    public void start() throws SQLException, ClassNotFoundException {
        Animals animals = new Animals();
        Pets pets = new Pets();
        PackAnimals packAnimals = new PackAnimals();
        boolean flag = true;
        while (flag) {
            System.out.println("------Система учета животных------");
            System.out.println("------" + animals.countAnimals() + "------");
            int n = titleStart();
            System.out.println(n); // для проверки
            switch (n){
                case (0):
                    System.out.println("Пока!");
                    flag = false;
                    break;
                case (1):
                    animals.getAllAnimals();
                    break;
                case (2):       // дом животные
                    pets.getAllPets();
                    int n2 = this.petsAndPackStart();
                    switch (n2) {
                        case (0):
                            System.out.println("Назад");
                            break;
                        case (1):
                            int n3 = this.cheisAddPets();
                            switch (n3){
                                case (0):
                                    System.out.println("Назад");
                                    break;
                                case (1):
                                    List<String> newDog = this.addNewPetsAndPack();
                                    if (newDog.size() != 0) {
                                        Dog dog = new Dog(newDog.get(0), newDog.get(1), newDog.get(2));
                                        dog.setNew();
                                        break;
                                    } else {
                                        System.out.println("Назад");
                                        break;
                                    }
                                case (2):
                                    List<String> newCat = this.addNewPetsAndPack();
                                    if (newCat.size() != 0) {
                                        Cat cat = new Cat(newCat.get(0), newCat.get(1), newCat.get(2));
                                        cat.setNew();
                                        break;
                                    }
                                case (3):
                                    List<String> newHam = this.addNewPetsAndPack();
                                    if (newHam.size() != 0) {
                                        Hamster hamster = new Hamster(newHam.get(0), newHam.get(1), newHam.get(2));
                                        hamster.setNew();
                                        break;
                                    }
                            }
                            break;
                        case (2):
                            boolean flag2 = true;
                            while (flag2) {
                                int idPet = this.getIdPets();
                                if (idPet > 0) {
                                    String newCommand = this.addNewComm();
                                    if (newCommand.length() != 0){
                                        pets.setNewCommand(newCommand, idPet);
                                        flag2 = false;
                                    } else {
                                        System.out.println("Новая команда не добавлена.");
                                    }
                                } else {
                                    System.out.println("Назад.");
                                    flag2 = false;
                                }
                            }
                            break;
                        case (3):
                            pets.getAllOrderBy();
                            break;
                    }
                    break;
                case (3):
                    packAnimals.getAllPack();
                    int n4 = this.petsAndPackStart();
                    switch (n4) {
                        case (0):
                            System.out.println("Назад");
                            break;
                        case (1):
                            int n5 = this.cheisAddPack();
                            switch (n5){
                                case (0):
                                    System.out.println("Назад");
                                    break;
                                case (1):
                                    List<String> newHorse = this.addNewPetsAndPack();
                                    if (newHorse.size() != 0) {
                                        Horse horse = new Horse(newHorse.get(0), newHorse.get(1), newHorse.get(2));
                                        horse.setNew();
                                        break;
                                    } else {
                                        System.out.println("Назад");
                                        break;
                                    }
                                case (2):
                                    List<String> newCamel = this.addNewPetsAndPack();
                                    if (newCamel.size() != 0) {
                                        Camel camel = new Camel(newCamel.get(0), newCamel.get(1), newCamel.get(2));
                                        camel.setNew();
                                        break;
                                    }
                                case (3):
                                    List<String> newDankey = this.addNewPetsAndPack();
                                    if (newDankey.size() != 0) {
                                        Donkey donkey = new Donkey(newDankey.get(0), newDankey.get(1), newDankey.get(2));
                                        donkey.setNew();
                                        break;
                                    }
                            }
                            break;
                        case (2):
                            boolean flag2 = true;
                            while (flag2) {
                                int idPet = this.getIdPack();
                                if (idPet > 0) {
                                    String newCommand = this.addNewComm();
                                    if (newCommand.length() != 0){
                                        pets.setNewCommand(newCommand, idPet);
                                        flag2 = false;
                                    } else {
                                        System.out.println("Новая команда не добавлена.");
                                    }
                                } else {
                                    System.out.println("Назад.");
                                    flag2 = false;
                                }
                            }
                            break;
                        case (3):
                            packAnimals.getAllOrderBy();
                            break;
                    }
                    break;
            }
        }

    }

    public int titleStart() {
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---Введите нужную цифру---");
            System.out.println("1 -> Посмотреть всех животных в базе.");
            System.out.println("2 -> Посмотреть только домашних животных.");
            System.out.println("3 -> Посмотреть только домашний скот.");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
            if (result > 0 && result < 4) {
                return result;
            } else if (result < 0 || result > 3) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
        }
        return result;
    }

    private int cheisAddPets() {
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---Введите нужную цифру---");
            System.out.println("---Кого нужно добавить?---");
            System.out.println("1 ---> Собаку.");
            System.out.println("2 ---> Кота (кошку).");
            System.out.println("3 ---> Хомяка.");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
            if (result > 0 && result < 4) {
                return result;
            } else if (result < 0 || result > 3) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
        }
        return result;
    }

    private int cheisAddPack() {
        int result = -1;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---Введите нужную цифру---");
            System.out.println("---Кого нужно добавить?---");
            System.out.println("1 ---> Лошадь (конь).");
            System.out.println("2 ---> Верблюда.");
            System.out.println("3 ---> Осела.");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
            if (result > 0 && result < 4) {
                return result;
            } else if (result < 0 || result > 3) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
        }
        return result;
    }

    private int petsAndPackStart() {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---Введите нужную цифру---");
            System.out.println("1 --> Добавить новую зверушку.");
            System.out.println("2 --> Обучить новой команде.");
            System.out.println("3 --> Посмотреть всех начиная с самого молодого");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
            if (result > 0 && result < 4) {
                return result;
            } else if (result < 0 || result > 3) {
                System.out.println("Необходимо ввести целое число!(1 - 3)");
            }
        }
        return result;
    }

    public List<String> addNewPetsAndPack() {
        List<String> result = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            String name = addNewName();
            if (name.length() != 0){
                String birthDay = addNewDate();
                if (birthDay.length() != 0) {
                    String commands = addNewComm();
                    if (commands.length() != 0) {
                        result.add(name);
                        result.add(birthDay);
                        result.add(commands);
                        flag = false;
                    } else {
                        break;
                    }

                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return result;
    }

    private String addNewName(){
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String result = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("----> Введите имя новой зверушки: ");
            String tmp = scanner.nextLine();
            String name = tmp.trim();
            String newName = controller.checkName(name);
            if (name.charAt(0) == '0'){
                break;
            }
            System.out.println(newName);
            if (name.trim().length() == newName.length()){
                result = newName;
                flag = false;
            }
        }
        return result;
    }

    private String addNewDate(){
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String result = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("----> Введите дату рождения (в формате: YYYY-MM-DD): ");
            String tmp = scanner.nextLine();
            String date = tmp.trim();
            String newDate = controller.checkBirthDay(date);
            if (date.charAt(0) == '0'){
                break;
            }
            System.out.println(newDate);
            if (date.trim().length() == newDate.length()){
                result = newDate;
                flag = false;
            }
        }
        return result;
    }

    private String addNewComm(){
        List<String> newCommList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        String result = "";
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("----> Введите команду, которую знает животное: ");
            String tmp = scanner.nextLine();
            String comm = tmp.trim();
            String newComm = controller.checkCommand(comm);
            if (comm.charAt(0) == '0'){
                break;
            }
            System.out.println(newComm);
            if (comm.trim().length() == newComm.length()){
                newCommList.add(newComm);
            }
        }
        if (newCommList.size() != 0) {
            result = controller.checkDoubleComm(newCommList);
        } else return "";
        return result;
    }

    private int getIdPets() throws SQLException, ClassNotFoundException {
        Pets pets = new Pets();
        pets.getAllPets();
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---> Введите номер зверушки (слева первый столбик): ");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!");
            }
            if (!pets.getAnimId(result)) {
                return 0;
            } else {
                return result;
            }
        }
        return result;
    }

    private int getIdPack() throws SQLException, ClassNotFoundException {
        PackAnimals packAnimals = new PackAnimals();
        packAnimals.getAllPack();
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Для выходы введите 0 (ноль).");
            System.out.println("---> Введите номер зверушки (слева первый столбик): ");
            String tmp = scanner.nextLine();
            String cheis = tmp.trim();
            if (cheis.length() == 0) {
                System.out.println("Необходимо ввести целое число!");
                continue;
            }
            if (cheis.trim().charAt(0) == '0') {
                result = 0;
                break;
            }
            try {
                int num1 = Integer.parseInt(cheis);
                result = num1;
            } catch (Exception e) {
                System.out.println("Необходимо ввести целое число!");
            }
            if (!packAnimals.getAnimId(result)) {
                return 0;
            } else {
                return result;
            }
        }
        return result;
    }

}


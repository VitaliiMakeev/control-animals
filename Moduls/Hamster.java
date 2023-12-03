package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Hamster extends Pets {
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Spin", "Roll", "Hide");

    public Hamster() {
        super.type = "hamster";
        super.samCommand = this.samCommand;
    }

    public Hamster(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "hamster";
        super.samCommand = this.samCommand;
    }
}

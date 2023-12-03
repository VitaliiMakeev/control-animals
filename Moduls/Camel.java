package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Camel extends PackAnimals {
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Walk", "Sit", "Run", "Carry Load");

    public Camel() {
        super.type = "camel";
        super.samCommand = this.samCommand;
    }

    public Camel(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "camel";
        super.samCommand = this.samCommand;
    }
}

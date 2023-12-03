package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Donkey extends PackAnimals {
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Walk", "Carry Load", "Bray", "Kick");

    public Donkey() {
        super.type = "donkey";
        super.samCommand = this.samCommand;
    }

    public Donkey(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "donkey";
        super.samCommand = this.samCommand;
    }
}

package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Dog extends Pets {
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Stay", "Fetch", "Paw", "Bark", "Roll", "Sit");

    public Dog() {
        super.type = "dog";
        super.samCommand = this.samCommand;
    }

    public Dog(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "dog";
        super.samCommand = this.samCommand;
    }

}

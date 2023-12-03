package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Cat extends Pets {
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Sit", "Pounce", "Scratch", "Meow", "Jump");

    public Cat() {
        super.type = "cat";
        super.samCommand = this.samCommand;
    }

    public Cat(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "cat";
        super.samCommand = this.samCommand;
    }

}

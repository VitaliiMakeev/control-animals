package org.example.Moduls;

import java.util.Arrays;
import java.util.List;

public class Horse extends PackAnimals{
    public String name;
    public String birthDay;
    public String commands;
    private List<String> samCommand = Arrays.asList("Trot", "Canter", "Jump", "Gallop");

    public Horse() {
        super.type = "horse";
        super.samCommand = this.samCommand;
    }

    public Horse(String name, String birthDay, String commands) {
        super(name, birthDay, commands);
        this.name = name;
        this.birthDay = birthDay;
        this.commands = commands;
        super.type = "horse";
        super.samCommand = this.samCommand;
    }
}

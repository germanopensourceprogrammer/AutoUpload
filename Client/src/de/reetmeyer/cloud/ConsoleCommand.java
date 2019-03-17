package de.reetmeyer.cloud;

public class ConsoleCommand {

    public String name = "NoName";
    public ConsoleUserInterface cui;
    public ConsoleCommand (ConsoleUserInterface cui) {
        this.cui = cui;
    }

    public ConsoleCommand() {
    }

    public void OnCommand(String args){

    }
    public void OnInput(String input){

    }

    public void SetName(String name) {
        this.name = name;
    }

}

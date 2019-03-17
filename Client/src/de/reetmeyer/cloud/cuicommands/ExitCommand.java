package de.reetmeyer.cloud.cuicommands;

import de.reetmeyer.cloud.ConsoleCommand;
import de.reetmeyer.cloud.ConsoleUserInterface;

public class ExitCommand extends ConsoleCommand {


    public ExitCommand(ConsoleUserInterface cui) {
        SetName("exit");
        this.cui = cui;
    }

    @Override
    public void OnCommand(String args) {
        cui.print("Exiting", 2);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}

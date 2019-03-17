package de.reetmeyer.cloud.cuicommands;

import de.reetmeyer.cloud.ConsoleCommand;
import de.reetmeyer.cloud.ConsoleUserInterface;

public class ConnectConfigCommand extends ConsoleCommand {

    int i = 0;

    int newport = -1;
    String newip = "ERROR";
    String curip = "localhost";
    int curport = 33445;

    public ConnectConfigCommand(ConsoleUserInterface cui) {
        SetName("ccc");
        this.cui = cui;
    }

    @Override
    public void OnCommand(String args) {
        if (args != null)
            if (args.equalsIgnoreCase("s") || args.equalsIgnoreCase("set")) {
                cui.transferInput(this);
                System.out.println("IP: ");
                return;
            }
        cui.print("Connection data set to: " + curip + ":" + curport, 0);
    }

    @Override
    public void OnInput(String input) {
        if (i == 0) {
            newip = input;
            System.out.println("Port:");
            i++;
        } else {
            newport = Integer.parseInt(input);
            cui.transferInput(null);
            updateData();
            cui.print("Connection data set to: " + newip + ":" + newport, 0);
        }
    }

    private void updateData() {
        cui.aucip= newip;
        curip = newip;
        cui.aucport = newport;
        curport = newport;
    }
}

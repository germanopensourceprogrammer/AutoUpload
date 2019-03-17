package de.reetmeyer.cloud;

import de.reetmeyer.cloud.cuicommands.ConnectConfigCommand;
import de.reetmeyer.cloud.cuicommands.ExitCommand;

import java.util.Scanner;

public class ConsoleUserInterface {

    String[] lines = new String[50];
    String inputmarker = ">>>";

    ConsoleCommand[] consoleCommands;

    ConsoleCommand transfered = null;

    String[] statusprefixs = new String[]{ConsoleColors.WHITE+"[INFO]", ConsoleColors.BLUE+"[DEBUG]", ConsoleColors.YELLOW+"[WARN]", ConsoleColors.RED+"[ERROR]"};

    String lastline = null;

    public AutoUploadClient autoUploadClient;
    public int aucport;
    public String aucip;

    public ConsoleUserInterface() {

        for (int i = 0; i < lines.length; i++) {
            lines[i] = "A" + i;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner s = new Scanner(System.in);
                while (true) readConsole(s.nextLine());
            }
        });
        t.start();
        
        consoleCommands = new ConsoleCommand[]{new ExitCommand(this), new ConnectConfigCommand(this)};
        for (ConsoleCommand cc :
                consoleCommands) {
            print(cc.name, 0);
        }
        lines[lines.length - 1] = inputmarker;
    }

    private void readConsole(String input) {
        if (transfered != null) {
            transfered.OnInput(input);
            return;
        }
        lastline = ">>>" + input;
        String[] split = input.split(" ", 2);
        boolean b = false;
        for (ConsoleCommand cc : consoleCommands) {
            if (cc.name.equalsIgnoreCase(split[0])) {
                b = true;
                if (split.length != 2) {
                    cc.OnCommand(null);
                } else {
                    cc.OnCommand(split[1]);
                }
            }
        }
        if (!b) print("Command Not found " + split[0] + input, 3);

    }

    public void transferInput(ConsoleCommand cc) {
        transfered = cc;
    }

    public void print(String message, int status) {  // i; 0 = [INFO]; 1 = [DEBUG]; 2 = [WARN]; 3 = [ERROR]
        int j = 1;
        if (lastline != null) {
            j = 2;
        }

        for (int i = 2; i < lines.length; i++) {
            lines[i - j] = lines[i];
        }
        if (lastline != null) {
            lines[lines.length - 3] = lastline;
        }
        lines[lines.length - 2] = statusprefixs[status] + " " + message + ConsoleColors.RESET;

        for (int i = 0; i < lines.length-1; i++) {
            System.out.println(lines[i]);
        }
        System.out.print(lines[lines.length-1]);
    }

    public void print(String message) {
        print(message, 0);
    }


    static public void main(String[] args) {
        System.out.println("Starting CUI");
        ConsoleUserInterface cui = new ConsoleUserInterface();
        cui.print("Starting", 0);
    }

}
class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}
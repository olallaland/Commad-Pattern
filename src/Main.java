import commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String args[]) throws Exception {
        Model model = new Model();
        CommandExecutor commandExecutor = new CommandExecutor(model);
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String input = in.nextLine();
            //  ‰»Î exit ÕÀ≥ˆ≥Ã–Ú
            if(input.equals("exit")) {
                break;
            }
            commandExecutor.setCommand(input);
            try {
                commandExecutor.execute();
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

import commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    HashMap<String, List<Command>> commands = new HashMap<>(

    );

    public static void main(String args[]) {
        Model model = new Model();
        CommandExecutor commandExecutor = new CommandExecutor();
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String input = in.nextLine();
            switch (input.charAt(0)) {
                case 's':
                    commandExecutor.execute(new ShowCommand(model, input));
                    break;
                case 'A':
                    commandExecutor.execute(new AddLastCommand(model, input));
                    break;
                case 'a':
                    commandExecutor.execute(new AddFirstCommand(model, input));
                    break;
                case 'D':
                    commandExecutor.execute(new DeleteLastCommand(model, input));
                    break;
                case 'd':
                    commandExecutor.execute(new DeleteFirstCommand(model, input));
                    break;
                case 'l':
                    commandExecutor.execute(new ListCommands(model, input));
                    break;
                case 'u':
                    commandExecutor.execute(new UndoCommand(model));
                    break;
                case 'r':
                    commandExecutor.execute(new RedoCommand(model));
                    break;
                case 'm':
                    commandExecutor.execute(new DefineCommand());
                    break;
                default:
                    System.out.println(" -- invalid command -- ");
            }
        }
    }

}

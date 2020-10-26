import commands.*;
import javafx.util.Pair;
import java.util.*;

public class CommandExecutor {
    private final Model model;
    private String command;
    private final HashMap<String, Command> viewCommands = new HashMap<>();
    private final HashMap<String, Command> modifyCommand = new HashMap<>();
    private final HashMap<String, LinkedList<Command>> macroCommands = new HashMap<>();
    LinkedList<Pair<String, Command>> commandsStack = new LinkedList<>();
    LinkedList<Pair<String, Command>> redoStack = new LinkedList<>();

    public CommandExecutor(Model model) {
        this.model = model;
        viewCommands.put("s", new ShowCommand(model));
        viewCommands.put("l", new ListCommand(model));
        modifyCommand.put("A", new AddLastCommand(model));
        modifyCommand.put("a", new AddFirstCommand(model));
        modifyCommand.put("D", new DeleteLastCommand(model));
        modifyCommand.put("d", new DeleteFirstCommand(model));
    }

    public void setCommand(String command) {
        this.command = command.trim();
    }

    /**
     * 添加自定义的宏命令
     * @param count
     * @param name
     */
    public void addMacroCommand(int count, String name) {
        LinkedList<Command> commandList = new LinkedList<>();
        for (int i = 0; i < Math.min(count, commandsStack.size()); i++) {
            commandList.add(commandsStack.get(i).getValue());
        }
        macroCommands.put(name, commandList);
    }

    /**
     * 分析命令所属的类别并执行相应操作
     * @throws Exception
     */
    public void execute() throws Exception {
        if(this.command.equals("")) {
            throw new RuntimeException(" -- invalid command -- ");
        }
        String[] params = this.command.split(" ");
        String cmdType = params[0];

        // 用户输入的命令属于修改类命令
        if(modifyCommand.containsKey(cmdType)) {
            Command curCommand = modifyCommand.get(cmdType);
            curCommand.setCommand(this.command);
            // 执行命令
            try {
                curCommand.execute();
            } catch (Exception e) {
                throw new RuntimeException(" -- invalid command -- ");
            }
            // 命令入栈
            commandsStack.push(new Pair<String, Command>(this.command, curCommand));
            model.show();

        // 用户输入的命令属于查看类命令
        } else if (viewCommands.containsKey(cmdType)) {
            Command curCommand = viewCommands.get(cmdType);
            curCommand.setCommand(this.command);
            if(cmdType.equals("l")) {
                list(params);
            } else {
                // 执行命令
                try {
                    curCommand.execute();
                } catch (Exception e) {
                    throw new RuntimeException(" -- invalid command -- ");
                }
            }
        } else if (cmdType.equals("m")) {
            addMacroCommand(Integer.parseInt(params[1]), params[2]);
        } else if (this.command.startsWith("$")) {
            if(macroCommands.containsKey(this.command.substring(1))) {
                List<Command> commandList = macroCommands.get(this.command.substring(1));
                for (int i = commandList.size() - 1; i >= 0; i--) {
                    commandList.get(i).execute();
                }
            } else {
                throw new RuntimeException(" -- invalid command -- ");
            }
            commandsStack.push(new Pair<String, Command>(this.command.substring(1), null));
            model.show();
        } else if (cmdType.equals("u")) {
            undo(new UndoCommand(model));
            model.show();
        } else if (cmdType.equals("r")) {
            redo(new RedoCommand(model));
            model.show();
        } else {
            throw new RuntimeException(" -- invalid command -- ");
        }
    }

    /**
     * 高层的 undo
     * @param curCommand
     */
    void undo(Command curCommand) {
        Pair<String, Command> lastCommand = null;
        try {
            lastCommand = commandsStack.pop();
        } catch (Exception e) {
            throw new RuntimeException(" -- No more commands can be undone -- ");
        }
        // 保存 undo 的命令，便于 redo 时恢复
        redoStack.push(lastCommand);
        String cmdType = lastCommand.getKey().split(" ")[0];
        // 如果是普通命令，undo 一次即可；如果是自定义的宏命令，则对命令集中的命令依次 undo
        if(this.modifyCommand.containsKey(cmdType)) {
            curCommand.execute();
        } else if(this.macroCommands.containsKey(cmdType)) {
            int size = this.macroCommands.get(cmdType).size();
            for (int i = 0; i < size; i++) {
                curCommand.execute();
            }
        }
    }

    /**
     * 高层的 redo
     * @param curCommand
     */
    void redo(Command curCommand) {
        Pair<String, Command> recoverCommand = null;
        try {
            recoverCommand = redoStack.pop();
        } catch (Exception e) {
            throw new RuntimeException(" -- No more commands can be recovered -- ");
        }
        String cmdType = recoverCommand.getKey().split(" ")[0];
        // 恢复命令，即将命令入栈
        commandsStack.push(recoverCommand);
        if(this.modifyCommand.containsKey(cmdType)) {
            curCommand.execute();
        } else if(this.macroCommands.containsKey(cmdType)) {
            int size = this.macroCommands.get(cmdType).size();
            for (int i = 0; i < size; i++) {
                curCommand.execute();
            }
        }
    }

    /**
     * 列出最近执行命令
     * @param params
     */
    void list(String[] params) {
        int count = Integer.parseInt(params[1]);
        int size = commandsStack.size();
        for(int i = 1; i <= Math.min(count, size); i++) {
            System.out.println(i + " " + commandsStack.get(i - 1).getKey());
        }
    }
}

package commands;

public class AddLastCommand implements Command {
    private Model model;
    private String command;

    public AddLastCommand(Model model) {
        this.model = model;
    }

    public AddLastCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.addLast(command);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}

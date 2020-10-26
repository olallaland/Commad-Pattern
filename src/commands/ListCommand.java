package commands;

public class ListCommand implements Command {
    private Model model;
    private String command;

    public ListCommand(Model model) {
        this.model = model;
    }

    public ListCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.list(command);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}

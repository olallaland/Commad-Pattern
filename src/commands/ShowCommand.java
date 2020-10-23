package commands;

public class ShowCommand implements Command {
    private Model model;
    String command;
    public ShowCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }
    @Override
    public void execute() {
        this.model.show(command);
    }
}

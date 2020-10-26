package commands;

public class DeleteLastCommand implements Command {
    private Model model;
    private String command;

    public DeleteLastCommand(Model model) {
        this.model = model;
    }

    public DeleteLastCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.deleteLast(command);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}

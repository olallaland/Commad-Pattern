package commands;

public class DeleteFirstCommand implements Command {
    private Model model;
    private String command;

    public DeleteFirstCommand(Model model) {
        this.model = model;
    }

    public DeleteFirstCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.deleteFirst(command);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}

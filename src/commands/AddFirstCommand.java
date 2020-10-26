package commands;

public class AddFirstCommand implements Command {
    private Model model;
    private String command;

    public AddFirstCommand(Model model) {
        this.model = model;
    }

    public AddFirstCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.addFirst(command);
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }

}

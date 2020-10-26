package commands;

public class ShowCommand implements Command {
    private Model model;
    private String command;

    public ShowCommand(Model model) {
        this.model = model;
    }

    public ShowCommand(Model model, String command) {
        this.model = model;
        this.command = command;
    }

    @Override
    public void execute() {
        this.model.show();
    }

    @Override
    public void setCommand(String command) {
        this.command = command;
    }
}

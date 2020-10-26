package commands;

public class RedoCommand implements Command {
    private Model model;
    public RedoCommand(Model model) {
        this.model = model;
    }
    @Override
    public void execute() {
        this.model.redo();
    }

    @Override
    public void setCommand(String command) {

    }
}

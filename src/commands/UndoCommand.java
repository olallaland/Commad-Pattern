package commands;

public class UndoCommand implements Command {
    private Model model;
    public UndoCommand(Model model) {
        this.model = model;
    }
    @Override
    public void execute() {
        this.model.undo();
    }

    @Override
    public void setCommand(String command) {
    }
}

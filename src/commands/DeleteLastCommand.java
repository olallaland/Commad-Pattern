package commands;

public class DeleteLastCommand implements Command {
    private Model model;
    private int count;
    public DeleteLastCommand(Model model, int count) {
        this.model = model;
        this.count = count;
    }
    @Override
    public void execute() {
        this.model.deleteLast(count);
    }
}

package commands;

public class DeleteFirstCommand implements Command {
    private Model model;
    private int count;
    public DeleteFirstCommand(Model model, int count) {
        this.model = model;
        this.count = count;
    }
    @Override
    public void execute() {
        this.model.deleteFirst(count);
    }
}

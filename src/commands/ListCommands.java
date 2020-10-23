package commands;

public class ListCommands implements Command {
    private Model model;
    private int count;
    public ListCommands(Model model, int count) {
        this.model = model;
        this.count = count;
    }
    @Override
    public void execute() {
        this.model.list(count);
    }
}

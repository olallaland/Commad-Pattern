package commands;

public class ListCommands implements Command {
    private Model model;
    private String content;
    public ListCommands(Model model, String content) {
        this.model = model;
        this.content = content;
    }
    @Override
    public void execute() {
        this.model.list(content);
    }
}

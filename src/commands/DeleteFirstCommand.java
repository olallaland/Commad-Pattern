package commands;

public class DeleteFirstCommand implements Command {
    private Model model;
    private String content;
    public DeleteFirstCommand(Model model, String content) {
        this.model = model;
        this.content = content;
    }
    @Override
    public void execute() {
        this.model.deleteFirst(content);
    }
}

package commands;

public class DeleteLastCommand implements Command {
    private Model model;
    private String content;
    public DeleteLastCommand(Model model, String content) {
        this.model = model;
        this.content = content;
    }
    @Override
    public void execute() {
        this.model.deleteLast(content);
    }
}

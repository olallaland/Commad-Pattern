package commands;

public class AddLastCommand implements Command {
    private Model model;
    private String content;
    public AddLastCommand(Model model, String content) {
        this.model = model;
        this.content = content;
    }
    @Override
    public void execute() {
        this.model.addLast(content);
    }
}

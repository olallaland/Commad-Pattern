package commands;

public class AddFirstCommand implements Command {
    private Model model;
    private String content;
    public AddFirstCommand(Model model, String content) {
        this.model = model;
        this.content = content;
    }
    @Override
    public void execute() {
        this.model.addFirst(content);
    }

}

package commands;

public class ShowCommand implements Command {
    private Model model;
    public ShowCommand(Model model) {
        this.model = model;
    }
    @Override
    public void execute() {
        this.model.show();
    }
}

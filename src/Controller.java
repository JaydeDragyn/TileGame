public abstract class Controller {

    protected Controller controller;
    protected DisplayPanel displayPanel;

    public Controller(Controller controller) { this.controller = controller; }
    public abstract void initialize();
    public DisplayPanel getDisplayPanel() { return displayPanel; }
    public abstract void react(Button button);
    public abstract void react(Command command, Object object);

}

public abstract class Controller {

    public abstract void initialize();
    public abstract DisplayElement getDisplayPanel();
    public abstract void react(Button button);
    public abstract void react(Command command, Object object);

}

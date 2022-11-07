public abstract class Controller {

    public abstract void initialize();
    public abstract DisplayPanel getDisplayPanel();
    public abstract void hover(Button button);
    public abstract void unhover();
    public abstract void press(Button button);
    public abstract void react(Button button);
}

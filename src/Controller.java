public abstract class Controller {

    public abstract void initialize();
    public abstract DisplayPanel getDisplayPanel();
    public abstract void react(Button button);
    public abstract void hover(Button button);
    public abstract void unhover();

}

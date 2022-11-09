/*
    class Controller

    This is a base class so the Buttons all have a consistent interface
    when the user hovers over them, presses them, etc.

 */

public abstract class Controller {

    public abstract void hover(Button button);
    public abstract void unhover();
    public abstract void press(Button button);
    public abstract void react(Button button);
}

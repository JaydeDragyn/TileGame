public enum TileColor {

    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    PURPLE("Purple"),
    CYAN("Cyan"),
    MAGENTA("Magenta"),
    BROWN("Brown"),
    BLACK("Black"),
    GREY("Grey"),
    WHITE("White");

    private final String color;

    TileColor(String color) {
        this.color = color;
    }

    public String toString() {
        return this.color;
    }

    public static TileColor nextColor(TileColor currentColor) {
        TileColor[] t = TileColor.values();
        TileColor newColor;
        if (currentColor.ordinal() < (t.length - 1)) {
            newColor = t[currentColor.ordinal() +1];
        } else {
            newColor = t[0];
        }
        return newColor;
    }
}

/*
    enum TileColor

    An enumeration of the colors available for Tiles.  Except for
    initial colors for the color progressions, and if the Help file
    wants to include tile images, no other part of the program needs
    to actually know how many colors there are, or even what colors
    there are.
    As long as the textures are named correctly and placed
    in the correct folder, adding new colors is as easy as adding
    another line to this enumeration with that color name.

    This enumeration provides a facility to cycle through the colors
    without needing to know how many colors there are.  The static
    .nextColor(currentColor) method will return the next color in the
    list (or the first color if currentColor is the last color in the
    list).

 */
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

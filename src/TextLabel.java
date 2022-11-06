import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TextLabel extends StaticDisplayElement {

    public enum FontSize {
        SMALL,
        MEDIUM,
        LARGE
    }

    public static Dimension getTextLabelSize(String text, FontSize fontSize) {
        Font font;
        switch (fontSize) {
            case LARGE -> font = fontLarge;
            case MEDIUM -> font = fontMedium;
            default -> font = fontSmall;
        }
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        return new Dimension(fontMetrics.stringWidth(text), fontMetrics.getHeight());
    }

    private final BufferedImage texture;

    public TextLabel(String text, Point location, FontSize fontSize, Color color) {
        super(location);
        if (!fontLoaded) { registerFont(); }

        Font font;
        switch (fontSize) {
            case LARGE -> font = fontLarge;
            case MEDIUM -> font = fontMedium;
            default -> font = fontSmall;
        }

        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        this.size = new Dimension(fontMetrics.stringWidth(text)+1, fontMetrics.getHeight());

        texture = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D pen = texture.createGraphics();

        pen.setFont(font);
        pen.setColor(color);
        pen.drawString(text, 0, fontMetrics.getAscent());

        pen.dispose();
    }

    public BufferedImage getTexture() {
        return texture;
    }
    public static Font fontLarge;
    public static Font fontMedium;
    public static Font fontSmall;

    private static boolean fontLoaded = false;

    private void registerFont() {
        String boldFontName = "/fonts/BAUHS93.TTF";
        String regularFontName = "/fonts/BauhausRegular.ttf";
        float largeSize = 40.0f;
        float mediumSize = 32.0f;
        float smallSize = 24.0f;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            fontLarge = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getResourceAsStream(boldFontName)));
            fontLarge = fontLarge.deriveFont(Font.PLAIN, largeSize);
            ge.registerFont(fontLarge);

            fontMedium = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getResourceAsStream((regularFontName))));
            fontMedium = fontMedium.deriveFont(Font.PLAIN, mediumSize);

            fontSmall = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getResourceAsStream((regularFontName))));
            fontSmall = fontSmall.deriveFont(Font.PLAIN, smallSize);

            fontLoaded = true;

            // if createFont, deriveFont or registerFont fail, they will throw a FontFormatException,
            // and we will try to use a system monospaced font instead, but it'll be ugly!
        } catch (FontFormatException | IOException | NullPointerException e1) {
            fontLarge = new Font("monospaced", Font.PLAIN, 40);
            fontMedium = new Font("monospaced", Font.PLAIN, 32);
            fontSmall = new Font("monospaced", Font.PLAIN, 24);
            System.out.println("Failed to load a font");
        }
    }

}


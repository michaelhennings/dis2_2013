package windowSystem;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Window dependent drawing context. This class abstracts away from the drawing
 * commands which are provided by the GraphicsEventSystem. The drawing commands
 * are all relative to the window for which the context is used. This class also
 * makes it possible to store a drawing state (current color, etc.) per window.
 */
public class DrawingContext {

  private WindowSystem windowSystem;

  private SimpleWindow window;

  private Color color;

  DrawingContext(WindowSystem windowSystem, SimpleWindow window) {
    this.windowSystem = windowSystem;
    this.window = window;
    color = Color.WHITE;
  }

  /**
   * Restores the values of this drawing context. This is called by the window
   * system when the context is switched.
   */
  void init() {
    windowSystem.setColor(color);
//    windowSystem.setFont(font);
  }

  private PointI convertCoordinates(PointF point) {
    int desktopX = (int) (window.getDesktopArea().x + point.getX() * window.getDesktopArea().width);
    int desktopY = (int) (window.getDesktopArea().y + point.getY() * window.getDesktopArea().height);

    return new PointI(desktopX, desktopY);
  }

  private RectangleI convertCoordinates(RectangleF rectangle) {
    int desktopX = (int) (window.getDesktopArea().x + rectangle.getX() * window.getDesktopArea().width);
    int desktopY = (int) (window.getDesktopArea().y + rectangle.getY() * window.getDesktopArea().height);
    int desktopWidth = (int) (window.getDesktopArea().getWidth() * rectangle.getWidth());
    int desktopHeight = (int) (window.getDesktopArea().getHeight() * rectangle.getHeight());

    return new RectangleI(desktopX, desktopY, desktopWidth, desktopHeight);
  }

  public void fillRect(RectangleF rectangle) {
    int desktopX = (int) (window.getDesktopArea().x + rectangle.getX() * window.getDesktopArea().width);
    int desktopY = (int) (window.getDesktopArea().y + rectangle.getY() * window.getDesktopArea().height);
    int desktopWidth = (int) (window.getDesktopArea().getWidth() * rectangle.getWidth());
    int desktopHeight = (int) (window.getDesktopArea().getHeight() * rectangle.getHeight());

    Rectangle rect = new Rectangle(desktopX, desktopY, desktopWidth, desktopHeight);
    rect = window.getDesktopArea().intersection(rect);

    windowSystem.fillRect(rect.x, rect.y, rect.getWidth(), rect.getHeight());
  }

  public void drawRect(RectangleF rectangle) {
    int desktopX = (int) (window.getDesktopArea().x + rectangle.getX() * window.getDesktopArea().width);
    int desktopY = (int) (window.getDesktopArea().y + rectangle.getY() * window.getDesktopArea().height);
    int desktopWidth = (int) (window.getDesktopArea().getWidth() * rectangle.getWidth());
    int desktopHeight = (int) (window.getDesktopArea().getHeight() * rectangle.getHeight());

    Rectangle rect = new Rectangle(desktopX, desktopY, desktopWidth, desktopHeight);
    rect = window.getDesktopArea().intersection(rect);

    windowSystem.drawRect(rect.x, rect.y, rect.getWidth(), rect.getHeight());
  }

  public void drawLine(PointF start, PointF end) {
    int desktopStartX = (int) (window.getDesktopArea().x + start.getX() * window.getDesktopArea().width);
    int desktopStartY = (int) (window.getDesktopArea().y + start.getY() * window.getDesktopArea().height);

    int desktopEndX = (int) (window.getDesktopArea().x + end.getX() * window.getDesktopArea().width);
    int desktopEndY = (int) (window.getDesktopArea().y + end.getY() * window.getDesktopArea().height);

    Rectangle rect = new Rectangle(desktopStartX, desktopStartY, desktopEndX - desktopStartX, desktopEndY - desktopStartY);
    rect = window.getDesktopArea().intersection(rect);

    windowSystem.drawLine((double) rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
  }

  public void setColor(Color color) {
    this.color = color;
    windowSystem.setColor(color);
  }

  public int getDesktopWidth() {
    return windowSystem.getDesktopWidth();
  }

  public int getDesktopHeight() {
    return windowSystem.getDesktopHeight();
  }

  public void drawString(PointF bottomLeft, String text) {
    PointI pos = convertCoordinates(bottomLeft);
    windowSystem.drawString(text, pos.x, pos.y);
  }

  public void drawStringCentered(PointF center, String text) {
    PointI pos = convertCoordinates(center);

    RectangleI size = windowSystem.getTextSize(text);
    pos.x -= size.getWidth() / 2;
    pos.y += size.getHeight() / 2;

    /** Brute-force variant of clipping the text **/

    int clipRight = window.getDesktopArea().x + window.getDesktopArea().width;
    int clipLeft = window.getDesktopArea().x;

    while(pos.x+size.getWidth() > clipRight && !text.isEmpty()) {
      text = text.substring(0, text.length()-1);
      size = windowSystem.getTextSize(text);
    }
    int alignRightPosition = pos.x+size.getWidth();
    while(pos.x < clipLeft && !text.isEmpty()) {
      text = text.substring(1);
      pos.x = alignRightPosition - windowSystem.getTextSize(text).getWidth();
    }

    windowSystem.drawString(text, pos.x, pos.y);
  }

//  public void setFont(Font font) {
//    this.font = font;
//    windowSystem.setFont(font);
//  }
//
//  public Font getFont() {
//    return font;
//  }

  void requestRepaint() {
    windowSystem.requestRepaint(new Rectangle(0, 0, getDesktopWidth(), getDesktopHeight()));
  }
}
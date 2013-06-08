package windowManager.rat;

import java.awt.Color;
import windowManager.rat.borders.LineBorder;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;

/**
 * A simple label that shows a centered text.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatLabel extends RatWidget {

  private String text;

  private Color foreground = Color.BLACK;
  
  private Color background = Color.WHITE;
  
  public RatLabel(RectangleF windowArea, String text) {
    super(windowArea);
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
    requestRepaint();
  }

  public void setBackground(Color background) {
    this.background = background;
    requestRepaint();
  }

  public Color getBackground() {
    return background;
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    context.setColor(background);
    context.fillRect(RectangleF.FULL);
    context.setColor(foreground);
    context.drawStringCentered(new PointF(0.5f, 0.5f), text);
    super.handlePaint(context);
  }
}
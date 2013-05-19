package windowManager;

import java.awt.Color;
import windowManager.rat.RatButton;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;

/**
 *
 * @author Andre
 */
public class CloseWindow extends RatButton {

  public CloseWindow(RectangleF windowArea) {
    super(windowArea, "");
    setBackground(Color.RED);
    setActiveBackground(Color.GREEN);
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    super.handlePaint(context);
    context.setColor(Color.white);
    context.drawLine(new PointF(.1f, .1f), new PointF(.9f, .9f));
    context.drawLine(new PointF(.9f, .1f), new PointF(.1f, .9f));
  }
}
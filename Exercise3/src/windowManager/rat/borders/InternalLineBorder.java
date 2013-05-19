package windowManager.rat.borders;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.PointF;

/**
 * Line Border that has an invisible distance to the frame.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class InternalLineBorder implements IBorder {

  private int thickness = 1;

  private final Color borderColor;

  private final float distanceX;

  private final float distanceY;

  public InternalLineBorder(int thickness, Color color, float distanceX, float distanceY) {
    this.thickness = thickness;
    this.borderColor = new Color(color.getRGB());
    this.distanceX = distanceX;
    this.distanceY = distanceY;
  }

  public InternalLineBorder(int thickness, Color color, float distance) {
    this(thickness, color, distance, distance);
  }

  @Override
  public void draw(DrawingContext context) {
    context.setColor(borderColor);
    PointF topLeft = new PointF(distanceX, distanceY);
    PointF topRight = new PointF(1 - distanceX, distanceY);
    PointF bottomLeft = new PointF(distanceX, 1 - distanceY);
    PointF bottomRight = new PointF(1 - distanceX, 1 - distanceY);
    context.drawLine(topLeft, topRight);
    context.drawLine(topRight, bottomRight);
    context.drawLine(bottomLeft, bottomRight);
    context.drawLine(topLeft, bottomLeft);
  }
}

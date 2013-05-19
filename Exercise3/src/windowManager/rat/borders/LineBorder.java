package windowManager.rat.borders;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.PointF;

/**
 * Simple line border that features a color and a thickness.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class LineBorder implements IBorder {

  private int thickness = 1;

  private final Color borderColor;

  public LineBorder(int thickness, Color color) {
    this.thickness = thickness;
    this.borderColor = new Color(color.getRGB());
  }

  @Override
  public void draw(DrawingContext context) {
    context.setColor(borderColor);
    context.drawLine(PointF.TOP_LEFT, PointF.TOP_RIGHT);
    context.drawLine(PointF.TOP_RIGHT, PointF.BOTTOM_RIGHT);
    context.drawLine(PointF.BOTTOM_LEFT, PointF.BOTTOM_RIGHT);
    context.drawLine(PointF.TOP_LEFT, PointF.BOTTOM_LEFT);
  }

  @Override
  public String toString() {
    return "LineBorder{" + "thickness=" + thickness + ", borderColor=" + borderColor + '}';
  }
}
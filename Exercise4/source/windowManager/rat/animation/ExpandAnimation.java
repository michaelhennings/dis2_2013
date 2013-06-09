package windowManager.rat.animation;

import windowManager.rat.RatWidget;
import windowSystem.RectangleF;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class ExpandAnimation<T extends RatWidget> extends Animation<T> {

  private final RectangleF from;

  private final RectangleF to;

  public ExpandAnimation(int duration, RectangleF from, RectangleF to) {
    super(duration);
    this.from = new RectangleF(from);
    this.to = new RectangleF(to);
  }

  @Override
  public void animate(RatWidget widget, double progress) {
    float x = (float) (from.getX() + (to.getX() - from.getX()) * progress);
    float y = (float) (from.getY() + (to.getY() - from.getY()) * progress);
    float width = (float) (from.getWidth() + (to.getWidth() - from.getWidth()) * progress);
    float height = (float) (from.getHeight() + (to.getHeight() - from.getHeight()) * progress);
    RectangleF target = new RectangleF(x, y, width, height);
    widget.setWindowArea(target);
    widget.requestRepaint();
  }
}

package windowManager.rat.events;

import windowSystem.SimpleWindow;

/**
 * Properties for mouse events.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatMouseEvent extends RatEvent {

  public final float x;

  public final float y;

  public RatMouseEvent(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public RatMouseEvent(float x, float y, SimpleWindow source) {
    this(x, y, null, source);
  }

  public RatMouseEvent(float x, float y, RatEvent parent, SimpleWindow source) {
    super(parent, source);
    this.x = x;
    this.y = y;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  @Override
  public String toString() {
    return "RatMouseEvent{" + "x=" + x + ", y=" + y + '}';
  }
}

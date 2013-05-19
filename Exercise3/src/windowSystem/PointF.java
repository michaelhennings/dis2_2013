package windowSystem;

public class PointF {

  private float x;

  private float y;

  public static final PointF ZERO = new PointF(0, 0);

  public static final PointF TOP_LEFT = new PointF(0, 0);

  public static final PointF TOP_RIGHT = new PointF(1, 0);

  public static final PointF BOTTOM_LEFT = new PointF(0, 1);

  public static final PointF BOTTOM_RIGHT = new PointF(1, 1);

  public PointF(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void setX(float value) {
    x = value;
  }

  public float getX() {
    return x;
  }

  public void setY(float value) {
    y = value;
  }

  public float getY() {
    return y;
  }
}

package windowSystem;

/**
 * A rectangle with float values.
 */
public class RectangleF {

  public final static RectangleF FULL = new RectangleF(0, 0, 1, 1);

  private float x;

  private float y;

  private float width;

  private float height;

  public RectangleF(float x, float y, float width, float height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public RectangleF(RectangleF rectangle) {
    this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getWidth() {
    return width;
  }

  public void setWidth(float width) {
    this.width = width;
  }

  public float getHeight() {
    return height;
  }

  public void setHeight(float height) {
    this.height = height;
  }

  public float getRight() {
    return x + width;
  }

  public float getBottom() {
    return y + height;
  }

  public boolean contains(PointF point) {
    if (point.getX() < x || point.getX() > getRight()) {
      return false;
    }
    if (point.getY() < y || point.getY() > getBottom()) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "RectangleF{" + "x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + '}';
  }
}

package windowManager.rat;

import java.awt.Color;
import windowManager.rat.events.AbstractRatMouseListener;
import windowManager.rat.events.IRatEventListener;
import windowManager.rat.events.IRatSliderStateChangedListener;
import windowManager.rat.events.RatEvent;
import windowManager.rat.events.RatMouseEvent;
import windowManager.rat.events.RatSliderStateChangedEvent;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;

/**
 * A simple slider.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatSlider extends RatWidget {

  private static final float SLIDER_WIDTH = 0.02f;

  private static final Color SLIDER_COLOR_DEFAULT = Color.BLACK;

  private static final Color SLIDER_COLOR_ACTIVE = Color.LIGHT_GRAY;

  private float minimum = 0;

  private float maximum = 100;

  private float relativeValue = 0.5f;

  private Color sliderColor = Color.BLACK;

  /**
   * Creates a new slider. Default values range from 0 to 100.
   *
   * @param windowArea
   */
  public RatSlider(RectangleF windowArea) {
    super(windowArea);
    setOpaque(true);
    setBackground(Color.WHITE);
    addMouseListener(new AbstractRatMouseListener() {
      float startX;

      float startValue;

      @Override
      public void mouseDragged(RatMouseEvent event) {
        if (startX >= 0) {
          float distance = event.x - startX;
          float relValOld = relativeValue;
          relativeValue = startValue + distance / (1 - SLIDER_WIDTH);
          relativeValue = Math.max(0, relativeValue);
          relativeValue = Math.min(1, relativeValue);
          if (relValOld != relativeValue) {
            requestRepaint();
            fireStateChanged(getValue(), event);
          }
        }
      }

      @Override
      public void mousePressed(RatMouseEvent event) {
        if (getSliderBox().contains(new PointF(event.x, event.y))
                && sliderColor != SLIDER_COLOR_ACTIVE) {

          startX = event.x;
          startValue = relativeValue;
          sliderColor = SLIDER_COLOR_ACTIVE;
          requestRepaint();
        }
      }

      @Override
      public void mouseReleased(RatMouseEvent event) {
        if (sliderColor != SLIDER_COLOR_DEFAULT) {
          sliderColor = SLIDER_COLOR_DEFAULT;
          startX = -1;
          requestRepaint();
        }
      }

      @Override
      public void mouseClicked(RatMouseEvent event) {
        RectangleF box = getSliderBox();
        if (!box.contains(new PointF(event.x, event.y))) {
          float relValOld = relativeValue;
          if (event.x > box.getX() + box.getWidth()) {
            relativeValue += 0.2f;
          } else if (event.x < box.getX()) {
            relativeValue -= 0.2f;
          }
          relativeValue = Math.max(0, relativeValue);
          relativeValue = Math.min(1, relativeValue);
          if (relValOld != relativeValue) {
            requestRepaint();
            fireStateChanged(getValue(), event);
          }
        }
      }
    });
  }

  public void addSliderStateChangeListener(IRatSliderStateChangedListener listener) {
    addListener(listener, IRatSliderStateChangedListener.class);
  }

  public void removeSliderStateChangeListener(IRatSliderStateChangedListener listener) {
    removeListener(listener, IRatSliderStateChangedListener.class);
  }

  protected void fireStateChanged(float value, RatEvent parent) {
    RatSliderStateChangedEvent event = new RatSliderStateChangedEvent(value, parent, this);
    if (listenerList.containsKey(IRatSliderStateChangedListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatSliderStateChangedListener.class)) {
        ((IRatSliderStateChangedListener) listener).sliderStateChanged(event);
      }
    }
  }

  public float getMaximum() {
    return maximum;
  }

  public float getMinimum() {
    return minimum;
  }

  public void setMinimum(float minimum) {
    this.minimum = minimum;
  }

  public void setMaximum(float maximum) {
    this.maximum = maximum;
  }

  public float getValue() {
    return minimum + relativeValue * (maximum - minimum);
  }

  public void setValue(float value) {
    this.relativeValue = value / (maximum - minimum);
    requestRepaint();
  }

  private RectangleF getSliderBox() {
    float posX = relativeValue * (1 - SLIDER_WIDTH);
    return new RectangleF(posX, 0.03f, SLIDER_WIDTH, 0.94f);
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    super.handlePaint(context);
    context.setColor(Color.BLACK);
    context.drawRect(new RectangleF(SLIDER_WIDTH / 2, 0.42f, 1 - SLIDER_WIDTH, 0.17f));
    context.setColor(sliderColor);
    context.fillRect(getSliderBox());
  }
}
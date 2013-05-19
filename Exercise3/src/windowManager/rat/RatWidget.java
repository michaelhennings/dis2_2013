package windowManager.rat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import windowManager.rat.borders.IBorder;
import windowManager.rat.events.IRatEventListener;
import windowManager.rat.events.IRatMouseListener;
import windowManager.rat.events.RatMouseEvent;
import windowSystem.DrawingContext;
import windowSystem.IMouseCallback;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 * Base class for all RAT widgets. Handles event forwarding from the window
 * manager and provides common functionality.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public abstract class RatWidget extends SimpleWindow {

  protected Map<Class<? extends IRatEventListener>, List<IRatEventListener>> listenerList = new HashMap<>();

  private IBorder border = null;

  public RatWidget(RectangleF windowArea) {
    super(windowArea);
    super.setMouseClickedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        fireMouseClickEvent(point);
      }
    });
    super.setMouseMovedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        fireMouseMoveEvent(point);
      }
    });
    super.setMousePressedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        fireMousePressedEvent(point);
      }
    });
    super.setMouseReleasedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        fireMouseReleasedEvent(point);
      }
    });
  }

  public void setBorder(IBorder border) {
    this.border = border;
  }

  public IBorder getBorder() {
    return border;
  }

  @Override
  @Deprecated
  public final void setMouseClickedCallback(IMouseCallback mouseClickedCallback) {
    super.setMouseClickedCallback(mouseClickedCallback);
  }

  @Override
  @Deprecated
  public void setMouseMovedCallback(IMouseCallback mouseMovedCallback) {
    super.setMouseMovedCallback(mouseMovedCallback);
  }

  private void paintBorder(DrawingContext context) {
    if (border != null) {
      border.draw(context);
    }
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    paintBorder(context);
    super.handlePaint(context);
  }

  public void addMouseListener(IRatMouseListener listener) {
    if (!listenerList.containsKey(IRatMouseListener.class)) {
      listenerList.put(IRatMouseListener.class, new ArrayList<IRatEventListener>());
    }
    listenerList.get(IRatMouseListener.class).add(listener);
  }

  public void removeMouseListener(IRatMouseListener listener) {
    if (listenerList.containsKey(IRatMouseListener.class)) {
      listenerList.get(IRatMouseListener.class).remove(listener);
    }
  }

  protected void fireMouseClickEvent(PointF point) {
    RatMouseEvent event = new RatMouseEvent(point.getX(), point.getY(), this);
    if (listenerList.containsKey(IRatMouseListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatMouseListener.class)) {
        ((IRatMouseListener) listener).mouseClicked(event);
      }
    }
  }

  protected void fireMouseMoveEvent(PointF point) {
    RatMouseEvent event = new RatMouseEvent(point.getX(), point.getY(), this);
    if (listenerList.containsKey(IRatMouseListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatMouseListener.class)) {
        ((IRatMouseListener) listener).mouseMoved(event);
      }
    }
  }

  protected void fireMousePressedEvent(PointF point) {
    RatMouseEvent event = new RatMouseEvent(point.getX(), point.getY(), this);
    if (listenerList.containsKey(IRatMouseListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatMouseListener.class)) {
        ((IRatMouseListener) listener).mousePressed(event);
      }
    }
  }

  protected void fireMouseReleasedEvent(PointF point) {
    RatMouseEvent event = new RatMouseEvent(point.getX(), point.getY(), this);
    if (listenerList.containsKey(IRatMouseListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatMouseListener.class)) {
        ((IRatMouseListener) listener).mouseReleased(event);
      }
    }
  }
}
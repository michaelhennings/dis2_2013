package windowManager.rat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import windowManager.rat.borders.IBorder;
import windowManager.rat.events.IRatAnimationTickListener;
import windowManager.rat.events.IRatEventListener;
import windowManager.rat.events.IRatMouseListener;
import windowManager.rat.events.RatAnimationTickEvent;
import windowManager.rat.events.RatMouseEvent;
import windowSystem.DrawingContext;
import windowSystem.IMouseCallback;
import windowSystem.ITickCallback;
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

  protected Map<Class<? extends IRatEventListener>, List<IRatEventListener>> listenerList =
          new HashMap<Class<? extends IRatEventListener>, List<IRatEventListener>>();

  private IBorder border = null;

  private Color background;

  private boolean opaque = false;

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
    super.setMouseDraggedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        fireMouseDraggedEvent(point);
      }
    });
    super.setTickCallback(new ITickCallback() {
      @Override
      public void tick() {
        fireAnimationTickEvent();
      }
    });
  }

  public void setBackground(Color background) {
    this.background = background;
  }

  public Color getBackground() {
    return background;
  }

  public void setBorder(IBorder border) {
    this.border = border;
    requestRepaint();
  }

  public IBorder getBorder() {
    return border;
  }

  @Deprecated
  @Override
  protected final void setTickCallback(ITickCallback callback) {
    super.setTickCallback(callback);
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
    super.handlePaint(context);
    paintBorder(context);
    if (opaque) {
      context.setColor(background);
      context.fillRect(RectangleF.FULL);
    }
  }

  public void setOpaque(boolean opaque) {
    this.opaque = opaque;
  }

  public boolean isOpaque() {
    return opaque;
  }

  protected void addListener(IRatEventListener listener, Class<? extends IRatEventListener> clazz) {
    if (!listenerList.containsKey(clazz)) {
      listenerList.put(clazz, new ArrayList<IRatEventListener>());
    }
    listenerList.get(clazz).add(listener);
  }

  protected void removeListener(IRatEventListener listener, Class<? extends IRatEventListener> clazz) {
    if (listenerList.containsKey(clazz)) {
      listenerList.get(clazz).remove(listener);
    }
  }

  public void addMouseListener(IRatMouseListener listener) {
    addListener(listener, IRatMouseListener.class);
  }

  public void removeMouseListener(IRatMouseListener listener) {
    removeListener(listener, IRatMouseListener.class);
  }
  
  public void addTickListener(IRatAnimationTickListener listener) {
    addListener(listener, IRatAnimationTickListener.class);
  }

  public void removeTickListener(IRatAnimationTickListener listener) {
    removeListener(listener, IRatAnimationTickListener.class);
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

  protected void fireMouseDraggedEvent(PointF point) {
    RatMouseEvent event = new RatMouseEvent(point.getX(), point.getY(), this);
    if (listenerList.containsKey(IRatMouseListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatMouseListener.class)) {
        ((IRatMouseListener) listener).mouseDragged(event);
      }
    }
  }

  protected void fireAnimationTickEvent() {
    RatAnimationTickEvent event = new RatAnimationTickEvent();
    if(listenerList.containsKey(IRatAnimationTickListener.class)) {
      for (IRatEventListener listener : listenerList.get(IRatAnimationTickListener.class)) {
        ((IRatAnimationTickListener) listener).tick(event);        
      }
    }
  }
}
package ws;


import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {

  /**
   * Width of the virtual desktop.
   */
  private final int width;

  /**
   * Height of the virtual desktop.
   */
  private final int height;

  /**
   * Maintains a list of windows that belong to this desktop. The order
   * represents the z-index of each window: the lower the z-index, the closer
   * will the window will be up-front in the list.
   */
  private final List<SimpleWindow> windowList;

  /**
   * Create a new virtual 'deskop' with the given (fixed) dimension.
   *
   * @param width Width of the window in px.
   * @param height Height of the window in px.
   */
  public WindowSystem(int width, int height) {
    super(width, height);
    this.windowList = new ArrayList<SimpleWindow>();
    this.width = width;
    this.height = height;
    this.setTitle("Desktop");

    // *** Allow the window to be closed ***

    WindowAdapter disposeOnClose = new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        WindowSystem.this.dispose();
      }
    };
    addWindowListener(disposeOnClose);
  }

  /**
   * Convert device independent abstract coordinates to desktop coordinates.
   * @param abstractCoord Abstract coordinate.
   * @return Desktop coordinate.
   */
  private Point<Integer> abstractToDesktopCoord(Point<Float> abstractCoord) {
    int x = (int) (width * abstractCoord.getX());
    int y = (int) (height * abstractCoord.getY());
    return new Point<Integer>(x, y);
  }

  /**
   * Draw the background. Currently its a simple line like a scar. Might evolve
   * into something more complex, like a picture or so.
   */
  private void drawBackground() {
    setColor(getBackground());
    drawRect(0, 0, width, height);
    setColor(Color.red);
    drawLine(0.2f, 0.3f, .8f, .7f);
  }

  /**
   * Handles the painting of the desktop, i.e. the background along with all
   * (visible) windows on top of it.
   */
  @Override
  public void handlePaint() {
    drawBackground();
    for (SimpleWindow window : windowList) {
      // TODO: Do something with the window here
    }
  }

  public void drawLine(float StartX, float StartY, float EndX, float EndY) {
    Point<Float> abstractStartPoint =
            new Point<Float>(StartX, StartY);
    Point<Float> abstractEndPoint =
            new Point<Float>(EndX, EndY);

    Point<Integer> desktopStartPoint =
            abstractToDesktopCoord(abstractStartPoint);
    Point<Integer> desktopEndPoint =
            abstractToDesktopCoord(abstractEndPoint);

    this.drawLine(desktopStartPoint.getX(), desktopStartPoint.getY(),
            desktopEndPoint.getX(), desktopEndPoint.getY());
  }

  /**
   * Add a window to the desktop.
   *
   * @param window The window to be added
   */
  public void addWindow(SimpleWindow window) {
    if (window == null) {
      throw new NullPointerException("window must not be null!");
    }
    windowList.add(window);
    revalidateWindows();
  }

  /**
   * Remove a window from the desktop.
   *
   * @param window The window to be removed
   */
  public void removeWindow(SimpleWindow window) {
    windowList.remove(window);
    revalidateWindows();
  }

  /**
   * This method re-evaluates the windows after adding/removing a window to the
   * desktop.
   */
  private void revalidateWindows() {
    handlePaint();
  }
}

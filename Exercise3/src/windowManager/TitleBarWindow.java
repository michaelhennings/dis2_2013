package windowManager;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.IMouseCallback;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 *
 * @author Andre
 */
public class TitleBarWindow extends SimpleWindow {

  private String title;

  private CloseWindow closeWindow;

  private ICloseCallback closeCallback;

  public TitleBarWindow(RectangleF drawingArea, String title) {
    super(drawingArea);
    this.title = title;
    closeWindow = new CloseWindow(new RectangleF(.01f, .01f, .1f, .8f));
    closeWindow.setMouseClickedCallback(new IMouseCallback() {
      @Override
      public void handleMouse(PointF point) {
        handleClose();
      }
    });
    addChild(closeWindow);

    closeCallback = null;
  }

  void handleClose() {
    if (closeCallback != null) {
      closeCallback.handleClose();
    }
  }

  @Override
  public void handlePaint(DrawingContext context) {
    context.setColor(Color.BLUE);
    context.fillRect(new RectangleF(.0f, .0f, 1.0f, 1.0f));
    context.setColor(Color.WHITE);
    context.drawStringCentered(new PointF(0.5f, 0.5f), title);
  }

  public void setCloseCallback(ICloseCallback closeCallback) {
    this.closeCallback = closeCallback;
  }
}

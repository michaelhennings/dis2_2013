package assignment3;

import java.awt.Color;
import windowManager.TopWindow;
import windowManager.WindowManager;
import windowManager.rat.events.AbstractRatMouseListener;
import windowManager.rat.RatButton;
import windowManager.rat.RatLabel;
import windowManager.rat.events.RatMouseEvent;
import windowManager.rat.events.IRatMouseListener;
import windowSystem.DrawingContext;
import windowSystem.RectangleF;
import windowSystem.WindowSystem;

/**
 * Exercise 3.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class HelloWorldGraphical extends TopWindow {

  public HelloWorldGraphical(RectangleF windowArea, String title) {
    super(windowArea, title);
    final RatLabel label = new RatLabel(new RectangleF(0.2f, 0.9f, 0.6f, 0.1f), "Guten Tag!");
    
    RatButton butEnglish = new RatButton(new RectangleF(0.1f, 0.1f, 0.8f, 0.2f), "English");
    butEnglish.addMouseListener(new AbstractRatMouseListener() {

      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("A wonderful evening to you, kind Sir!");
      }
    });
    RatButton butGerman = new RatButton(new RectangleF(0.1f, 0.35f, 0.8f, 0.2f), "German");
    butGerman.addMouseListener(new AbstractRatMouseListener() {

      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("Moin Moin!");
      }
    });
    RatButton butFrench = new RatButton(new RectangleF(0.1f, 0.6f, 0.8f, 0.2f), "French");
    butFrench.addMouseListener(new AbstractRatMouseListener() {

      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("Salut tout le monde!");
      }
    });
    
    addChild(butEnglish);
    addChild(butGerman);
    addChild(butFrench);
    addChild(label);
  }

  @Override
  protected void handlePaint(DrawingContext drawingContext) {
    drawingContext.setColor(Color.GRAY);
    drawingContext.fillRect(new RectangleF(0, 0, 1, 1));
  }

  public static void main(String[] args) {
    WindowSystem windowSystem = new WindowSystem(800, 600);
    WindowManager windowManager = new WindowManager(windowSystem);

    HelloWorldGraphical hwg = new HelloWorldGraphical(new RectangleF(0.1f, 0.1f, 0.7f, 0.5f), "HWG-2");
    windowManager.addTopWindow(hwg);
  }
}

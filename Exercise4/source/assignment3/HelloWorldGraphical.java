package assignment3;

import java.awt.Color;
import windowManager.TopWindow;
import windowManager.WindowManager;
import windowManager.rat.events.AbstractRatMouseListener;
import windowManager.rat.RatButton;
import windowManager.rat.RatLabel;
import windowManager.rat.RatSlider;
import windowManager.rat.RatTaskbar;
import windowManager.rat.events.RatMouseEvent;
import windowManager.rat.events.IRatSliderStateChangedListener;
import windowManager.rat.events.RatSliderStateChangedEvent;
import windowManager.taskbar.IApplication;
import windowSystem.DrawingContext;
import windowSystem.RectangleF;
import windowSystem.WindowSystem;

/**
 * Exercise WS Part 3.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class HelloWorldGraphical extends TopWindow {

  final float BUTTON_HEIGHT = 0.1f;

  private Color background = new Color(255, 128, 128);

  public HelloWorldGraphical(RectangleF windowArea, String title) {
    super(windowArea, title);

    final RatLabel label = new RatLabel(new RectangleF(0.2f, 0.9f, 0.6f, 0.1f), "Guten Tag!");

    RatButton butEnglish = new RatButton(new RectangleF(0.1f, 0.1f, 0.8f, BUTTON_HEIGHT), "English");
    butEnglish.addMouseListener(new AbstractRatMouseListener() {
      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("A wonderful evening to you, kind Sir!");
      }
    });
    RatButton butGerman = new RatButton(new RectangleF(0.1f, BUTTON_HEIGHT * 2.5f, 0.8f, BUTTON_HEIGHT), "German");
    butGerman.addMouseListener(new AbstractRatMouseListener() {
      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("Moin Moin!");
      }
    });
    RatButton butFrench = new RatButton(new RectangleF(0.1f, BUTTON_HEIGHT * 4f, 0.8f, BUTTON_HEIGHT), "French");
    butFrench.addMouseListener(new AbstractRatMouseListener() {
      @Override
      public void mouseClicked(RatMouseEvent event) {
        label.setText("Bonjour tout le monde!");
      }
    });

    RatSlider slider = new RatSlider(new RectangleF(0.15f, BUTTON_HEIGHT * 6f, 0.7f, BUTTON_HEIGHT * 1.5f));
    slider.setMinimum(0);
    slider.setMaximum(255);
    slider.addSliderStateChangeListener(new IRatSliderStateChangedListener() {
      @Override
      public void sliderStateChanged(RatSliderStateChangedEvent event) {
        int val = (int) event.getValue();
        label.setBackground(new Color(255, 255 - val, val));
      }
    });

    addChild(butEnglish);
    addChild(butGerman);
    addChild(butFrench);
    addChild(slider);

    addChild(label);
  }

  @Override
  protected void handlePaint(DrawingContext drawingContext) {
    drawingContext.setColor(background);
    drawingContext.fillRect(new RectangleF(0, 0, 1, 1));
  }

  public static void main(String[] args) {
    RatTaskbar taskbar = new RatTaskbar();
    taskbar.addApplication(new IApplication() {
      @Override
      public String getName() {
        return "Hello World G";
      }

      @Override
      public TopWindow newInstance() {
        return new HelloWorldGraphical(new RectangleF(0.15f, 0.2f, 0.7f, 0.5f), "HWG-2");
      }
    });

    WindowSystem windowSystem = new WindowSystem(800, 600);
    WindowManager windowManager = new WindowManager(windowSystem, taskbar);

    HelloWorldGraphical hwg = new HelloWorldGraphical(new RectangleF(0.15f, 0.2f, 0.7f, 0.5f), "HWG-2");
    windowManager.addTopWindow(hwg);
  }
}
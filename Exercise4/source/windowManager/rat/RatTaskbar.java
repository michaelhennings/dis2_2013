package windowManager.rat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import windowManager.TopWindow;
import windowManager.WindowManager;
import windowManager.rat.animation.*;
import windowManager.rat.events.AbstractRatMouseListener;
import windowManager.rat.events.RatMouseEvent;
import windowManager.taskbar.IApplication;
import windowManager.taskbar.ITaskbar;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;
import windowSystem.WindowSystem;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatTaskbar extends RatButton implements ITaskbar {

  private final List<IApplication> applications = new ArrayList<IApplication>();

  private WindowManager manager;

  private WindowSystem ws;

  public RatTaskbar() {
    super(new RectangleF(0f, 0f, 0.1f, 0.1f), "R");
    setBackground(new Color(128, 128, 255));
    addMouseListener(new AbstractRatMouseListener() {
      @Override
      public void mouseClicked(RatMouseEvent event) {
        toggleShowApplications();
      }
    });
  }

  @Override
  public void setWindowManager(WindowManager wm) {
    this.manager = wm;
  }

  boolean applicationsVisible = false;

  protected void toggleShowApplications() {
    applicationsVisible = !applicationsVisible;

    if (applicationsVisible) {
      showApplications();
    } else {
      hideApplications();
    }
  }

  @Override
  public SimpleWindow asSimpleWindow() {
    return this;
  }

  private final List<RatButton> appButtons = new ArrayList<RatButton>();

  protected void showApplications() {
    applicationsVisible = true;
    RectangleF rectFrom = new RectangleF(0.1f, 0.0f, 0.0f, 0.1f);
    RectangleF rectTo = new RectangleF(0.1f, 0.0f, 0.15f, 0.1f);
    for (final IApplication app : applications) {
      RatButton button = new RatButton(rectFrom, app.getName());
      button.addMouseListener(new AbstractRatMouseListener() {
        @Override
        public void mouseClicked(RatMouseEvent event) {
          TopWindow newInstance = app.newInstance();
          manager.addTopWindow(newInstance);
          hideApplications();
        }
      });
      appButtons.add(button);
      ws.addWindow(button);
      Animator<RatButton> animator = new Animator<RatButton>(button);
      animator.addAnimation(new ExpandAnimation(333, rectFrom, rectTo));
      rectFrom.setX(rectFrom.getX() + 0.15f);
      rectTo.setX(rectTo.getX() + 0.15f);
      animator.startAnimations();
    }
  }

  protected void hideApplications() {
    applicationsVisible = false;
    for (RatButton appButton : appButtons) {
      ws.removeWindow(appButton);
    }
  }

  public void addApplication(IApplication application) {
    applications.add(application);
  }

  @Override
  public List<IApplication> listApplications() {
    return Collections.unmodifiableList(applications);
  }

  @Override
  public void setWindowSystem(WindowSystem ws) {
    this.ws = ws;
  }
}
package windowManager.taskbar;

import java.util.List;
import windowManager.WindowManager;
import windowSystem.SimpleWindow;
import windowSystem.WindowSystem;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public interface ITaskbar {

  List<IApplication> listApplications();

  SimpleWindow asSimpleWindow();

  void setWindowManager(WindowManager wm);

  void setWindowSystem(WindowSystem ws);
}

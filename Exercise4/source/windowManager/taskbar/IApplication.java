package windowManager.taskbar;

import windowManager.TopWindow;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public interface IApplication {
  String getName();
  TopWindow newInstance();
}

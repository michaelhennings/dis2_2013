package windowManager.rat.events;

/**
 * Event listener for mouse events.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public interface IRatMouseListener extends IRatEventListener {

  void mouseClicked(RatMouseEvent event);

  void mouseMoved(RatMouseEvent event);

  void mouseEntered(RatMouseEvent event);

  void mouseExited(RatMouseEvent event);

  void mousePressed(RatMouseEvent event);

  void mouseReleased(RatMouseEvent event);
}

package windowManager.rat.events;

/**
 * Abstract implementation of the
 * <code>IRatMouseListener</code> interface. Provides no functionality, but
 * makes the code easier to read if not all functions are desired.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public abstract class AbstractRatMouseListener implements IRatMouseListener {

  @Override
  public void mouseClicked(RatMouseEvent event) {
  }

  @Override
  public void mouseMoved(RatMouseEvent event) {
  }

  @Override
  public void mouseExited(RatMouseEvent event) {
  }

  @Override
  public void mouseEntered(RatMouseEvent event) {
  }

  @Override
  public void mousePressed(RatMouseEvent event) {
  }

  @Override
  public void mouseReleased(RatMouseEvent event) {
  }

  @Override
  public void mouseDragged(RatMouseEvent event) {
  }
}
package windowManager.rat.events;

import windowSystem.SimpleWindow;

/**
 * Common properties for all RAT events like parent event (if any) and the
 * widget the event originated from.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public abstract class RatEvent {

  private final RatEvent parent;

  private final SimpleWindow source;

  public RatEvent() {
    this(null, null);
  }

  public RatEvent(SimpleWindow source) {
    this(null, source);
  }

  public RatEvent(RatEvent parent, SimpleWindow source) {
    this.parent = parent;
    this.source = source;
  }

  public SimpleWindow getSource() {
    return source;
  }

  public RatEvent getParent() {
    return parent;
  }
}

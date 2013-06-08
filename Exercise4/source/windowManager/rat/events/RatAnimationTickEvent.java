package windowManager.rat.events;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatAnimationTickEvent {
  public final long tickTime;

  public RatAnimationTickEvent() {
    tickTime = System.currentTimeMillis();
  }

  /**
   * Time at which the tick was produced.
   * 
   * @return Timestamp of this event, in milliseconds.
   */
  public long getTickTime() {
    return tickTime;
  }
}
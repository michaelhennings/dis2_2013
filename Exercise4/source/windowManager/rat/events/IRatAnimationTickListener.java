package windowManager.rat.events;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public interface IRatAnimationTickListener extends IRatEventListener {
  void tick(RatAnimationTickEvent event);
}

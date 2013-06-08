package windowManager.rat.animation;

import windowManager.rat.RatWidget;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public abstract class Animation<T extends RatWidget> {
  private int duration;

  public Animation(int duration) {
    this.duration = duration;
  }

  public abstract void animate(T widget, double progress);
  
  public int getDuration() {
    return duration;
  }
  
}

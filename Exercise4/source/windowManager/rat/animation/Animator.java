package windowManager.rat.animation;

import java.util.ArrayList;
import java.util.List;
import windowManager.rat.RatWidget;
import windowManager.rat.events.IRatAnimationTickListener;
import windowManager.rat.events.RatAnimationTickEvent;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class Animator<T extends RatWidget> {

  private final T widget;

  private final List<Animation<T>> animations = new ArrayList<Animation<T>>();

  private boolean animationStarted = false;

  private long animationStartTime;

  public Animator(T widget) {
    this.widget = widget;
    widget.addTickListener(new IRatAnimationTickListener() {
      @Override
      public void tick(RatAnimationTickEvent event) {
        if (animationStarted) {
          if (animationStartTime == 0) {
            animationStartTime = event.tickTime;
            fireAnimationStartedEvent();
          }
          animateStep(event.tickTime);
        }
      }
    });
  }

  public void addAnimation(Animation<T> animation) {
    animations.add(animation);
  }

  public void startAnimations() {
    animationStarted = true;
  }

  public void abortAnimation() {
  }

  protected void animateStep(long currentTime) {
    boolean allFinished = true;
    for (Animation animation : animations) {
      double progress = (currentTime - animationStartTime) / (double) animation.getDuration();
      if (progress > 1) {
        progress = 1;
      } else {
        allFinished = false;
      }
      animation.animate(widget, progress);
    }
    if (allFinished) {
      animationStarted = false;
      animationStartTime = 0;
      fireAnimationStoppedEvent();
    }
    widget.requestRepaint();
  }

  protected void fireAnimationStartedEvent() {
  }

  protected void fireAnimationStoppedEvent() {
  }

  public void reset() {
    if (animationStarted) {
      abortAnimation();
    }
    animations.clear();
  }
}
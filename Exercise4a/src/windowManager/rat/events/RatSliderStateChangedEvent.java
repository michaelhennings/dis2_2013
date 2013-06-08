package windowManager.rat.events;

import windowSystem.SimpleWindow;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatSliderStateChangedEvent extends RatEvent {

  private final float value;

  public RatSliderStateChangedEvent(float value) {
    this.value = value;
  }

  public RatSliderStateChangedEvent(float value, SimpleWindow source) {
    super(source);
    this.value = value;
  }

  public RatSliderStateChangedEvent(float value, RatEvent parent, SimpleWindow source) {
    super(parent, source);
    this.value = value;
  }

  public float getValue() {
    return value;
  }
}

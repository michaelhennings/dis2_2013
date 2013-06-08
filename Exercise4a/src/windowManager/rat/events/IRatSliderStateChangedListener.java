package windowManager.rat.events;

/**
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public interface IRatSliderStateChangedListener extends IRatEventListener {

  void sliderStateChanged(RatSliderStateChangedEvent event);
}

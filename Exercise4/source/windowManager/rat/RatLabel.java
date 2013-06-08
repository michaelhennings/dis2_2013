package windowManager.rat;

import java.awt.Color;
import windowManager.rat.animation.Animation;
import windowManager.rat.animation.Animator;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;

/**
 * A simple label that shows a centered text.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatLabel extends RatWidget {

  private static final int ANIMATION_DURATION = 333;

  private class TextSlideAnimation extends Animation<RatLabel> {

    private final String oldText;

    private final String newText;

    public TextSlideAnimation(String oldText, String newText) {
      super(ANIMATION_DURATION);
      this.oldText = oldText;
      this.newText = newText;
    }

    @Override
    public void animate(RatLabel widget, double progress) {
      if (progress < .5) {
        animationText = oldText;
        animationOffset = (float) (progress * 2);
      } else {
        animationText = newText;
        animationOffset = (float) ((progress-1) * 2);
      }
    }
  }

  private Animator<RatLabel> animator = new Animator<RatLabel>(this);

  private String text;

  private String animationText;

  private float animationOffset;

  private Color foreground = Color.BLACK;

  private Color background = Color.WHITE;

  public RatLabel(RectangleF windowArea, String text) {
    super(windowArea);
    this.text = text;
    this.animationText = text;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    animator.abortAnimation();
    animator.reset();
    animator.addAnimation(new TextSlideAnimation(this.text, text));
    this.text = text;
    animator.startAnimations();
  }

  @Override
  public void setBackground(Color background) {
    this.background = background;
    requestRepaint();
  }

  @Override
  public Color getBackground() {
    return background;
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    context.setColor(background);
    context.fillRect(RectangleF.FULL);
    context.setColor(foreground);
    context.drawStringCentered(new PointF(animationOffset+0.5f, 0.5f), animationText);
    super.handlePaint(context);
  }
}
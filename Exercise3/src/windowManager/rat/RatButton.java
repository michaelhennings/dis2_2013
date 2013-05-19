package windowManager.rat;

import java.awt.Color;
import windowManager.rat.borders.IBorder;
import windowManager.rat.borders.InternalLineBorder;
import windowManager.rat.borders.LineBorder;
import windowManager.rat.events.AbstractRatMouseListener;
import windowManager.rat.events.RatMouseEvent;
import windowSystem.DrawingContext;
import windowSystem.RectangleF;

/**
 * A simple button with two colors and border states.
 *
 * @author Michael Hennings
 * @author Andre Tebart
 * @author Jonas Strohmeier
 */
public class RatButton extends RatLabel {

  private IBorder normalBorder = new LineBorder(1, Color.BLACK);

  private IBorder hoverBorder = new LineBorder(1, Color.GREEN);

  private Color activeBackground = Color.LIGHT_GRAY;

  private Color normalBackground = Color.WHITE;

  // TODO: Shift internal border into a CompoundBorder
  private IBorder internalNormalBorder = new InternalLineBorder(1, Color.BLACK, 0.01f, 0.1f);

  private IBorder internalHoverBorder = new InternalLineBorder(1, Color.GREEN, 0.01f, 0.1f);

  private IBorder internalBorder = new InternalLineBorder(1, Color.BLACK, 0.01f, 0.1f);

  public RatButton(RectangleF windowArea, String label) {
    super(windowArea, label);
    setBorder(normalBorder);
    addMouseListener(new AbstractRatMouseListener() {
      @Override
      public void mouseMoved(RatMouseEvent event) {
      }

      @Override
      public void mouseClicked(RatMouseEvent event) {
        animateClick();
      }

      @Override
      public void mouseEntered(RatMouseEvent event) {
        internalBorder = internalHoverBorder;
        RatButton.this.setBorder(hoverBorder);
      }

      @Override
      public void mouseExited(RatMouseEvent event) {
        internalBorder = internalNormalBorder;
        RatButton.this.setBorder(normalBorder);
      }

      @Override
      public void mousePressed(RatMouseEvent event) {
        RatButton.super.setBackground(activeBackground);
      }

      @Override
      public void mouseReleased(RatMouseEvent event) {
        RatButton.super.setBackground(normalBackground);
      }
    });
  }

  @Override
  public void setBackground(Color background) {
    super.setBackground(background);
    this.normalBackground = background;
  }

  public void setActiveBackground(Color activeBackground) {
    this.activeBackground = activeBackground;
    requestRepaint();
  }

  private void animateClick() {
    // TODO
  }

  @Override
  protected void handlePaint(DrawingContext context) {
    super.handlePaint(context);
    internalBorder.draw(context);
  }
}
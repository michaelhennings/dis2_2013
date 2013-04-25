package assignment2.bonus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Solution for Assignment 2 (Bonus)
 */
public class BonusApp extends JPanel {

  /**
   * Shortest length the horizontal line may be adjusted to (in pixels).
   */
  private static final int MIN_LINE_LENGTH = 10;

  /**
   * Default length to line will be drawn with after start / reset.
   */
  public static final int LINE_DEFAULT_LENGTH = 100;

  /**
   * Internal state representing the length of the horizontal line.
   */
  private int lineLength = LINE_DEFAULT_LENGTH;

  /**
   * Create a new panel that draws a horizontal line.
   */
  public BonusApp() {
    // *** Setup Layout ***

    setLayout(null);
    setOpaque(true);
    setPreferredSize(new Dimension(800, 600));

    // *** Add reset button ***

    JButton resetButton = new JButton("Reset line length");
    resetButton.setLocation(40, 60);
    resetButton.setSize(resetButton.getPreferredSize());

    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lineLength = LINE_DEFAULT_LENGTH;
        repaint();
      }
    });

    add(resetButton);

    // *** Install mouse listeners ***

    MouseAdapter ma = new MouseAdapter() {
      private Point dragStart = null;

      private int startLineLength = lineLength;

      @Override
      public void mousePressed(MouseEvent e) {
        // Set start point and length
        dragStart = e.getPoint();
        startLineLength = lineLength;
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        // Reset start point
        dragStart = null;
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        // Adjust line length
        if (dragStart != null) {
          lineLength = startLineLength + (dragStart.y - e.getPoint().y);

          // Ensure line gets not too small
          lineLength = Math.max(MIN_LINE_LENGTH, lineLength);

          // Request repaint
          repaint();
        }
      }
    };
    addMouseListener(ma);
    addMouseMotionListener(ma);
  }
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.drawString("Click anywhere on the panel and move cursor up/down to adjust size!", 40, 20);
    g.setColor(Color.black);
    g.drawLine(40, 40, 40 + Math.max(MIN_LINE_LENGTH, lineLength), 40);
  }

  /**
   * Executes the application
   * 
   * @param args command line arguments
   */
  public static void main(String[] args) {
    BonusApp panel = new BonusApp();

    final JFrame window = new JFrame("Bonus App");
    window.add(panel);
    window.pack();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        window.setVisible(true);
      }
    });
  }
}
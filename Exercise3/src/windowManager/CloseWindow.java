/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 *
 * @author Andre
 */
public class CloseWindow extends SimpleWindow {
    
    public CloseWindow(RectangleF windowArea){
        super(windowArea);
    }
    
    @Override
    protected void handlePaint(DrawingContext context){
        context.setColor(Color.red);
        context.fillRect(new RectangleF(0.0f, 0.0f, 1.0f, 1.0f));
        context.setColor(Color.white);
        context.drawLine(new PointF(.1f, .1f), new PointF(.9f, .9f));
        context.drawLine(new PointF(.9f, .1f), new PointF(.1f, .9f));
    }
}

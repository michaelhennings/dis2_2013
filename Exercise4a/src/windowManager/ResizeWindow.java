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
public class ResizeWindow extends SimpleWindow {
    public ResizeWindow(RectangleF windowArea){
        super(windowArea);
    }
    
    @Override
    protected void handlePaint(DrawingContext context){
        context.setColor(Color.gray);
        context.drawLine(new PointF(.0f, 1.0f), new PointF(1.0f, .0f));
        context.drawLine(new PointF(.5f, 1.0f), new PointF(1.0f, .5f));
        context.drawLine(new PointF(.8f, 1.0f), new PointF(1.0f, .8f));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 * A window that changes it's color when it is clicked.
 */
public class ColorWindow extends SimpleWindow {
    private boolean firstColorSet;
    private Color color1;
    private Color color2;
    
    public ColorWindow(RectangleF drawingArea, Color color1, Color color2){
        super(drawingArea);
        firstColorSet = true;
        this.color1 = color1;
        this.color2 = color2;
    }
    
    @Override
    public void handlePaint(DrawingContext context){
        if(firstColorSet)
            context.setColor(color1);
        else
            context.setColor(color2);
        context.fillRect(new RectangleF(.0f, .0f, 1.0f, 1.0f));
    }
    
    @Override
    public void handleMouseClicked(PointF point){
        firstColorSet = !firstColorSet;            
        requestRepaint();
    }
}

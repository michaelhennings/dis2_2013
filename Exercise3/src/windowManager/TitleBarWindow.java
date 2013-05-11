/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.awt.Color;
import windowSystem.DrawingContext;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 *
 * @author Andre
 */
public class TitleBarWindow extends SimpleWindow {
    private String title;
    public TitleBarWindow(RectangleF drawingArea, String title){
        super(drawingArea);
        this.title = title;
    }
    
    @Override
    public void handlePaint(DrawingContext context){
        context.setColor(Color.blue);
        context.fillRect(new RectangleF(.0f, .0f, 1.0f, 1.0f));
    }
}

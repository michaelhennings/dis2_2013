/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowSystem;

/**
 *
 * @author Andre
 */
public class RootWindow extends SimpleWindow {
    private DrawingContext drawingContext;
    
    RootWindow(RectangleF desktopArea, DrawingContext drawingContext){
        super(desktopArea);
        this.drawingContext = drawingContext;
    }
    
    DrawingContext getDrawingContext(){
        return drawingContext;
    }
}

package ws;

import java.awt.Rectangle;


/**
 * Basic top-class that manages a single window on the desktop.
 */
public class SimpleWindow {
    private RectangleF desktopArea;
    private IPaintCallback paintCallback;
    
    SimpleWindow(RectangleF desktopArea){
        this.desktopArea = desktopArea;
    }
    
    void handlePaint(DrawingContext context){
        context.setDrawingArea(desktopArea);
        if(paintCallback != null)
            paintCallback.handleDraw(context);
    }
    
    public void setPaintCallback(IPaintCallback paintCallback){
        this.paintCallback = paintCallback;
    }
}

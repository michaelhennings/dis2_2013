package windowSystem;


/**
 * Basic top-class that manages a single window on the desktop.
 */
public class SimpleWindow {
    RectangleF desktopArea;
    private IPaintCallback paintCallback;
    private IMouseClickedCallback mouseClickedCallback;
    
    SimpleWindow(RectangleF desktopArea){
        this.desktopArea = desktopArea;
    }
    
    void handlePaint(DrawingContext context){
        context.init(desktopArea);
        if(paintCallback != null)
            paintCallback.handleDraw(context);
    }
    
    public void setPaintCallback(IPaintCallback paintCallback){
        this.paintCallback = paintCallback;
    }
    
    void handleMouseClicked(Point<Float> point){
        if(mouseClickedCallback != null)
            mouseClickedCallback.handleMouseClicked(point);
    }
    
    public void setMouseClickedCallback(IMouseClickedCallback mouseClickedCallback){
        this.mouseClickedCallback = mouseClickedCallback;
    }
}

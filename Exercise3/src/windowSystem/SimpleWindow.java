package windowSystem;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;


/**
 * Basic top-class that manages a single window on the desktop.
 */
public class SimpleWindow {
    /**
     * The window area of this window defined in terms of it's parents
     * coordinate system.
     */
    RectangleF windowArea;
    
    /**
     * The actual pixel area on the desktop which is covered by this window.
     * This is used by the drawing context to make drawing easier.
     * Needs to be recalculated whenever the size or position of the window
     * changes.
     */
    Rectangle desktopArea;
    
    SimpleWindow parentWindow;
    List<SimpleWindow> children;
    DrawingContext drawingContext;
    
    public SimpleWindow(RectangleF windowArea){
        this.windowArea = windowArea;
        
        //The desktop area is first correctly initialized when the window is 
        //registered with the window system.
        desktopArea = new Rectangle();
        children = new ArrayList<SimpleWindow>();
        parentWindow = null;
    }
    
    void internalInit(DrawingContext drawingContext){
        this.drawingContext = drawingContext;
        recalculateDesktopArea();
    }
    
    /**
     * Internal paint method. For root windows this is called by the window
     * system.
     */
    void internalHandlePaint(){
        //Draw this window first and then it's children
        drawingContext.init();
        handlePaint(drawingContext);
        for(SimpleWindow child : children){
            child.internalHandlePaint();
        }
    }
    
    /**
     * All drawing commands should be called here. This method is supposed
     * to be overwritten by subclasses.
     * @param context 
     */
    protected void handlePaint(DrawingContext context){
    }
    
    void internalHandleMouseClicked(PointF point){
        //Traverse the window list back to front
        for(int i = children.size() - 1; i >= 0; i--){
            SimpleWindow child = children.get(i);
            if(child.windowArea.contains(point)){
                PointF relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        child.windowArea);
                child.internalHandleMouseClicked(relativePoint);
                return;
            }
        }
        handleMouseClicked(point);
    }
    
    protected void handleMouseClicked(PointF point){
        
    }
    
    DrawingContext getDrawingContext(){
        return drawingContext;
    }
    
    void recalculateDesktopArea(){
        if(parentWindow == null){
            desktopArea.x = (int)(windowArea.getX() * drawingContext.getDesktopWidth());
            desktopArea.y = (int)(windowArea.getY() * drawingContext.getDesktopHeight());
            desktopArea.width = (int)(windowArea.getWidth() * drawingContext.getDesktopWidth());
            desktopArea.height = (int)(windowArea.getHeight() * drawingContext.getDesktopHeight());
        }
        else{
            desktopArea.x = (int)(parentWindow.desktopArea.x + windowArea.getX() * parentWindow.desktopArea.getWidth());
            desktopArea.y = (int)(parentWindow.desktopArea.y + windowArea.getY() * parentWindow.desktopArea.getHeight());
            desktopArea.width = (int)(windowArea.getWidth() * parentWindow.desktopArea.getWidth());
            desktopArea.height = (int)(windowArea.getHeight() * parentWindow.desktopArea.getHeight());
        }
    }
    
    Rectangle getDesktopArea(){
        return desktopArea;
    }
    
    RectangleF getWindowArea(){
        return windowArea;
    }
    
    public void requestRepaint(){
        drawingContext.requestRepaint();
    }
    
    public void addChild(SimpleWindow simpleWindow){
        simpleWindow.parentWindow = this;
        children.add(simpleWindow);
    }
}

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
    SimpleWindow mousePriorityWindow;
    
    IPaintCallback paintCallback;
    IMouseCallback mouseClickedCallback;
    IMouseCallback mouseMovedCallback;
    IMouseCallback mouseDraggedCallback;
    IMouseCallback mousePressedCallback;
    IMouseCallback mouseReleasedCallback;
    
    public SimpleWindow(RectangleF windowArea){
        this.windowArea = windowArea;
        
        //The desktop area is first correctly initialized when the window is 
        //registered with the window system.
        desktopArea = new Rectangle();
        children = new ArrayList<SimpleWindow>();
        parentWindow = null;
        mousePriorityWindow = null;
        
        paintCallback = null;
        mouseClickedCallback = null;
        mouseMovedCallback = null;
        mouseDraggedCallback = null;
        mousePressedCallback = null;
        mouseReleasedCallback = null;
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
        if(paintCallback != null){
            paintCallback.handleDraw(drawingContext);
        }
    }
    
    void internalHandleMouseClicked(PointF point){
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseClicked(relativePoint);
            return;
        }
        //Traverse the window list back to front
        for(int i = children.size() - 1; i >= 0; i--){
            SimpleWindow child = children.get(i);
            if(child.windowArea.contains(point)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        child.windowArea);
                child.internalHandleMouseClicked(relativePoint);
                return;
            }
        }
        handleMouseClicked(point);
    }
    
    protected void handleMouseClicked(PointF point){
        if(mouseClickedCallback != null)
            mouseClickedCallback.handleMouse(point);
    }
    
    void internalHandleMouseMoved(PointF point){
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseMoved(relativePoint);
            return;
        }
        //Traverse the window list back to front
        for(int i = children.size() - 1; i >= 0; i--){
            SimpleWindow child = children.get(i);
            if(child.windowArea.contains(point)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        child.windowArea);
                child.internalHandleMouseMoved(relativePoint);
                return;
            }
        }
        handleMouseMoved(point);
    }
    
    protected void handleMouseMoved(PointF point){
        if(mouseMovedCallback != null){
            mouseMovedCallback.handleMouse(point);
        }
    }
    
    void internalHandleMouseDragged(PointF point){
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseDragged(relativePoint);
            return;
        }
        //Traverse the window list back to front
        for(int i = children.size() - 1; i >= 0; i--){
            SimpleWindow child = children.get(i);
            if(child.windowArea.contains(point)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        child.windowArea);
                child.internalHandleMouseDragged(relativePoint);
                return;
            }
        }
        handleMouseDragged(point);
    }
    
    protected void handleMouseDragged(PointF point){
        if(mouseDraggedCallback != null){
            mouseDraggedCallback.handleMouse(point);
        }
    }
    
    void internalHandleMousePressed(PointF point){
        //Traverse the window list back to front
        for(int i = children.size() - 1; i >= 0; i--){
            SimpleWindow child = children.get(i);
            if(child.windowArea.contains(point)){
                PointF relativePoint = 
                        CoordinateMath.transformToRelativePoint(point, 
                        child.windowArea);
                mousePriorityWindow = child;
                child.internalHandleMousePressed(relativePoint);
                return;
            }
        }
        handleMousePressed(point);
    }
    
    protected void handleMousePressed(PointF point){
        if(mousePressedCallback != null){
            mousePressedCallback.handleMouse(point);
        }
    }
    
    void internalHandleMouseReleased(PointF point){
        if(mousePriorityWindow != null)
            mousePriorityWindow.internalHandleMouseReleased(point);
        else
           handleMouseReleased(point);
        mousePriorityWindow = null;
    }
    
    protected void handleMouseReleased(PointF point){
        if(mouseReleasedCallback != null){
            mouseReleasedCallback.handleMouse(point);
        }
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
        
        for(SimpleWindow child : children){
            child.recalculateDesktopArea();
        }
    }
    
    Rectangle getDesktopArea(){
        return desktopArea;
    }
    
    public void requestRepaint(){
        drawingContext.requestRepaint();
    }
    
    public void addChild(SimpleWindow simpleWindow){
        simpleWindow.parentWindow = this;
        children.add(simpleWindow);
    }
    
    public void moveTo(PointF position){
        windowArea.setX(position.getX());
        windowArea.setY(position.getY());
        recalculateDesktopArea();
    }
    
    public void setPaintCallback(IPaintCallback paintCallback){
        this.paintCallback = paintCallback;
    }
    
    public void setMouseClickedCallback(IMouseCallback mouseClickedCallback){
        this.mouseClickedCallback = mouseClickedCallback;
    }
    
    public void setMouseMovedCallback(IMouseCallback mouseMovedCallback){
        this.mouseMovedCallback = mouseMovedCallback;
    }
    
    public void setMouseDraggedCallback(IMouseCallback mouseDraggedCallback){
        this.mouseDraggedCallback = mouseDraggedCallback;
    }
    
    public void setMousePressedCallback(IMouseCallback mousePressedCallback){
        this.mousePressedCallback = mousePressedCallback;
    }
    
    public void setMouseReleasedCallback(IMouseCallback mouseReleasedCallback){
        this.mouseReleasedCallback = mouseReleasedCallback;
    }
    
    public RectangleF getWindowArea(){
        return new RectangleF(windowArea);
    }
    
    public void resize(float width, float height){
        if(parentWindow == null)
        {
            if(width < .02f)
                width = .02f;
            if(height < .02f)
                height = .02f;
            
        }
        windowArea.setWidth(width);
        windowArea.setHeight(height);
        recalculateDesktopArea();
    }
}

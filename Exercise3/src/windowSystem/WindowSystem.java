package windowSystem;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import rwth.hci.Graphics.GraphicsEventSystem;

public class WindowSystem extends GraphicsEventSystem {

    /**
     * Width of the virtual desktop.
     */
    private final int width;
    /**
     * Height of the virtual desktop.
     */
    private final int height;
    /**
     * Maintains a list of windows that belong to this desktop. The order
     * represents the z-index of each window: the lower the z-index, the closer
     * will the window will be up-front in the list.
     */
    private final List<SimpleWindow> windowList;

    /**
     * Create a new virtual 'deskop' with the given (fixed) dimension.
     *
     * @param width Width of the window in px.
     * @param height Height of the window in px.
     */
    
    private SimpleWindow mousePriorityWindow;
    public WindowSystem(int width, int height) {
        super(width, height);
        this.windowList = new ArrayList<SimpleWindow>();
        this.width = width;
        this.height = height;
        this.setTitle("Desktop");
        mousePriorityWindow = null;
 
        // *** Allow the window to be closed ***

        WindowAdapter disposeOnClose = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowSystem.this.dispose();
            }
        };
        addWindowListener(disposeOnClose);
    }

    /**
     * Convert device independent abstract coordinates to desktop coordinates.
     *
     * @param abstractCoord Abstract coordinate.
     * @return Desktop coordinate.
     */
    Point abstractToDesktopCoord(PointF abstractCoord) {
        int x = (int) (width * abstractCoord.getX());
        int y = (int) (height * abstractCoord.getY());
        return new Point(x, y);
    }
    
    PointF desktopToAbstractCoord(Point desktopCoord) {
        float x = (float)desktopCoord.getX()/(float)width;
        float y = (float)desktopCoord.getY()/(float)height;
        return new PointF(x, y);
    }
    
    Rectangle abstractToDesktopRectangle(RectangleF abstractRectangle){
        float abstractX = abstractRectangle.getX();
        float abstractY = abstractRectangle.getY();
        Point desktopPoint = abstractToDesktopCoord(new PointF(abstractX, abstractY));
        
        int desktopWidth = (int) (width * abstractRectangle.getWidth());
        int desktopHeight = (int) (height * abstractRectangle.getHeight());
        
        return new Rectangle(desktopPoint.x, desktopPoint.y, desktopWidth, desktopHeight);
    }

    /**
     * Draw the background. Currently its a simple line like a scar. Might
     * evolve into something more complex, like a picture or so.
     */
    private void drawBackground() {
        setColor(Color.CYAN);
        fillRect(0, 0, width, height);
    }

    /**
     * Handles the painting of the desktop, i.e. the background along with all
     * (visible) windows on top of it.
     */
    @Override
    public void handlePaint() {
        drawBackground();
        for (SimpleWindow window : windowList) {
            window.internalHandlePaint();
        }
    }
    
    @Override
    public void handleMouseClicked(int x, int y){
        //Transform point to abstract coordinates
        PointF abstractPoint = 
                desktopToAbstractCoord(new Point(x, y));
        
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseClicked(relativePoint);
            return;
        }
        
        //Traverse the window list back to front
        for(int i = windowList.size() - 1; i >= 0; i--){
            SimpleWindow currentWindow = windowList.get(i);
            if(currentWindow.windowArea.contains(abstractPoint)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        currentWindow.windowArea);
                currentWindow.internalHandleMouseClicked(relativePoint);
                return;
            }
        }
    }
    
    @Override
    public void handleMouseMoved(int x, int y){
        //Transform point to abstract coordinates
        PointF abstractPoint = 
                desktopToAbstractCoord(new Point(x, y));
        
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseMoved(relativePoint);
            return;
        }
        
        //Traverse the window list back to front
        for(int i = windowList.size() - 1; i >= 0; i--){
            SimpleWindow currentWindow = windowList.get(i);
            if(currentWindow.windowArea.contains(abstractPoint)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        currentWindow.windowArea);
                currentWindow.internalHandleMouseMoved(relativePoint);
                return;
            }
        }
    }
    
    @Override
    public void handleMouseDragged(int x, int y){
        //Transform point to abstract coordinates
        PointF abstractPoint = 
                desktopToAbstractCoord(new Point(x, y));
        
        PointF relativePoint = null;
        if(mousePriorityWindow != null){
            relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        mousePriorityWindow.windowArea);
            mousePriorityWindow.internalHandleMouseDragged(relativePoint);
            return;
        }
        
        //Traverse the window list back to front
        for(int i = windowList.size() - 1; i >= 0; i--){
            SimpleWindow currentWindow = windowList.get(i);
            if(currentWindow.windowArea.contains(abstractPoint)){
                relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        currentWindow.windowArea);
                currentWindow.internalHandleMouseDragged(relativePoint);
                return;
            }
        }
    }
    
    @Override
    public void handleMousePressed(int x, int y){
        //Transform point to abstract coordinates
        PointF abstractPoint = 
                desktopToAbstractCoord(new Point(x, y));
        
        //Traverse the window list back to front
        for(int i = windowList.size() - 1; i >= 0; i--){
            SimpleWindow currentWindow = windowList.get(i);
            if(currentWindow.windowArea.contains(abstractPoint)){
                PointF relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        currentWindow.windowArea);
                mousePriorityWindow = currentWindow;
                currentWindow.internalHandleMousePressed(relativePoint);
                return;
            }
        }
    }
    
    @Override
    public void handleMouseReleased(int x, int y){
        if(mousePriorityWindow == null){//Theoretically this should not happen, but just in case
            return;
        }
        //Transform point to abstract coordinates
        PointF abstractPoint = 
                desktopToAbstractCoord(new Point(x, y));
        
        mousePriorityWindow.internalHandleMouseReleased(abstractPoint);
        mousePriorityWindow = null;
    }

    public void drawLine(float StartX, float StartY, float EndX, float EndY) {
        PointF abstractStartPoint =
                new PointF(StartX, StartY);
        PointF abstractEndPoint =
                new PointF(EndX, EndY);

        Point desktopStartPoint =
                abstractToDesktopCoord(abstractStartPoint);
        Point desktopEndPoint =
                abstractToDesktopCoord(abstractEndPoint);

        this.drawLine(desktopStartPoint.x, desktopStartPoint.y,
                desktopEndPoint.x, desktopEndPoint.y);
    }

    /**
     * Add a window to the desktop.
     *
     * @param window The window to be added
     */
    public void addWindow(SimpleWindow window) {
        if (window == null) {
            throw new NullPointerException("window must not be null!");
        }
        
        initNewWindow(window);
        windowList.add(window);
        requestRepaint(new Rectangle(0, 0, width, height));
    }

    void initNewWindow(SimpleWindow window){
        //Give the window a drawing context
        window.internalInit(new DrawingContext(this, window));
        //Init it's children
        for(SimpleWindow child : window.children){
            initNewWindow(child);
        }
    }
    /**
     * Remove a window from the desktop.
     *
     * @param window The window to be removed
     */
    public void removeWindow(SimpleWindow window) {
        windowList.remove(window);
        requestRepaint(new Rectangle(0,0,width,height));
    }
    
    public int getDesktopWidth(){
        return width;
    }
    
    public int getDesktopHeight(){
        return height;
    }
    
    public void moveWindowToTop(SimpleWindow window){
        windowList.remove(window);
        addWindow(window);
        
    }
}

package windowSystem;

import java.awt.Color;
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
    private final List<RootWindow> windowList;

    /**
     * Create a new virtual 'deskop' with the given (fixed) dimension.
     *
     * @param width Width of the window in px.
     * @param height Height of the window in px.
     */
    public WindowSystem(int width, int height) {
        super(width, height);
        this.windowList = new ArrayList<RootWindow>();
        this.width = width;
        this.height = height;
        this.setTitle("Desktop");
 
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
    Point<Integer> abstractToDesktopCoord(Point<Float> abstractCoord) {
        int x = (int) (width * abstractCoord.getX());
        int y = (int) (height * abstractCoord.getY());
        return new Point<Integer>(x, y);
    }
    
    Point<Float> desktopToAbstractCoord(Point<Integer> desktopCoord) {
        float x = (float)desktopCoord.getX()/(float)width;
        float y = (float)desktopCoord.getY()/(float)height;
        return new Point<Float>(x, y);
    }
    
    Rectangle abstractToDesktopRectangle(RectangleF abstractRectangle){
        float abstractX = abstractRectangle.getX();
        float abstractY = abstractRectangle.getY();
        Point<Integer> desktopPoint = abstractToDesktopCoord(new Point<Float>(abstractX, abstractY));
        
        int desktopWidth = (int) (width * abstractRectangle.getWidth());
        int desktopHeight = (int) (height * abstractRectangle.getHeight());
        
        return new Rectangle(desktopPoint.getX(), desktopPoint.getY(), desktopWidth, desktopHeight);
    }

    /**
     * Draw the background. Currently its a simple line like a scar. Might
     * evolve into something more complex, like a picture or so.
     */
    private void drawBackground() {
        setColor(Color.CYAN);
        fillRect(0, 0, width, height);
        setColor(Color.red);
        drawLine(0.2f, 0.3f, .8f, .7f);
    }

    /**
     * Handles the painting of the desktop, i.e. the background along with all
     * (visible) windows on top of it.
     */
    @Override
    public void handlePaint() {
        System.out.println("Yes");
        drawBackground();
        for (RootWindow window : windowList) {
            window.handlePaint(window.getDrawingContext());
        }
    }
    
    @Override
    public void handleMouseClicked(int x, int y){
        //Transform point to abstract coordinates
        Point<Float> abstractPoint = 
                desktopToAbstractCoord(new Point<Integer>(x, y));
        
        
        //Traverse the window list back to front
        for(int i = windowList.size() - 1; i >= 0; i--){
            SimpleWindow currentWindow = windowList.get(i);
            if(currentWindow.desktopArea.contains(abstractPoint)){
                Point<Float> relativePoint = 
                        CoordinateMath.transformToRelativePoint(abstractPoint, 
                        currentWindow.desktopArea);
                currentWindow.handleMouseClicked(relativePoint);
                return;
            }
        }
    }

    public void drawLine(float StartX, float StartY, float EndX, float EndY) {
        Point<Float> abstractStartPoint =
                new Point<Float>(StartX, StartY);
        Point<Float> abstractEndPoint =
                new Point<Float>(EndX, EndY);

        Point<Integer> desktopStartPoint =
                abstractToDesktopCoord(abstractStartPoint);
        Point<Integer> desktopEndPoint =
                abstractToDesktopCoord(abstractEndPoint);

        this.drawLine(desktopStartPoint.getX(), desktopStartPoint.getY(),
                desktopEndPoint.getX(), desktopEndPoint.getY());
    }

    /**
     * Add a window to the desktop.
     *
     * @param window The window to be added
     */
    public void addWindow(RootWindow window) {
        if (window == null) {
            throw new NullPointerException("window must not be null!");
        }
        windowList.add(window);
    }

    /**
     * Remove a window from the desktop.
     *
     * @param window The window to be removed
     */
    public void removeWindow(RootWindow window) {
        windowList.remove(window);
    }

    public SimpleWindow createRootWindow(RectangleF desktopArea) {
        RootWindow rootWindow = new RootWindow(desktopArea, new DrawingContext(this));
        addWindow(rootWindow);
        return rootWindow;
    }
}

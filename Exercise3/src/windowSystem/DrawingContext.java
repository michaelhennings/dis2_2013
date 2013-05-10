package windowSystem;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Window dependent drawing context.
 * This class abstracts away from the drawing commands which are provided by
 * the GraphicsEventSystem. The drawing commands are all relative to the window
 * for which the context is used. This class also makes it possible to store
 * a drawing state (current color, etc.) per window.
 */
public class DrawingContext {
    private WindowSystem windowSystem;
    private SimpleWindow window;
    
    private Color color;
    
    DrawingContext(WindowSystem windowSystem, SimpleWindow window){
        this.windowSystem = windowSystem;
        this.window = window;
        color = windowSystem.getBackground();
    }
    
    /**
     * Restores the values of this drawing context. This is called by the
     * window system when the context is switched.
     */
    void init(){
        windowSystem.setColor(color);
    }
    
    public void fillRect(RectangleF rectangle){
        int desktopX = (int)(window.getDesktopArea().x + rectangle.getX() * window.getDesktopArea().width);
        int desktopY = (int)(window.getDesktopArea().y + rectangle.getY() * window.getDesktopArea().height);
        int desktopWidth = (int)(window.getDesktopArea().getWidth() * rectangle.getWidth());
        int desktopHeight = (int)(window.getDesktopArea().getHeight() * rectangle.getHeight());
        
        windowSystem.fillRect(desktopX, desktopY, desktopWidth, desktopHeight);
    }
    
    public void drawLine(PointF start, PointF end){
        int desktopStartX = (int)(window.getDesktopArea().x + start.getX() * window.getDesktopArea().width);
        int desktopStartY = (int)(window.getDesktopArea().y + start.getY() * window.getDesktopArea().height);
        
        int desktopEndX = (int)(window.getDesktopArea().x + end.getX() * window.getDesktopArea().width);
        int desktopEndY = (int)(window.getDesktopArea().y + end.getY() * window.getDesktopArea().height);
        
        windowSystem.drawLine(desktopStartX, desktopStartY, desktopEndX, desktopEndY);
    }
    
    public void setColor(Color color){
        this.color = color;
        windowSystem.setColor(color);
    }
    
    public int getDesktopWidth(){
        return windowSystem.getDesktopWidth();
    }
    
    public int getDesktopHeight(){
        return windowSystem.getDesktopHeight();
    }
    
    void requestRepaint(){
        windowSystem.requestRepaint(new Rectangle(0, 0, getDesktopWidth(), getDesktopHeight()));
    }
}

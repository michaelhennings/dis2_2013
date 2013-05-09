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
    private RectangleF drawingArea;
    
    private Color color;
    
    DrawingContext(WindowSystem windowSystem){
        this.windowSystem = windowSystem;
        color = windowSystem.getBackground();
    }
    
    void init(RectangleF drawingArea){
        this.drawingArea = drawingArea;
        
        windowSystem.setColor(color);
    }
    
    public void fillRect(RectangleF rectangle){
        RectangleF abstractRectangle = transformToAbsoluteRect(rectangle);
        
        Rectangle desktopRectangle = 
                windowSystem.abstractToDesktopRectangle(abstractRectangle);
        
        windowSystem.fillRect(desktopRectangle.x, desktopRectangle.y, 
                desktopRectangle.width, desktopRectangle.height);
    }
    
    public void drawLine(Point<Float> start, Point<Float> end){
        Point<Float> absoluteStart = transformToAbsoluteCoord(start);
        Point<Float> absoluteEnd = transformToAbsoluteCoord(end);
        
        Point<Integer> desktopStart = windowSystem.abstractToDesktopCoord(absoluteStart);
        Point<Integer> desktopEnd = windowSystem.abstractToDesktopCoord(absoluteEnd);
        
        windowSystem.drawLine(desktopStart.getX(), desktopStart.getY(), desktopEnd.getX(), desktopEnd.getY());
    }
    
    public void setColor(Color color){
        this.color = color;
        windowSystem.setColor(color);
    }
    
    private Point<Float> transformToAbsoluteCoord(Point<Float> relativeCoord){
        float absX = drawingArea.getX() * (1.0f - relativeCoord.getX())
                + drawingArea.getRight() * relativeCoord.getX();
        float absY = drawingArea.getY() * (1.0f - relativeCoord.getY())
                + drawingArea.getBottom() * relativeCoord.getY();
        
        return new Point<Float>(absX, absY);
    }
    
    private RectangleF transformToAbsoluteRect(RectangleF relativeRect){
        Point<Float> absPoint = 
                transformToAbsoluteCoord(
                new Point<Float>(relativeRect.getX(), relativeRect.getY()));
        float absWidth = drawingArea.getWidth() * relativeRect.getWidth();
        float absHeight = drawingArea.getHeight() * relativeRect.getHeight();
        
        return new RectangleF(absPoint.getX(), absPoint.getY(), absWidth, 
                absHeight);
    }
}

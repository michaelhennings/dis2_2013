/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowSystem;

/**
 *
 * @author Andre
 */
public class CoordinateMath {
    public static Point<Float> transformToRelativePoint(Point<Float> point, RectangleF relativeSystem){
        float relativeX = (point.getX() - relativeSystem.getX())/relativeSystem.getWidth();
        float relativeY = (point.getY() - relativeSystem.getY())/relativeSystem.getHeight();
        
        return new Point<Float>(relativeX, relativeY);
    }
    
    public static Point<Float> transformToAbsolutePoint(Point<Float> point, RectangleF relativeSystem){
        float absX = relativeSystem.getX() * (1.0f - point.getX())
                + relativeSystem.getRight() * point.getX();
        float absY = relativeSystem.getY() * (1.0f - point.getY())
                + relativeSystem.getBottom() * point.getY();
        
        return new Point<Float>(absX, absY);
    }
}

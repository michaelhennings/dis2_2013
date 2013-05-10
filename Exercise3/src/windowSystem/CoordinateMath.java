package windowSystem;

/**
 * Provides functions to transform abstract coordinates from parent to child 
 * windows and vice versa.
 */
public class CoordinateMath {
    /**
     * Transforms a point from coordinate system A to coordinate system B where
     * B is defined in terms of A (Parent -> Child).
     */
    public static PointF transformToRelativePoint(PointF point, RectangleF relativeSystem){
        float relativeX = (point.getX() - relativeSystem.getX())/relativeSystem.getWidth();
        float relativeY = (point.getY() - relativeSystem.getY())/relativeSystem.getHeight();
        
        return new PointF(relativeX, relativeY);
    }
    
    /**
     * Transforms a point from coordinate system A to coordinate system B where
     * A is defined in terms of B (Child -> Parent).
     */
    public static PointF transformToAbsolutePoint(PointF point, RectangleF relativeSystem){
        float absX = relativeSystem.getX() * (1.0f - point.getX())
                + relativeSystem.getRight() * point.getX();
        float absY = relativeSystem.getY() * (1.0f - point.getY())
                + relativeSystem.getBottom() * point.getY();
        
        return new PointF(absX, absY);
    }
}

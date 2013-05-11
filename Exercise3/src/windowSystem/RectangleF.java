package windowSystem;

/**
 * A rectangle with float values.
 */
public class RectangleF {
    private float x;
    private float y;
    private float width;
    private float height;
    
    public RectangleF(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public RectangleF(RectangleF rectangle){
        this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public float getX(){
        return x;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getWidth(){
        return width;
    }
    
    public float getHeight(){
        return height;
    }
    
    public float getRight()
    {
        return x + width;
    }
    
    public float getBottom(){
        return y + height;
    }
    
    public boolean contains(PointF point){
        if(point.getX() < x || point.getX() > getRight())
            return false;
        if(point.getY() < y || point.getY() > getBottom())
            return false;
        
        return true;
    }
}

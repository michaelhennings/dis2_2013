package windowSystem;

/**
 * A rectangle with int values.
 */
public class RectangleI {
    private int x;
    private int y;
    private int width;
    private int height;
    
    public RectangleI(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public RectangleI(RectangleI rectangle){
        this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public int getX(){
        return x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public void setWidth(int width){
        this.width = width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setHeight(int height){
        this.height = height;
    }
    
    public int getRight()
    {
        return x + width;
    }
    
    public int getBottom(){
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

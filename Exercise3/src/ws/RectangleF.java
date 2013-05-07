/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

/**
 *
 * @author Andre
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
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import windowSystem.CoordinateMath;
import windowSystem.DrawingContext;
import windowSystem.IMouseCallback;
import windowSystem.IPaintCallback;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;

/**
 *
 * @author Andre
 */
public class TopWindow {
    SimpleWindow containerWindow;
    TitleBarWindow titleWindow;
    SimpleWindow clientWindow;
    PointF titleDragPoint;
    WindowManager windowManager;
    
    public TopWindow(RectangleF windowArea, String title){
        containerWindow = new SimpleWindow(windowArea);
        titleWindow = new TitleBarWindow(new RectangleF(.0f, .0f, 1.0f, .1f), title);
        clientWindow = new SimpleWindow(new RectangleF(.0f, .1f, 1.0f, .9f));
        
        containerWindow.addChild(titleWindow);
        containerWindow.addChild(clientWindow);
        
        titleWindow.setMousePressedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.handleTitleBarMousePressed(point);
            }
        });
        
        titleWindow.setMouseDraggedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.handleTitleBarMouseDragged(point);
            }
        });
        
        clientWindow.setPaintCallback(new IPaintCallback() {

            @Override
            public void handleDraw(DrawingContext drawingContext) {
                TopWindow.this.handlePaint(drawingContext);
            }
        });
        
        clientWindow.setMouseClickedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.handleMouseClicked(point);
            }
        });
        
        titleWindow.setCloseCallback(new ICloseCallback() {

            @Override
            public void handleClose() {
                TopWindow.this.handleClose();
            }
        });
    }
    
    public void addChild(SimpleWindow child){
        clientWindow.addChild(clientWindow);
    }
    
    protected void handlePaint(DrawingContext drawingContext){
        
    }
    
    protected void handleMouseClicked(PointF point){
        
    }
    
    protected void handleClose(){
        requestClose();
    }
    
    private void handleTitleBarMouseDragged(PointF point){
        if(titleDragPoint == null)
            return;
        //Convert to container coordinates
        PointF containerPoint = CoordinateMath.transformToAbsolutePoint(point, 
                titleWindow.getWindowArea());
        
        //Convert to abstract desktop coordinates
        PointF desktopPoint = CoordinateMath.transformToAbsolutePoint(containerPoint, containerWindow.getWindowArea());
        
        PointF movementVector = new PointF(desktopPoint.getX() - titleDragPoint.getX(), 
                desktopPoint.getY() - titleDragPoint.getY());
        
        PointF finalPosition = new PointF(containerWindow.getWindowArea().getX() + movementVector.getX(),
                containerWindow.getWindowArea().getY() + movementVector.getY());
        
        containerWindow.moveTo(finalPosition);
        titleDragPoint = desktopPoint;
        requestRepaint();
    }
    
    public void moveTo(PointF position){
        containerWindow.moveTo(position);
    }
    
    public void requestRepaint(){
        containerWindow.requestRepaint();
    }

    private void handleTitleBarMousePressed(PointF point) {
        PointF containerDragPoint = CoordinateMath.transformToAbsolutePoint(point, titleWindow.getWindowArea());
        titleDragPoint = CoordinateMath.transformToAbsolutePoint(containerDragPoint, containerWindow.getWindowArea());
    }
    
    public void requestClose(){
        windowManager.removeTopWindow(this);
    }
}

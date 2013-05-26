/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.awt.Rectangle;
import windowSystem.CoordinateMath;
import windowSystem.DrawingContext;
import windowSystem.IMouseCallback;
import windowSystem.IPaintCallback;
import windowSystem.PointF;
import windowSystem.RectangleF;
import windowSystem.SimpleWindow;
import java.awt.Rectangle;
import windowSystem.IInitCallback;

/**
 *
 * @author Andre
 */
public class TopWindow {
    private static final int TITLE_DESKTOP_HEIGHT = 25;
    SimpleWindow containerWindow;
    TitleBarWindow titleWindow;
    SimpleWindow clientWindow;
    ResizeWindow resizeWindow;
    PointF titleDragPoint;
    WindowManager windowManager;
    
    
    public TopWindow(RectangleF windowArea, String title){
        containerWindow = new SimpleWindow(windowArea);
        titleWindow = new TitleBarWindow(new RectangleF(.0f, .0f, 1.0f, .1f), title);
        clientWindow = new SimpleWindow(new RectangleF(.0f, .1f, 1.0f, .9f));
        resizeWindow = new ResizeWindow(new RectangleF(.9f, .9f, .1f, .1f));
        
        containerWindow.addChild(titleWindow);
        containerWindow.addChild(clientWindow);
        containerWindow.addChild(resizeWindow);
        
        titleWindow.setMousePressedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.windowManager.moveWindowToTop(TopWindow.this);
                TopWindow.this.handleTitleBarMousePressed(point);
            }
        });
        
        clientWindow.setMousePressedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.windowManager.moveWindowToTop(TopWindow.this);
                TopWindow.this.handleMousePressed(point);
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
        
        resizeWindow.setMouseDraggedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.handleResizeWindowDragged(point);
            }
        });
        
        resizeWindow.setMousePressedCallback(new IMouseCallback() {

            @Override
            public void handleMouse(PointF point) {
                TopWindow.this.windowManager.moveWindowToTop(TopWindow.this);
            }
        });
        
        containerWindow.setInitCallback(new IInitCallback() {

            @Override
            public void handleInit() {
                recalculateTitleSize();
            }
        });
    }
    
    public void addChild(SimpleWindow child){
        clientWindow.addChild(child);
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
    
    public void resize(float width, float height){
        containerWindow.resize(width, height);
    }

    private void handleResizeWindowDragged(PointF point) {
        //Convert to container coordinates
        PointF containerPoint = CoordinateMath.transformToAbsolutePoint(point, 
                resizeWindow.getWindowArea());
        
        containerWindow.resize(containerPoint.getX() * containerWindow.getWindowArea().getWidth(), containerPoint.getY() * containerWindow.getWindowArea().getHeight());
        recalculateTitleSize();
        requestRepaint();
    }
    
    private void recalculateTitleSize(){
        //Quick hack to have a constant title bar height and a constant size
        //for the close button
        Rectangle containerDesktopArea = containerWindow.getDesktopArea();
        float height = (float)TITLE_DESKTOP_HEIGHT / (float)containerDesktopArea.getHeight();
        titleWindow.resize(titleWindow.getWindowArea().getWidth(), height);
        clientWindow.moveTo(new PointF(0.0f, height));
        clientWindow.resize(clientWindow.getWindowArea().getWidth(), 1.0f-height);
        CloseWindow closeWindow = titleWindow.getCloseWindow();
        float closeWidth = 20.0f/(float)titleWindow.getDesktopArea().getWidth();
        closeWindow.resize(closeWidth, .8f);
        float closeX = 2.0f/(float)titleWindow.getDesktopArea().getWidth();
        closeWindow.moveTo(new PointF(closeX, closeX));
    }

    protected void handleMousePressed(PointF point) {
        
    }
}

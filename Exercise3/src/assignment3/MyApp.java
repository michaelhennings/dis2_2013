package assignment3;


import windowSystem.IPaintCallback;
import windowSystem.SimpleWindow;
import windowSystem.RectangleF;
import windowSystem.Point;
import windowSystem.WindowSystem;
import windowSystem.DrawingContext;
import java.awt.Color;
import java.awt.Rectangle;
import windowSystem.IMouseClickedCallback;


public class MyApp {
	public static void main(String[] args){
		WindowSystem windowSystem = new WindowSystem(800, 600);
                
                //Init window 1
                SimpleWindow window1 = windowSystem.createRootWindow(new RectangleF(.1f, .1f, 0.5f, 0.5f));
                
                window1.setPaintCallback(new IPaintCallback() {

                    @Override
                    public void handleDraw(DrawingContext drawingContext) {
                        drawingContext.setColor(Color.GRAY);
                        drawingContext.fillRect(new RectangleF(0.0f, 0.0f, 1.0f, 1.0f));
                        drawingContext.setColor(Color.black);
                        drawingContext.drawLine(new Point<Float>(.2f, .3f), new Point<Float>(.8f, .7f));
                    }
                });
                
                window1.setMouseClickedCallback(new IMouseClickedCallback() {

                    @Override
                    public void handleMouseClicked(Point<Float> point) {
                        System.out.println("Hallo: (" + point.getX() + ", " + point.getY() + ")");
                    }
                });
                
                //Init window 2
                SimpleWindow window2 = windowSystem.createRootWindow(new RectangleF(.7f, .1f, 0.2f, 0.2f));
                window2.setPaintCallback(new IPaintCallback() {

                    @Override
                    public void handleDraw(DrawingContext drawingContext) {
                        drawingContext.setColor(Color.GRAY);
                        drawingContext.fillRect(new RectangleF(0.0f, 0.0f, 1.0f, 1.0f));
                        drawingContext.setColor(Color.black);
                        drawingContext.drawLine(new Point<Float>(.2f, .3f), new Point<Float>(.8f, .7f));
                        drawingContext.drawLine(new Point<Float>(.2f, .8f), new Point<Float>(.8f, .3f));
                    }
                });
                
                //Init window 3
                SimpleWindow window3 = windowSystem.createRootWindow(new RectangleF(.1f, .7f, 0.8f, 0.2f));
                window3.setPaintCallback(new IPaintCallback() {

                    @Override
                    public void handleDraw(DrawingContext drawingContext) {
                        drawingContext.setColor(Color.GRAY);
                        drawingContext.fillRect(new RectangleF(0.0f, 0.0f, 1.0f, 1.0f));
                        drawingContext.setColor(Color.black);
                        drawingContext.drawLine(new Point<Float>(.2f, .3f), new Point<Float>(.8f, .7f));
                        drawingContext.drawLine(new Point<Float>(.2f, .8f), new Point<Float>(.8f, .3f));
                        drawingContext.drawLine(new Point<Float>(.5f, .1f), new Point<Float>(.5f, .9f));
                    }
                });
	}
}

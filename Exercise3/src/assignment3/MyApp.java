package assignment3;


import windowSystem.IPaintCallback;
import windowSystem.SimpleWindow;
import windowSystem.RectangleF;
import windowSystem.PointF;
import windowSystem.WindowSystem;
import windowSystem.DrawingContext;
import java.awt.Color;
import java.awt.Rectangle;
import windowSystem.IMouseClickedCallback;


public class MyApp {
	public static void main(String[] args){
		WindowSystem windowSystem = new WindowSystem(800, 600);
                
                ColorWindow colorWindow = new ColorWindow(new RectangleF(.1f, .1f, .5f, .5f), Color.red, Color.blue);
                
                ColorWindow innerWindow = new ColorWindow(new RectangleF(.25f, .25f, .5f, .5f), Color.green, Color.yellow);
                colorWindow.addChild(innerWindow);
                
                ColorWindow innerInnerWindow = new ColorWindow(new RectangleF(.5f, .5f, .25f, .25f), Color.black, Color.white);
                innerWindow.addChild(innerInnerWindow);
                
                windowSystem.addWindow(colorWindow);
	}
}

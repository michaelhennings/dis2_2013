package assignment3;


import windowSystem.RectangleF;
import windowSystem.WindowSystem;
import java.awt.Color;
import windowManager.WindowManager;


public class MyApp {
	public static void main(String[] args){
		WindowSystem windowSystem = new WindowSystem(800, 600);
                WindowManager windowManager = new WindowManager(windowSystem);
                
                ColorWindow window1 = new ColorWindow(new RectangleF(.1f, .1f, .4f, .4f), "Window1", Color.BLACK, Color.white);
                windowManager.addTopWindow(window1);
                
                ColorWindow window2 = new ColorWindow(new RectangleF(.6f, .1f, .3f, .3f), "Window2", Color.green, Color.red);
                windowManager.addTopWindow(window2);
                
                ColorWindow window3 = new ColorWindow(new RectangleF(.1f, .6f, .8f, .3f), "Window3", Color.pink, Color.yellow);
                windowManager.addTopWindow(window3);
	}
}

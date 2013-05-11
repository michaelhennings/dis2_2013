/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.util.ArrayList;
import java.util.List;
import windowSystem.SimpleWindow;
import windowSystem.WindowSystem;

/**
 *
 * @author Andre
 */
public class WindowManager {
    private WindowSystem windowSystem;
    private List<TopWindow> topWindows;
    
    public WindowManager(WindowSystem windowSystem){
        this.windowSystem = windowSystem;
        topWindows = new ArrayList<TopWindow>();
    }
    
    public void addTopWindow(TopWindow window){
        topWindows.add(window);
        windowSystem.addWindow(window.containerWindow);
    }
}

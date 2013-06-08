/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.util.ArrayList;
import java.util.List;
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
        window.windowManager = this;
        windowSystem.addWindow(window.containerWindow);
    }
    
    public void removeTopWindow(TopWindow window){
        windowSystem.removeWindow(window.containerWindow);
        topWindows.remove(window);
    }
    
    void moveWindowToTop(TopWindow window){
        windowSystem.moveWindowToTop(window.containerWindow);
    }
}

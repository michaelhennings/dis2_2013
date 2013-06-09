/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windowManager;

import java.util.ArrayList;
import java.util.List;
import windowManager.rat.RatTaskbar;
import windowManager.taskbar.ITaskbar;
import windowSystem.WindowSystem;

/**
 *
 * @author Andre
 */
public class WindowManager {
    private final WindowSystem windowSystem;
    private final List<TopWindow> topWindows;
    private final ITaskbar taskbar;

    public WindowManager(WindowSystem windowSystem, ITaskbar taskbar){
        this.windowSystem = windowSystem;
        topWindows = new ArrayList<TopWindow>();
        this.taskbar = taskbar;
        this.taskbar.setWindowManager(this);
        this.taskbar.setWindowSystem(windowSystem);
        windowSystem.addWindow(taskbar.asSimpleWindow());
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

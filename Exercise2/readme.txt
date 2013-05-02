### Group ###

André Tebart, 292***, andre.tebart@rwth-aachen.de
Jonas Strohmeier, 292287, jonas.strohmeier@rwth-aachen.de
Michael Hennings, 292165, michael.hennings@rwth-aachen.de

### Testing Your Understanding ###
1)
At the moment we decided to use a simple list to keep track of all windows. The
more up-front the window, the lower is its z-index. This will simplify repainting
the complete desktop (draw each window as it appears in the list).
2)
adding/removing windows:
  Nothing special has to be considered, simple list operations (add at the end, remove anytime).
  Repaint the desktop aferwards.
drawing windows in front-to-back order:
  Paint all windows as they appear in the z-index-sorted list.
finding a specific window given an arbitrary (x; y) (desktop) coordinate:
  Traverse list from back to front and take the first one that covers the given 
  the coordinate.
overall code complexity:
  The current code is simple, most methods have only managing character with 
  2 to 3 lines each.

### Extra Credit ###
Describe what you learned for the design of your own event handling system:
  For events that change parameters controlling the appearance of the window
  extra care must be taken to store data at the beginning of the event if needed.
  However, the changed state must also be made visible, that is, a repaint call
  must be manually executed.
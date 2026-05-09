package mouseState;

import java.awt.event.MouseEvent;

import gui.Canvas;

public interface MouseStateInterface {
    void onPressed(MouseEvent e, Canvas canvas);
    void onDragged(MouseEvent e, Canvas canvas);
    void onReleased(MouseEvent e, Canvas canvas);
} 
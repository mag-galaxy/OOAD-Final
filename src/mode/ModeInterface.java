package mode;

import java.awt.event.MouseEvent;

import gui.Canvas;

public interface ModeInterface {
    public abstract void onPressed(MouseEvent e, Canvas canvas);
    public abstract void onDragged(MouseEvent e, Canvas canvas);
    public abstract void onReleased(MouseEvent e, Canvas canvas);
    public abstract void onHovered(MouseEvent e, Canvas canvas);
}

package mouseState;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.MouseEvent;

import gui.Canvas;
import objects.*;

public class DragState implements MouseStateInterface {
    private ArrayList<BasicAbstract> draggedObjs;
    private Point lastMousePoint;

    public DragState(ArrayList<BasicAbstract> objs, Point p) {
        this.draggedObjs = objs;
        this.lastMousePoint = p;
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {}

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {
        int dx = e.getX() - lastMousePoint.x;
        int dy = e.getY() - lastMousePoint.y;

        for (BasicAbstract obj: draggedObjs){
            obj.setX(obj.getX() + dx);
            obj.setY(obj.getY() + dy);
        }
        lastMousePoint = e.getPoint();
    }

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        lastMousePoint = null;
        canvas.setMouseState(new IdleState());
    }

    @Override
    public void onHovered(MouseEvent e, Canvas canvas) {}
}

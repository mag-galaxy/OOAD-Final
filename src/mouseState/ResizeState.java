package mouseState;

import java.awt.*;
import java.awt.event.MouseEvent;

import gui.Canvas;
import objects.BasicAbstract;
import objects.Port;

public class ResizeState implements MouseStateInterface {
    private Port resizePort;
    private Rectangle originalBound;
    private BasicAbstract target;

    public ResizeState(Port p){
        this.resizePort = p;
        this.target = resizePort.getOwner();
        this.originalBound = new Rectangle(target.getX(), target.getY(), target.getWidth(), target.getHeight());
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {}

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {
        Point point = e.getPoint();
        
        int x1 = originalBound.x;
        int y1 = originalBound.y;
        int x2 = x1 + originalBound.width;
        int y2 = y1 + originalBound.height;

        if (resizePort.getRatioX() == 0) x1 = point.x;
        else if (resizePort.getRatioX() == 1) x2 = point.x;

        if (resizePort.getRatioY() == 0) y1 = point.y;
        else if (resizePort.getRatioY() == 1) y2 = point.y;

        int newX = Math.min(x1, x2);
        int newY = Math.min(y1, y2);
        int newW = Math.abs(x2 - x1);
        int newH = Math.abs(y2 - y1);

        target.setX(newX);
        target.setY(newY);
        target.setWidth(newW);
        target.setHeight(newH);
    }

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        resizePort = null;
        originalBound = null;
        target = null;
    }
}

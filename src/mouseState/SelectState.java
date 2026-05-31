package mouseState;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.MouseEvent;

import gui.Canvas;
import objects.BasicAbstract;

public class SelectState implements MouseStateInterface {
    private Point selectStart;
    private Rectangle selectRect;

    public SelectState(Point p){
        this.selectStart = p;
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {}

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {
        int x = Math.min(e.getX(), selectStart.x);
        int y = Math.min(e.getY(), selectStart.y);
        int width = Math.abs(e.getX() - selectStart.x);
        int height = Math.abs(e.getY() - selectStart.y);
        
        selectRect = new Rectangle(x, y, width, height);
        canvas.setSelectRect(selectRect);
    }

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        if (selectRect == null) return;
        ArrayList<BasicAbstract> allObjs = canvas.getObjs();
        for (BasicAbstract s : allObjs) {
            Rectangle objRect = new Rectangle(s.getX(), s.getY(), s.getWidth(), s.getHeight());
            if (selectRect.contains(objRect)) {
                s.setIsSelected(true);
            }
        }
        canvas.updateSelectedObject();
        selectRect = null;
        selectStart = null;
        canvas.setSelectRect(selectRect);
    }
}

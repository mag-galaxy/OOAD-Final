package mouseState;

import java.util.ArrayList;

import java.awt.*;
import java.awt.event.MouseEvent;

import gui.Canvas;
import objCreate.LinkCreateInterface;
import objects.BasicAbstract;
import objects.Port;

public class IdleState implements MouseStateInterface {
    ArrayList<BasicAbstract> selectedObjs;

    public IdleState(){
        selectedObjs = new ArrayList<>();
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {
        Point point = e.getPoint();
        Port port = canvas.findPortAt(point);
        BasicAbstract object = canvas.findObjAt(point);
        LinkCreateInterface linkStrategy = canvas.getLinkDraw();
        selectedObjs = canvas.getSelectedObjs();

        if (linkStrategy != null) {
            if (port != null) {
                canvas.setMouseState(new DrawLinkState(port, linkStrategy));
                System.out.println("draw link mode");
            }
        }
        else if(port != null){
            canvas.resetObjs();
            canvas.setMouseState(new ResizeState(port));
            System.out.println("resize mode");
        }
        else if(object != null && selectedObjs.contains(object)){ // select one of old objs
            canvas.setMouseState(new DragState(selectedObjs, point));
            System.out.println("dragging mode");
        }
        else if(object != null){ // select a new obj
            canvas.selectObjectAt(point);
            canvas.updateSelectedObject();
            selectedObjs = canvas.getSelectedObjs();
            canvas.setMouseState(new DragState(selectedObjs, point));
            System.out.println("dragging mode");
        }
        else{
            canvas.resetObjs();
            canvas.setMouseState(new SelectionState(point));
            System.out.println("select mode");
        }
    }

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {}

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {}
}

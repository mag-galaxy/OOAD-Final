package mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import gui.Canvas;
import mouseState.DragState;
import mouseState.MouseStateInterface;
import mouseState.ResizeState;
import mouseState.SelectState;
import objects.BasicAbstract;
import objects.Port;

public class SelectMode implements ModeInterface {
    MouseStateInterface state;
    List<BasicAbstract> allObjs;
    List<BasicAbstract> selectedObjs;

    public SelectMode(){
        allObjs = new ArrayList<>();
        selectedObjs = new ArrayList<>();
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {
        Point point = e.getPoint();
        BasicAbstract object = canvas.findObjAt(point);
        Port port = canvas.findPortAt(point);

        selectedObjs = canvas.getSelectedObjs();

        if (object != null && selectedObjs.contains(object)) {
            state = new DragState(selectedObjs, point);
            System.out.println("dragging state");
        }
        else if (object != null) {
            canvas.selectObjectAt(point);
            canvas.updateSelectedObject();
            selectedObjs = canvas.getSelectedObjs();
            state = new DragState(selectedObjs, point);
            System.out.println("dragging state");
        }
        else if (port != null) {
            canvas.resetObjs();
            port.getOwner().setIsSelected(true);
            canvas.updateSelectedObject();
            state = new ResizeState(port);
            System.out.println("resizing state");
        }
        else {
            canvas.resetObjs();
            canvas.updateSelectedObject();
            state = new SelectState(point);
            System.out.println("selecting state");
        }
    }

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {
        state.onDragged(e, canvas);
    }

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        state.onReleased(e, canvas);
    }

    @Override
    public void onHovered(MouseEvent e, Canvas canvas) {
        BasicAbstract newHovered = canvas.findObjAt(e.getPoint());
        BasicAbstract oldHovered = canvas.getHoveredObj();
        if (oldHovered != newHovered) {
            if (oldHovered != null) {
                oldHovered.setIsHovered(false);
            }
            if (newHovered != null) {
                newHovered.setIsHovered(true);
            }
            canvas.setHoveredObj(newHovered);
        }
    }
}
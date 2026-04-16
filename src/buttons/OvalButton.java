package buttons;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import draw.OvalCreate;
import gui.Canvas;
import gui.ToolBar;

public class OvalButton extends ButtonAbstract implements MouseListener{
    public OvalButton(Canvas canvas, ToolBar toolBar) {
        super("oval", canvas, toolBar);
        setIcon("oval");
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point dropPoint = e.getPoint();
        Point pOnCanvas = SwingUtilities.convertPoint(this, dropPoint, canvas);
        if (canvas.contains(pOnCanvas)){
            canvas.addShape(new OvalCreate(), pOnCanvas.x, pOnCanvas.y);
        }
        toolBar.resetButtonColor();
        toolBar.activeModeButton.setSelectedStyle();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

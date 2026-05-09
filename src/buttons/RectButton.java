package buttons;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import gui.Canvas;
import gui.ToolBar;
import objCreate.RectCreate;

public class RectButton extends ButtonAbstract implements MouseListener{
    private static final String LABEL = "rect";

    public RectButton(Canvas canvas, ToolBar toolBar) {
        super(LABEL, canvas, toolBar);
        setIcon(LABEL);
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
        if (canvas.contains(pOnCanvas)) {
            canvas.addShape(new RectCreate(), pOnCanvas.x, pOnCanvas.y);
        }
        toolBar.resetButtonColor();
        toolBar.activeModeButton.setSelectedStyle();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

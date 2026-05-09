package buttons;

import javax.swing.SwingUtilities;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gui.Canvas;
import gui.ToolBar;
import objects.Oval;

public class OvalButton extends ButtonAbstract implements MouseListener{
    private static final String LABEL = "oval";
    
    public OvalButton(Canvas canvas, ToolBar toolBar) {
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
        if (canvas.contains(pOnCanvas)){
            canvas.addShape((x, y, depth) -> new Oval(x, y, depth), pOnCanvas.x, pOnCanvas.y);
        }
        toolBar.resetButtonColor();
        toolBar.activeModeButton.setSelectedStyle();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

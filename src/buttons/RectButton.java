package buttons;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import draw.RectCreate;
import gui.Canvas;
import gui.ToolBar;

public class RectButton extends ButtonAbstract implements MouseListener{
    public RectButton(Canvas canvas, ToolBar toolBar) {
        super("rect", canvas, toolBar);
        setIcon("rect");
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
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     toolBar.resetButtonColor();
    //     this.setSelectedStyle();
    //     canvas.setBasicDraw(new RectCreate());
    //     canvas.setLinkDraw(null);
    // }
}

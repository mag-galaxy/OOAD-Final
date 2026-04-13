package buttons;

import java.awt.event.ActionEvent;
import draw.RectCreate;
import gui.Canvas;
import gui.ToolBar;

public class RectButton extends ButtonAbstract {
    public RectButton(Canvas canvas, ToolBar toolBar) {
        super("rect", canvas, toolBar);
        setIcon("rect");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setBasicDraw(new RectCreate());
        canvas.setLinkDraw(null);
    }
}

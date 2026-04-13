package buttons;

import java.awt.event.ActionEvent;
import draw.OvalCreate;
import gui.Canvas;
import gui.ToolBar;

public class OvalButton extends ButtonAbstract {
    public OvalButton(Canvas canvas, ToolBar toolBar) {
        super("oval", canvas, toolBar);
        setIcon("oval");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setBasicDraw(new OvalCreate());
        canvas.setLinkDraw(null);
    }
}

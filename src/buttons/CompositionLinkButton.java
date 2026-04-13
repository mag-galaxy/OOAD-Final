package buttons;

import java.awt.event.ActionEvent;
import draw.CompositionCreate;
import gui.Canvas;
import gui.ToolBar;

public class CompositionLinkButton extends ButtonAbstract {
    public CompositionLinkButton(Canvas canvas, ToolBar toolBar) {
        super("composition", canvas, toolBar);
        setIcon("composition");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setBasicDraw(null);
        canvas.setLinkDraw(new CompositionCreate());
    }
}

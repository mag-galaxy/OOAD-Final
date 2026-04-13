package buttons;

import java.awt.event.ActionEvent;
import draw.GeneralizationCreate;
import gui.Canvas;
import gui.ToolBar;

public class GeneralizationLinkButton extends ButtonAbstract {
    public GeneralizationLinkButton(Canvas canvas, ToolBar toolBar){
        super("generalization", canvas, toolBar);
        setIcon("generalization");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setBasicDraw(null);
        canvas.setLinkDraw(new GeneralizationCreate());
    }
}

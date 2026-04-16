package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import draw.GeneralizationCreate;
import gui.Canvas;
import gui.ToolBar;

public class GeneralizationLinkButton extends ButtonAbstract implements ActionListener {
    public GeneralizationLinkButton(Canvas canvas, ToolBar toolBar){
        super("generalization", canvas, toolBar);
        setIcon("generalization");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setLinkDraw(new GeneralizationCreate());
    }
}

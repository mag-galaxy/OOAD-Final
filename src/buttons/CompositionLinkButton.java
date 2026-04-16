package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import draw.CompositionCreate;
import gui.Canvas;
import gui.ToolBar;

public class CompositionLinkButton extends ButtonAbstract implements ActionListener {
    public CompositionLinkButton(Canvas canvas, ToolBar toolBar) {
        super("composition", canvas, toolBar);
        setIcon("composition");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setLinkDraw(new CompositionCreate());
    }
}

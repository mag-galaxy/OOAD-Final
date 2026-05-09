package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Canvas;
import gui.ToolBar;
import objCreate.CompositionCreate;

public class CompositionLinkButton extends ButtonAbstract implements ActionListener {
    private static final String LABEL = "composition";
    
    public CompositionLinkButton(Canvas canvas, ToolBar toolBar) {
        super(LABEL, canvas, toolBar);
        setIcon(LABEL);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setLinkDraw(new CompositionCreate());
    }
}

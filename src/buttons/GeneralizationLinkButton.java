package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Canvas;
import gui.ToolBar;
import objCreate.GeneralizationCreate;

public class GeneralizationLinkButton extends ButtonAbstract implements ActionListener {
    private static final String LABEL = "generalization";
    
    public GeneralizationLinkButton(Canvas canvas, ToolBar toolBar){
        super(LABEL, canvas, toolBar);
        setIcon(LABEL);
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setLinkDraw(new GeneralizationCreate());
    }
}

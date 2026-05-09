package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Canvas;
import gui.ToolBar;
import objCreate.AssociationCreate;

public class AssociationLinkButton extends ButtonAbstract implements ActionListener{
    private static final String LABEL = "association";
    
    public AssociationLinkButton(Canvas canvas, ToolBar toolBar) {
        super(LABEL, canvas, toolBar);
        setIcon(LABEL);
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setLinkDraw(new AssociationCreate());
    }
}

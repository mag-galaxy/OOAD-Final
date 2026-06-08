package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ToolBar;
import mode.DrawLinkMode;
import objects.Association;

public class AssociationLinkButton extends ButtonAbstract implements ActionListener{
    private static final String LABEL = "association";
    
    public AssociationLinkButton(ToolBar toolBar) {
        super(LABEL, toolBar);
        setIcon(LABEL);
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setMode(new DrawLinkMode((p1, p2) -> new Association(p1, p2)));
    }
}

package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import draw.AssociationCreate;
import gui.Canvas;
import gui.ToolBar;

public class AssociationLinkButton extends ButtonAbstract implements ActionListener{
    public AssociationLinkButton(Canvas canvas, ToolBar toolBar) {
        super("association", canvas, toolBar);
        setIcon("association");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setLinkDraw(new AssociationCreate());
    }
}

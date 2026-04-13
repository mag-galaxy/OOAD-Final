package buttons;

import java.awt.event.ActionEvent;

import draw.AssociationCreate;
import gui.Canvas;
import gui.ToolBar;

public class AssociationLinkButton extends ButtonAbstract {
    public AssociationLinkButton(Canvas canvas, ToolBar toolBar) {
        super("association", canvas, toolBar);
        setIcon("association");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setSelectedStyle();
        canvas.setBasicDraw(null);
        canvas.setLinkDraw(new AssociationCreate()); // 關閉連線模式
    }
}

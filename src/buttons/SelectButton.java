package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

import gui.Canvas;
import gui.ToolBar;

public class SelectButton extends ButtonAbstract {
    public SelectButton(Canvas canvas, ToolBar toolBar) { 
        super("select", canvas, toolBar);
        setIcon("select");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.resetButtonColor();
        this.setBackground(Color.GRAY);
        canvas.setBasicDraw(null);
        canvas.setLinkDraw(null);
    }    
}

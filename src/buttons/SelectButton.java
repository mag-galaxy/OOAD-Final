package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.Canvas;
import gui.ToolBar;

public class SelectButton extends ButtonAbstract implements ActionListener{
    public SelectButton(Canvas canvas, ToolBar toolBar) { 
        super("select", canvas, toolBar);
        setIcon("select");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setLinkDraw(null);
    }    
}

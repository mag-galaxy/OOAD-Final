package buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.ToolBar;
import mode.SelectMode;

public class SelectButton extends ButtonAbstract implements ActionListener{
    public SelectButton(ToolBar toolBar) { 
        super("select", toolBar);
        setIcon("select");
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        toolBar.setActiveBtn(this);
        canvas.setMode(new SelectMode());
    }    
}

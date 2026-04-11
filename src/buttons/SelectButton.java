package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

public class SelectButton extends ButtonAbstract {
    public SelectButton() { 
        super("select");
        setIcon("select");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // 通知 Canvas 進入 SELECT 模式
        
        setBackground(Color.GRAY);
    }    
}

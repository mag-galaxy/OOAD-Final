package buttons;

import java.awt.Color;
import java.awt.event.ActionEvent;

import objects.Oval;

public class OvalButton extends ButtonAbstract {
    public OvalButton() {
        super("oval");
        setIcon("oval");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 通知 Canvas 進入創建 Oval 模式 [cite: 17]
        canvas.drawLink = null;
        canvas.drawBasic = null;
        this.setBackground(Color.GRAY);
    }
}

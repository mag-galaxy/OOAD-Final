package buttons;

import java.awt.event.ActionEvent;

public class RectButton extends ButtonAbstract {
    public RectButton() {
        super("rect");
        setIcon("rect");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 通知 Canvas 進入創建 Rect 模式 [cite: 16]
    }
}

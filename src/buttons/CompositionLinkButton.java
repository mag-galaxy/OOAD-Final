package buttons;

import java.awt.event.ActionEvent;

public class CompositionLinkButton extends ButtonAbstract {
    public CompositionLinkButton() {
        super("composition");
        setIcon("composition");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 進入連結模式 [cite: 13]
    }
    
}

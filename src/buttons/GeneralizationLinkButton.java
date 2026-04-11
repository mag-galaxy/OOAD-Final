package buttons;

import java.awt.event.ActionEvent;

public class GeneralizationLinkButton extends ButtonAbstract {
    public GeneralizationLinkButton(){
        super("generalization");
        setIcon("generalization");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 進入連結模式 [cite: 13]
    }
}

package buttons;

import java.awt.event.ActionEvent;

public class AssociationLinkButton extends ButtonAbstract {
    public AssociationLinkButton() {
        super("association");
        setIcon("association");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // 進入連結模式 [cite: 13]
    }
    
}

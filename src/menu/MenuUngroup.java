package menu;

import java.awt.event.ActionEvent;

public class MenuUngroup extends MenuItemAbstract {
    public MenuUngroup() {
        super("Ungroup");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.ungroupObjects();
    }
}

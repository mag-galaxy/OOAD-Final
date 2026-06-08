package menu;

import java.awt.event.ActionEvent;

public class MenuGroup extends MenuItemAbstract{
    public MenuGroup() {
        super("Group");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.groupObjects();
    }
}

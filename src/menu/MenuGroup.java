package menu;

import java.awt.event.ActionEvent;

import gui.Canvas;

public class MenuGroup extends MenuItemAbstract{
    public MenuGroup(Canvas canvas) {
        super("Group", canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.groupObjects();
        System.out.println("Objects Grouped");
    }
}

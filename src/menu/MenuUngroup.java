package menu;

import java.awt.event.ActionEvent;
import gui.Canvas;

public class MenuUngroup extends MenuItemAbstract {
    public MenuUngroup(Canvas canvas) {
        super("Ungroup", canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        canvas.ungroupObjects();
        System.out.println("Group Dissolved");
    }
}

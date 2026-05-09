package menu;

import javax.swing.*;

import gui.Canvas;

public class EditMenu extends JMenu {
    private MenuGroup menuGroup;
    private MenuUngroup menuUngroup;
    private MenuLabel menuLabel;

    public EditMenu(String label, Canvas canvas){
        super(label);
        addMenuItems(canvas);
    }

    private void addMenuItems(Canvas canvas){
        menuGroup = new MenuGroup(canvas);
        menuUngroup = new MenuUngroup(canvas);
        menuLabel = new MenuLabel(canvas);
        this.add(menuGroup);
        this.add(menuUngroup);
        this.add(menuLabel);
    }
}

package menu;

import javax.swing.*;

public class EditMenu extends JMenu {
    private MenuGroup menuGroup;
    private MenuUngroup menuUngroup;
    private MenuLabel menuLabel;

    public EditMenu(String label){
        super(label);
        addMenuItems();
    }

    private void addMenuItems(){
        menuGroup = new MenuGroup();
        menuUngroup = new MenuUngroup();
        menuLabel = new MenuLabel();
        this.add(menuGroup);
        this.add(menuUngroup);
        this.add(menuLabel);
    }
}

package menu;

import gui.Canvas;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public JMenu fileMenu;
    public JMenu editMenu;
    
    public MenuBar(Canvas canvas){
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        editMenu.add(new MenuGroup(canvas));
        editMenu.add(new MenuUngroup(canvas));
        editMenu.add(new MenuLabel(canvas));

        this.add(fileMenu);
        this.add(editMenu);
    }
}

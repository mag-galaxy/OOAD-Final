package menu;

import gui.Canvas;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public MenuBar(Canvas canvas){
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        editMenu.add(new MenuGroup(canvas));
        editMenu.add(new MenuUngroup(canvas));
        editMenu.add(new MenuLabel(canvas));

        this.add(fileMenu);
        this.add(editMenu);
    }
}

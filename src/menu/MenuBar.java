package menu;

import gui.Canvas;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    public FileMenu fileMenu;
    public EditMenu editMenu;
    
    public MenuBar(Canvas canvas){
        fileMenu = new FileMenu("File");
        editMenu = new EditMenu("Edit", canvas);
        this.add(fileMenu);
        this.add(editMenu);
    }
}

package menu;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    private static MenuBar instance;

    public FileMenu fileMenu;
    public EditMenu editMenu;

    private MenuBar(){
        fileMenu = new FileMenu("File");
        editMenu = new EditMenu("Edit");
        this.add(fileMenu);
        this.add(editMenu);
    }
    
    public static MenuBar getInstance(){
        if (instance == null) instance = new MenuBar();
        return instance;
    }
}

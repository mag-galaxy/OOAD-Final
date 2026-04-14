package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import gui.Canvas;

public abstract class MenuItemAbstract extends JMenuItem implements ActionListener{

    protected Canvas canvas;

    public MenuItemAbstract(String name, Canvas canvas) {
        super(name);
        this.canvas = canvas;
        this.addActionListener(this);
    }
    
    @Override
    public abstract void actionPerformed(ActionEvent e);
}
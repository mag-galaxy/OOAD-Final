package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import gui.Canvas;
import objects.BasicAbstract;
import objects.ShapeAbstract;

public class MenuLabel extends MenuItemAbstract{
    public MenuLabel(Canvas canvas) {
        super("Label", canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShapeAbstract selected = canvas.getSelectedObject();
        
        if (selected instanceof BasicAbstract) {
            showSettingDialog((BasicAbstract) selected);
        }
        else {
            JOptionPane.showMessageDialog(canvas, "Please select a single basic object.");
        }
    }

    private void showSettingDialog(BasicAbstract target) {
        String newName = JOptionPane.showInputDialog(canvas, "Enter Name:", target.getName());
        
        if (newName != null) {
            target.setName(newName);
            
            Color newColor = JColorChooser.showDialog(canvas, "Choose Color", target.getColor());
            if (newColor != null) {
                target.setColor(newColor);
            }
            canvas.repaint();
        }
    }
}
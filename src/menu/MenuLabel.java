package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import gui.Canvas;
import objects.BasicAbstract;

public class MenuLabel extends MenuItemAbstract{
    public MenuLabel(Canvas canvas) {
        super("Label", canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<BasicAbstract> selectedObjs = canvas.getSelectedObjs();
        if(selectedObjs.size() == 1){
            showSettingDialog(selectedObjs.get(0));
        }
    }

    private void showSettingDialog(BasicAbstract target) {
        String newName = JOptionPane.showInputDialog(canvas, "Enter Name:", target.getLabel());
        
        if (newName != null) {
            target.setLabel(newName);
            
            Color newColor = JColorChooser.showDialog(canvas, "Choose Color", target.getColor());
            if (newColor != null) {
                target.setColor(newColor);
            }
            canvas.repaint();
        }
    }
}
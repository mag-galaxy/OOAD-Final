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
        super("Change Object Name", canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. 取得目前選中的物件 (假設 selectObjectAt 已將選中者移至最後)
        ShapeAbstract selected = canvas.getSelectedObject();
        
        // 2. 只有 BasicAbstract 才能改名與變色 (Composite 通常不支援)
        if (selected instanceof BasicAbstract) {
            showSettingDialog((BasicAbstract) selected);
        }
        else {
            JOptionPane.showMessageDialog(canvas, "Please select a single basic object.");
        }
    }

    private void showSettingDialog(BasicAbstract target) {
        // 彈出輸入視窗詢問名稱
        String newName = JOptionPane.showInputDialog(canvas, "Enter Name:", target.getName());
        
        if (newName != null) {
            target.setName(newName);
            
            // 進階：使用 JColorChooser 讓使用者挑選顏色
            Color newColor = JColorChooser.showDialog(canvas, "Choose Color", target.getColor());
            if (newColor != null) {
                target.setColor(newColor);
            }
            canvas.repaint();
        }
    }
}

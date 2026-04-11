package buttons;

import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gui.Canvas;

public abstract class ButtonAbstract extends JButton implements ActionListener{
    
    protected String modeName;
    protected Canvas canvas;

    public ButtonAbstract(String label) {
        super(label);
        this.modeName = label;
        this.setBackground(Color.WHITE); // 預設背景顏色 
        this.setFocusPainted(false);
        this.addActionListener(this); // 綁定點擊事件
    }

    // 提供給外部統一變色的方法
    public void setSelectedStyle() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }

    public void setUnselectedStyle() {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
    
    public void setIcon(String iconName){
        try {
            String path = "/icons/" + iconName + ".png";
            java.net.URL imgURL = getClass().getResource(path);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(scaledImg));
            }
        } 
        catch (Exception e) {
            System.err.println("找不到圖示: " + iconName);
        }
    }
}

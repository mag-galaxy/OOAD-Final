package buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;

import gui.Canvas;
import gui.ToolBar;

public abstract class ButtonAbstract extends JButton{
    protected Canvas canvas;
    protected ToolBar toolBar;
    private final int ICON_WIDTH = 40;
    private final int ICON_HEIGHT =40;
    private final String ICON_PATH = "/icons/";
    private final String ICON_TYPE = ".png";

    public ButtonAbstract(String label, Canvas canvas, ToolBar toolBar) {
        super(label);
        this.canvas = canvas;
        this.toolBar = toolBar;
        this.setBackground(Color.WHITE);
        this.setFocusPainted(false);
    }

    public void setSelectedStyle() {
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
    }

    public void setUnselectedStyle() {
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
    }
    
    public void setIcon(String iconName){
        try {
            String path = ICON_PATH + iconName + ICON_TYPE;
            java.net.URL imgURL = getClass().getResource(path);
            
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL);
                
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(scaledImg));
            }
        } 
        catch (Exception e) {
            System.err.println("icon not found: " + iconName);
        }
    }
}

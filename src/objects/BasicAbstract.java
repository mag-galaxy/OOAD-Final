package objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.ArrayList;

public abstract class BasicAbstract extends ShapeAbstract{
    public ArrayList<Port> ports;
    private int x;
    private int y;
    private int height;
    private int width;
    private String name = "default";
    private boolean isSelected;

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth(){
        return this.width;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setIsSelected(boolean selected){
        this.isSelected = selected;
    }

    public boolean getIsSelected(){
        return isSelected;
    }

    protected void drawLabel(Graphics g) {
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        // 計算文字置中的座標
        int textX = getX() + (getWidth() - fm.stringWidth(getName())) / 2;
        int textY = getY() + (getHeight() + fm.getAscent()) / 2 - 2; 
        g.drawString(getName(), textX, textY);
    }

    protected void drawPorts(java.awt.Graphics g) {
        if (getIsSelected()) { // 只有在被選取狀態才顯示 ports
            for (Port p : ports) {
                p.draw(g);
            }
        }
    }
}

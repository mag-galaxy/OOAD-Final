package objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.FontMetrics;
import java.util.ArrayList;

public abstract class BasicAbstract extends ShapeAbstract{
    public ArrayList<Port> ports;
    private int x;
    private int y;
    private int height;
    private int width;
    private String name = "";
    private boolean isSelected;
    protected Color color = Color.WHITE;

    @Override
    public void setX(int x){
        this.x = x;
    }

    @Override
    public int getX(){
        return this.x;
    }

    @Override
    public void setY(int y){
        this.y = y;
    }

    @Override
    public int getY(){
        return this.y;
    }

    @Override
    public void setHeight(int height){
        this.height = height;
    }

    @Override
    public int getHeight(){
        return this.height;
    }

    @Override
    public void setWidth(int width){
        this.width = width;
    }

    @Override
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

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public ArrayList<Port> getPorts(){
        return this.ports;
    }

    @Override
    public boolean isInside(Point p) {
        return (p.x >= this.x && p.x <= this.x + this.width &&
                p.y >= this.y && p.y <= this.y + this.height);
    }

    protected void drawLabel(Graphics g) {
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();

        int textX = getX() + (getWidth() - fm.stringWidth(getName())) / 2;
        int textY = getY() + (getHeight() + fm.getAscent()) / 2 - 2; 
        g.drawString(getName(), textX, textY);
    }

    protected void drawPorts(java.awt.Graphics g) {
        if (getIsSelected()) {
            for (Port p : ports) {
                p.draw(g);
            }
        }
    }
}

package objects;

import java.awt.*;
import java.util.List;

public abstract class BasicAbstract extends ShapeAbstract{
    protected List<Port> ports;
    private int depth = 0;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isSelected = false;
    private boolean isHover = false;
    private String label = "";
    private Color color = Color.WHITE;
    private final int MIN_WIDTH = 50;
    private final int MIN_HEIGHT = 40;

    public void setDepth(int d){
          this.depth = d;
     }

     public int getDepth(){
          return this.depth;
     }
    
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
        this.height = (height >= MIN_HEIGHT) ? height : MIN_HEIGHT;
    }

    public int getHeight(){
        return this.height;
    }

    public void setWidth(int width){
        this.width = (width >= MIN_WIDTH) ? width : MIN_WIDTH;
    }

    public int getWidth(){
        return this.width;
    }

    public void setIsSelected(boolean selected){
        this.isSelected = selected;
    }

    public boolean getIsSelected(){
        return isSelected;
    }

    public void setIsHovered(boolean hovered){
        this.isHover = hovered;
    }

    public boolean getIsHovered(){
        return this.isHover;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return this.color;
    }

    public List<Port> getPorts(){
        return this.ports;
    }

    public boolean isInside(Point p) {
        return (p.x >= this.x && p.x <= this.x + this.width &&
                p.y >= this.y && p.y <= this.y + this.height);
    }

    protected void drawLabel(Graphics g) {
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();

        int textX = getX() + (getWidth() - fm.stringWidth(getLabel())) / 2;
        int textY = getY() + (getHeight() + fm.getAscent()) / 2 - 2; 
        g.drawString(getLabel(), textX, textY);
    }

    protected void drawPorts(Graphics g) {
        if ((getIsSelected() || getIsHovered()) && ports != null) {
            for (Port p : ports) {
                p.draw(g);
            }
        }
    }

    public List<BasicAbstract> getMembers(){
        return List.of(this);
    }
}

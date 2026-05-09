package objects;

import java.awt.*;

public class Port extends ShapeAbstract{
    private BasicAbstract owner;
    private double ratioX;
    private double ratioY;
    private final int PORT_SIZE = 8;
    private final int CLICK_RANGE = 12;
    private final Color PORT_COLOR = Color.BLACK;

    public Port(BasicAbstract owner, double rx, double ry) {
        this.owner = owner;
        this.ratioX = rx;
        this.ratioY = ry;
    }

    public BasicAbstract getOwner(){
        return this.owner;
    }

    public double getRatioX(){
        return this.ratioX;
    }

    public double getRatioY(){
        return this.ratioY;
    }

    public Point getAbsolutePosition() {
        int absX = (int) (owner.getX() + owner.getWidth() * ratioX);
        int absY = (int) (owner.getY() + owner.getHeight() * ratioY);
        return new Point(absX, absY);
    }

    public boolean isInside(Point p) { // is p inside this Port
        Point abs = getAbsolutePosition();
        return (p.x >= abs.x - CLICK_RANGE && p.x <= abs.x + CLICK_RANGE &&
                p.y >= abs.y - CLICK_RANGE && p.y <= abs.y + CLICK_RANGE);
    }

    @Override
    public void draw(Graphics g) {
        Point abs = getAbsolutePosition();
        g.setColor(PORT_COLOR);
        g.fillRect(abs.x - PORT_SIZE / 2, abs.y - PORT_SIZE / 2, PORT_SIZE, PORT_SIZE);
    }   
}
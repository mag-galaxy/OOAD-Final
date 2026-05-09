package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Composite extends BasicAbstract{
    private ArrayList<BasicAbstract> members = new ArrayList<>();

    public void addMember(BasicAbstract shape) {
        members.add(shape);
    }

    public ArrayList<BasicAbstract> getMembers() {
        return members;
    }

    @Override
    public void setX(int newX) {
        int dx = newX - this.getX();
        for (BasicAbstract s : members) {
            s.setX(s.getX() + dx);
        }
        super.setX(newX);
    }

    @Override
    public int getX(){
        return getBoundary().x;
    }

    @Override
    public void setY(int newY) {
        int dy = newY - this.getY();
        for (BasicAbstract s : members) {
            s.setY(s.getY() + dy);
        }
        super.setY(newY);
    }

    @Override
    public int getY(){
        return getBoundary().y;
    }

    @Override
    public int getWidth(){
        return getBoundary().width;
    }

    @Override
    public int getHeight(){
        return getBoundary().height;
    }

    @Override
    public void draw(Graphics g) {
        for (BasicAbstract s : members) {
            s.draw(g);
        }
        if (this.getIsSelected() || this.getIsHovered()) {
            drawBoundingBox(g);
        }
    }

    private void drawBoundingBox(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        Rectangle bound = getBoundary();
        float[] dash = {5.0f};
        g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
        g2d.setColor(Color.GRAY);
        g2d.drawRect(bound.x - 5, bound.y - 5, bound.width + 10, bound.height + 10);
        g2d.dispose();
    }

    private Rectangle getBoundary() {
        if(members.isEmpty()) {
            return new Rectangle();
        }
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (BasicAbstract s : members) {
            minX = Math.min(minX, s.getX());
            minY = Math.min(minY, s.getY());
            maxX = Math.max(maxX, s.getX() + s.getWidth());
            maxY = Math.max(maxY, s.getY() + s.getHeight());
        }
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    @Override
    public boolean isInside(Point p) {
        for (BasicAbstract s : members) {
            if (s.isInside(p)) return true;
        }
        return false;
    }  
}
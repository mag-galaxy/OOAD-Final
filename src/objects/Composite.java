package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Composite extends ShapeAbstract{
    private ArrayList<ShapeAbstract> members = new ArrayList<>();

    public void addMember(ShapeAbstract shape) {
        members.add(shape);
    }

    public ArrayList<ShapeAbstract> getMembers() {
        return members;
    }

    @Override
    public void setX(int newX) {
        int dx = newX - this.getX();
        for (ShapeAbstract s : members) {
            s.setX(s.getX() + dx);
        }
        super.setX(newX);
    }

    @Override
    public void setY(int newY) {
        int dy = newY - this.getY();
        for (ShapeAbstract s : members) {
            s.setY(s.getY() + dy);
        }
        super.setY(newY);
    }

    @Override
    public void draw(Graphics g) {
        for (ShapeAbstract s : members) {
            s.draw(g);
        }
        if (this.getIsSelected()) {
            drawGroupBoundingBox(g);
        }
    }

    private void drawGroupBoundingBox(Graphics g) {
        // 實作計算成員最大/最小 x, y 的邏輯
    }

    @Override
    public boolean isInside(Point p) {
        for (ShapeAbstract s : members) {
            if (s.isInside(p)) return true;
        }
        return false;
    }
}
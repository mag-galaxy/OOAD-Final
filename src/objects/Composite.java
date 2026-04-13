package objects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Composite extends ShapeAbstract{
    private List<ShapeAbstract> members = new ArrayList<>();

    public void addMember(ShapeAbstract shape) {
        members.add(shape);
    }

    public List<ShapeAbstract> getMembers() {
        return members;
    }

    @Override
    public void setX(int newX) {
        int dx = newX - this.getX(); // 計算這次動了多少
        for (ShapeAbstract s : members) {
            s.setX(s.getX() + dx);   // 讓所有成員跟著移動相同的距離
        }
        super.setX(newX); // 更新群組本身的基準點
    }

    @Override
    public void setY(int newY) {
        int dy = newY - this.getY(); // 計算這次動了多少
        for (ShapeAbstract s : members) {
            s.setX(s.getY() + dy);   // 讓所有成員跟著移動相同的距離
        }
        super.setX(newY); // 更新群組本身的基準點
    }

    @Override
    public void draw(Graphics g) {
        // 畫出所有成員，並在最外層畫出選取框（如果被選中的話）
        for (ShapeAbstract s : members) {
            s.draw(g);
        }
        if (this.getIsSelected()) {
            drawGroupBoundingBox(g);
        }
    }

    // 根據成員位置計算出群組的範圍，並畫出虛線框
    private void drawGroupBoundingBox(Graphics g) {
        // 實作計算成員最大/最小 x, y 的邏輯
    }

    @Override
    public boolean isInside(Point p) {
        // 只要點中任何一個成員，就算點中這個群組
        for (ShapeAbstract s : members) {
            if (s.isInside(p)) return true;
        }
        return false;
    }
}
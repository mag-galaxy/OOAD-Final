package objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Port extends ShapeAbstract{
    
    private BasicAbstract owner;
    private double ratioX; // 相對寬度比例 [0, 1]
    private double ratioY; // 相對高度比例 [0, 1]
    private final int PORT_SIZE = 8; // 黑點大小

    private ArrayList<LinkAbstract> startLinks = new ArrayList<>();
    private ArrayList<LinkAbstract> endLinks = new ArrayList<>();

    public Port(BasicAbstract owner, double rx, double ry) {
        this.owner = owner;
        this.ratioX = rx;
        this.ratioY = ry;
    }

    public BasicAbstract getOwner(){
        return this.owner;
    }

    // 新增線段紀錄
    public void addStartLink(LinkAbstract link) { startLinks.add(link); }
    public void addEndLink(LinkAbstract link) { endLinks.add(link); }

    // 取得絕對座標，供 Link 繪製使用
    public Point getAbsolutePosition() {
        int absX = (int) (owner.getX() + owner.getWidth() * ratioX);
        int absY = (int) (owner.getY() + owner.getHeight() * ratioY);
        return new Point(absX, absY);
    }

    // 判斷滑鼠是否點中此 Port (用於 Use Case B)
    public boolean isInside(Point p) {
        Point abs = getAbsolutePosition();
        return (p.x >= abs.x - PORT_SIZE && p.x <= abs.x + PORT_SIZE &&
                p.y >= abs.y - PORT_SIZE && p.y <= abs.y + PORT_SIZE);
    }

    public void draw(Graphics g) {
        Point abs = getAbsolutePosition();
        g.setColor(Color.BLACK);
        g.fillRect(abs.x - PORT_SIZE / 2, abs.y - PORT_SIZE / 2, PORT_SIZE, PORT_SIZE);
    }   
    
}

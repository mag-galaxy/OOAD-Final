package objects;

import java.util.ArrayList;
import java.awt.*;

public class Rect extends BasicAbstract {
    
    public Rect(int x, int y, int depth) {
        this.setDepth(depth);
        this.setX(x);
        this.setY(y);
        this.setWidth(100);
        this.setHeight(80);
        initializePort(); // 這裡會加入 8 個 port
    }

    public void initializePort() {
        this.ports = new ArrayList<>(8);
        // 四個角
        ports.add(new Port(this, 0.0, 0.0)); // 左上 (North-West)
        ports.add(new Port(this, 1.0, 0.0)); // 右上 (North-East)
        ports.add(new Port(this, 0.0, 1.0)); // 左下 (South-West)
        ports.add(new Port(this, 1.0, 1.0)); // 右下 (South-East)
        
        // 四邊中點
        ports.add(new Port(this, 0.5, 0.0)); // 上中 (North)
        ports.add(new Port(this, 0.5, 1.0)); // 下中 (South)
        ports.add(new Port(this, 0.0, 0.5)); // 左中 (West)
        ports.add(new Port(this, 1.0, 0.5)); // 右中 (East)
    }

    @Override
    public void draw(Graphics g) {
        // 1. 畫主體 (目前預設白色，可隨 Use Case G 調整顏色 [cite: 131])
        g.setColor(this.getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        
        // 2. 畫黑色外框
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getWidth(), getHeight());

        // 3. 畫標籤文字
        drawLabel(g);

        // 4. 處理選取狀態下的 ports [cite: 62]
        drawPorts(g);
    }
}

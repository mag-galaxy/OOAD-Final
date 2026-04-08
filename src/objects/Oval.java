package objects;

import java.util.ArrayList;
import java.awt.*;

public class Oval extends BasicAbstract {

    public Oval(int x, int y, int depth) {
        this.setDepth(depth);
        this.setX(x);
        this.setY(y);
        this.setWidth(100);
        this.setHeight(60);
        this.setIsSelected(false);
        initializePort();
    }

    public void initializePort() {
        this.ports = new ArrayList<>(4);
        // 四個頂點
        ports.add(new Port(this, 0.5, 0.0)); // 上 (North)
        ports.add(new Port(this, 0.5, 1.0)); // 下 (South)
        ports.add(new Port(this, 0.0, 0.5)); // 左 (West)
        ports.add(new Port(this, 1.0, 0.5)); // 右 (East)
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
        
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getWidth(), getHeight());

        drawLabel(g);
        drawPorts(g);
    }

}

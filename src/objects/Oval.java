package objects;

import java.util.ArrayList;
import java.awt.*;

public class Oval extends BasicAbstract {
    private final int PORT_AMOUNT = 4;
    private final int WIDTH = 100;
    private final int HEIGHT = 60;

    public Oval(int x, int y, int depth) {
        this.setDepth(depth);
        this.setX(x);
        this.setY(y);
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setIsSelected(false);
        initializePort();
    }

    public void initializePort() {
        this.ports = new ArrayList<>(PORT_AMOUNT);
        ports.add(new Port(this, 0.5, 0.0)); // 上
        ports.add(new Port(this, 0.5, 1.0)); // 下
        ports.add(new Port(this, 0.0, 0.5)); // 左
        ports.add(new Port(this, 1.0, 0.5)); // 右
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(getX(), getY(), getWidth(), getHeight());
        
        g.setColor(Color.BLACK);
        g.drawOval(getX(), getY(), getWidth(), getHeight());

        drawLabel(g);
        drawPorts(g);
    }
}
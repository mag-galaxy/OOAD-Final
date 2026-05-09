package objects;

import java.util.ArrayList;
import java.awt.*;

public class Oval extends BasicAbstract {
    private final int PORT_AMOUNT = 4;
    private final int WIDTH = 100;
    private final int HEIGHT = 80;
    private final double[] PORT_POS_X = {0.5, 1.0, 0.5, 0.0};
    private final double[] PORT_POS_Y = {0.0, 0.5, 1.0, 0.5};

    public Oval(int x, int y, int depth) {
        this.setDepth(depth);
        this.setX(x);
        this.setY(y);
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        initializePort();
    }

    public void initializePort() {
        this.ports = new ArrayList<>(PORT_AMOUNT);
        for (int i = 0; i < PORT_AMOUNT; ++i){
            ports.add(new Port(this, PORT_POS_X[i], PORT_POS_Y[i]));
        }
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
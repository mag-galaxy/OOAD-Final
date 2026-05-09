package objects;

import java.awt.*;

public class Generalization extends LinkAbstract {

    public Generalization(Port start, Port end) {
        super.createLink(start, end);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Point p1 = getStartPort().getAbsolutePosition();
        Point p2 = getEndPort().getAbsolutePosition();
        
        g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        drawArrow(g2, p1, p2);
    }

    private void drawArrow(Graphics2D g2, Point p1, Point p2) {
        double barb = 15;
        double phi = Math.toRadians(25);
        double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);

        Polygon triangle = new Polygon();
        triangle.addPoint(p2.x, p2.y);
        triangle.addPoint(
            (int) (p2.x - barb * Math.cos(theta + phi)),
            (int) (p2.y - barb * Math.sin(theta + phi))
        );
        triangle.addPoint(
            (int) (p2.x - barb * Math.cos(theta - phi)),
            (int) (p2.y - barb * Math.sin(theta - phi))
        );

        g2.setColor(Color.WHITE);
        g2.fillPolygon(triangle);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(triangle);
    }
}

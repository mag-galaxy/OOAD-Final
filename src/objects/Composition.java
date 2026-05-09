package objects;

import java.awt.*;

public class Composition extends LinkAbstract {

    public Composition(Port start, Port end) {
        super.constructLink(start, end);
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
        double size = 12;
        double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);

        Polygon diamond = new Polygon();
        diamond.addPoint(p1.x, p1.y);
        diamond.addPoint(
            (int) (p1.x + size * Math.cos(theta + Math.toRadians(30))),
            (int) (p1.y + size * Math.sin(theta + Math.toRadians(30)))
        );
        diamond.addPoint(
            (int) (p1.x + size * 2 * Math.cos(theta)),
            (int) (p1.y + size * 2 * Math.sin(theta))
        );
        diamond.addPoint(
            (int) (p1.x + size * Math.cos(theta - Math.toRadians(30))),
            (int) (p1.y + size * Math.sin(theta - Math.toRadians(30)))
        );

        g2.setColor(Color.WHITE); 
        g2.fillPolygon(diamond);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(diamond);
    }    
}

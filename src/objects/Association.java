package objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Association extends LinkAbstract{
    
    public Association(Port start, Port end) {
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
        double phi = Math.toRadians(30);
        int barb = 10;
        double dy = p2.y - p1.y;
        double dx = p2.x - p1.x;
        double theta = Math.atan2(dy, dx);
        
        int x, y;
        x = (int) (p2.x - barb * Math.cos(theta + phi));
        y = (int) (p2.y - barb * Math.sin(theta + phi));
        g2.drawLine(p2.x, p2.y, x, y);
        
        x = (int) (p2.x - barb * Math.cos(theta - phi));
        y = (int) (p2.y - barb * Math.sin(theta - phi));
        g2.drawLine(p2.x, p2.y, x, y);
    }
}
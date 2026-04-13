package objects;

import java.awt.Graphics;
import java.awt.Point;

public abstract class ShapeAbstract {

     // members
     private int depth = 0;

     // abstract methods, every subtype must override
     public abstract void draw(Graphics g);

     // non abstract methods
     public boolean isInside(Point p) { return false;}
     public void setIsSelected(boolean selected) {}
     public boolean getIsSelected() { return false;}
     public void setX(int x) {};
     public int getX() { return -1;}
     public void setY(int y) {};
     public int getY() { return -1;}

     public void setDepth(int d){
          this.depth = d;
     }

     public int getDepth(){
          return this.depth;
     }
}
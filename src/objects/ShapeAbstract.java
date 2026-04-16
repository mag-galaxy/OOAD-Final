package objects;

import java.awt.Graphics;

public abstract class ShapeAbstract {

     // members
     private int depth = 0;

     // abstract methods, every subtype must override
     public abstract void draw(Graphics g);

     public void setDepth(int d){
          this.depth = d;
     }

     public int getDepth(){
          return this.depth;
     }
}
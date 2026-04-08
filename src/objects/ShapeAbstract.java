package objects;

import java.awt.Graphics;

public abstract class ShapeAbstract {
     private int depth = 0;
     public abstract void draw(Graphics g);

     public void setDepth(int d){
          this.depth = d;
     }

     public int getDepth(){
          return this.depth;
     }
}
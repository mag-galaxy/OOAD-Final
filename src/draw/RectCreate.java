package draw;

import objects.BasicAbstract;
import objects.Rect;

public class RectCreate implements BasicDrawInterface {
    @Override
    public BasicAbstract createBasic(int x, int y, int depth){
        return new Rect(x, y, depth);
    }
}

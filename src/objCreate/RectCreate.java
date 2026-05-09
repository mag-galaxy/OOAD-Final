package objCreate;

import objects.BasicAbstract;
import objects.Rect;

public class RectCreate implements BasicCreateInterface {
    @Override
    public BasicAbstract createBasic(int x, int y, int depth){
        return new Rect(x, y, depth);
    }
}

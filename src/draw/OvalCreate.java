package draw;

import objects.BasicAbstract;
import objects.Oval;

public class OvalCreate implements BasicDrawInterface{
    @Override
    public BasicAbstract createBasic(int x, int y, int depth){
        return new Oval(x, y, depth);
    }
}

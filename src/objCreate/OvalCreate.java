package objCreate;

import objects.BasicAbstract;
import objects.Oval;

public class OvalCreate implements BasicCreateInterface{
    @Override
    public BasicAbstract createBasic(int x, int y, int depth){
        return new Oval(x, y, depth);
    }
}

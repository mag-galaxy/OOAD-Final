package draw;

import objects.LinkAbstract;
import objects.Port;

public interface LinkDrawInterface {
    public LinkAbstract createLink(Port p1, Port p2);    
}

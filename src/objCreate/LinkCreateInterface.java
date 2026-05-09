package objCreate;

import objects.LinkAbstract;
import objects.Port;

public interface LinkCreateInterface {
    public abstract LinkAbstract createLink(Port p1, Port p2);    
}

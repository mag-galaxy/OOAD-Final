package objCreate;

import objects.LinkAbstract;
import objects.Composition;
import objects.Port;

public class CompositionCreate implements LinkCreateInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Composition(p1, p2);
    }
}

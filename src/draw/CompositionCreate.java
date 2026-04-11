package draw;

import objects.LinkAbstract;
import objects.Port;
import objects.Composition;

public class CompositionCreate implements LinkDrawInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Composition(p1, p2);
    }
}

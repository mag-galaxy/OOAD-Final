package draw;

import objects.Generalization;
import objects.LinkAbstract;
import objects.Port;

public class GeneralizationCreate implements LinkDrawInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Generalization(p1, p2);
    }
}

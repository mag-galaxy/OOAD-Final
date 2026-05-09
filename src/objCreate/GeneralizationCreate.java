package objCreate;

import objects.LinkAbstract;
import objects.Generalization;
import objects.Port;

public class GeneralizationCreate implements LinkCreateInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Generalization(p1, p2);
    }
}

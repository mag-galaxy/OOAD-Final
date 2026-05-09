package objCreate;

import objects.LinkAbstract;
import objects.Association;
import objects.Port;

public class AssociationCreate implements LinkCreateInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Association(p1, p2);
    }    
}

package draw;

import objects.Association;
import objects.LinkAbstract;
import objects.Port;

public class AssociationCreate implements LinkDrawInterface {
    @Override
    public LinkAbstract createLink(Port p1, Port p2){
        return new Association(p1, p2);
    }    
}

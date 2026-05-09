package objects;

public abstract class LinkAbstract extends ShapeAbstract {
    private Port startPort;
    private Port endPort;

    public void createLink(Port start, Port end){
        this.startPort = start;
        this.endPort = end;
        start.addStartLink(this);
        end.addEndLink(this);
    }

    public void setStartPort(Port start){
        this.startPort = start;
    }

    public Port getStartPort(){
        return this.startPort;
    }

    public void setEndPort(Port end){
        this.endPort = end;
    }

    public Port getEndPort(){
        return this.endPort;
    }
}

package mouseState;

import java.awt.event.MouseEvent;

import gui.Canvas;
import objCreate.LinkCreateInterface;
import objects.LinkAbstract;
import objects.Port;

public class DrawLinkState implements MouseStateInterface {
    private Port startPort;
    private Port endPort;
    private LinkCreateInterface linkStrategy;

    public DrawLinkState(LinkCreateInterface strategy){
        this.linkStrategy = strategy;
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {
        startPort = canvas.findPortAt(e.getPoint());
    }

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {}

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        endPort = canvas.findPortAt(e.getPoint());
        
        if (isValidEndPort(endPort, startPort)) {
            LinkAbstract newLink = linkStrategy.createLink(startPort, endPort);
            canvas.addLink(newLink);
        }
    }

    private boolean isValidEndPort(Port end, Port start) {
        return (end != null && end.getOwner() != start.getOwner());
    }
}

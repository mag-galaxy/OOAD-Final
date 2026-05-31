package mode;

import java.awt.event.MouseEvent;

import gui.Canvas;
import mouseState.DrawLinkState;
import mouseState.MouseStateInterface;
import objCreate.LinkCreateInterface;

public class DrawLinkMode implements ModeInterface{
    MouseStateInterface state;

    public DrawLinkMode(LinkCreateInterface strategy){
        state = new DrawLinkState(strategy);
    }

    @Override
    public void onPressed(MouseEvent e, Canvas canvas) {
        state.onPressed(e, canvas);
    }

    @Override
    public void onDragged(MouseEvent e, Canvas canvas) {
        state.onDragged(e, canvas);
    }

    @Override
    public void onReleased(MouseEvent e, Canvas canvas) {
        state.onReleased(e, canvas);
    }

    @Override
    public void onHovered(MouseEvent e, Canvas canvas) {}
}

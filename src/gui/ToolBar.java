package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import buttons.*;
import mode.SelectMode;

public class ToolBar extends JPanel{
    public ButtonAbstract activeModeButton;
    private List<ButtonAbstract> allButtons;
    private Canvas canvas;
    private final int ROWS = 6;
    private final int COLUMNS = 1;
    private final int GAP = 5;
    private final Color BG_COLOR = Color.LIGHT_GRAY;
    private final int SELECT_INX = 0;

    public ToolBar(Canvas canvas) {
        this.canvas = canvas;
        this.setLayout(new GridLayout(ROWS, COLUMNS, GAP, GAP));
        this.setBackground(BG_COLOR);
        initializeButtons();
    }

    private void initializeButtons() {
        allButtons = new ArrayList<>();
        allButtons.add(new SelectButton(canvas, this));
        allButtons.add(new AssociationLinkButton(canvas, this));
        allButtons.add(new GeneralizationLinkButton(canvas, this));
        allButtons.add(new CompositionLinkButton(canvas, this));
        allButtons.add(new RectButton(canvas, this));
        allButtons.add(new OvalButton(canvas, this));

        for (ButtonAbstract btn : allButtons) {
            this.add(btn);
        }
        setActiveBtn(allButtons.get(SELECT_INX));
        canvas.setMode(new SelectMode());
    }

    public void resetButtonColor() {
        for (ButtonAbstract btn : allButtons) {
            btn.setUnselectedStyle();
        }
    }

    public void setActiveBtn(ButtonAbstract activeBtn){
        resetButtonColor();
        activeBtn.setSelectedStyle();
        this.activeModeButton = activeBtn;
    }
}

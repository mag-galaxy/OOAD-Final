package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import buttons.*;
import mode.SelectMode;

public class ToolBar extends JPanel{

    private static ToolBar instance;

    public ButtonAbstract activeModeButton;
    private List<ButtonAbstract> allButtons;
    private Canvas canvas;
    private final int ROWS = 6;
    private final int COLUMNS = 1;
    private final int GAP = 5;
    private final Color BG_COLOR = Color.LIGHT_GRAY;
    private final int SELECT_INX = 0;

    private ToolBar() {
        this.canvas = Canvas.getInstance();
        this.setLayout(new GridLayout(ROWS, COLUMNS, GAP, GAP));
        this.setBackground(BG_COLOR);
        initializeButtons();
    }

    public static ToolBar getInstance(){
        if (instance == null) instance = new ToolBar();
        return instance;
    }

    private void initializeButtons() {
        allButtons = new ArrayList<>();
        allButtons.add(new SelectButton(this));
        allButtons.add(new AssociationLinkButton(this));
        allButtons.add(new GeneralizationLinkButton(this));
        allButtons.add(new CompositionLinkButton(this));
        allButtons.add(new RectButton(this));
        allButtons.add(new OvalButton(this));

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

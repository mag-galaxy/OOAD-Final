package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

import buttons.AssociationLinkButton;
import buttons.ButtonAbstract;
import buttons.CompositionLinkButton;
import buttons.GeneralizationLinkButton;
import buttons.OvalButton;
import buttons.RectButton;
import buttons.SelectButton;

public class ToolBar extends JPanel{
    private List<ButtonAbstract> allButtons = new ArrayList<>();
    private Canvas canvas;
    public ButtonAbstract activeModeButton;
    private final int SELECT_INX = 0;

    public ToolBar(Canvas canvas) {
        this.canvas = canvas;
        this.setLayout(new GridLayout(6, 1, 5, 5));
        this.setBackground(Color.LIGHT_GRAY);
        initializeButtons();
        activeModeButton = allButtons.get(0);
    }

    private void initializeButtons() {
        allButtons.add(new SelectButton(canvas, this));
        allButtons.add(new AssociationLinkButton(canvas, this));
        allButtons.add(new GeneralizationLinkButton(canvas, this));
        allButtons.add(new CompositionLinkButton(canvas, this));
        allButtons.add(new RectButton(canvas, this));
        allButtons.add(new OvalButton(canvas, this));

        for (ButtonAbstract btn : allButtons) {
            this.add(btn);
        }
        selectDefaultButton();
    }

    public void resetButtonColor() {
        for (ButtonAbstract btn : allButtons) {
            btn.setUnselectedStyle();
        }
    }

    public void selectDefaultButton() {
        resetButtonColor();
        allButtons.get(SELECT_INX).setSelectedStyle(); 
    }

    public void setActiveBtn(ButtonAbstract activeBtn){
        resetButtonColor();
        activeBtn.setSelectedStyle();
        this.activeModeButton = activeBtn;
    }
}

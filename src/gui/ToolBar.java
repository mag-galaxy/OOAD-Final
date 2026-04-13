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

    public ToolBar(Canvas canvas) {
        this.canvas = canvas;
        this.setLayout(new GridLayout(6, 1, 5, 5));
        this.setBackground(Color.LIGHT_GRAY); // 稍微區隔背景色

        initializeButtons();
    }

    private void initializeButtons() {
        // 1. 建立按鈕物件，並將 Canvas 引用傳入
        allButtons.add(new SelectButton(canvas, this));
        allButtons.add(new AssociationLinkButton(canvas, this));
        allButtons.add(new GeneralizationLinkButton(canvas, this));
        allButtons.add(new CompositionLinkButton(canvas, this));
        allButtons.add(new RectButton(canvas, this));
        allButtons.add(new OvalButton(canvas, this));

        // 2. 將按鈕加入面板，並預設 Select 按鈕為選中狀態
        for (ButtonAbstract btn : allButtons) {
            this.add(btn);
        }
        
        // 程式啟動時，預設第一個（Select）變色
        selectDefaultButton();
    }

    // 這就是按鈕會呼叫的 Panel 功能
    public void resetButtonColor() {
        for (ButtonAbstract btn : allButtons) {
            btn.setUnselectedStyle(); // 全部變回白底黑字
        }
    }

    // 讓 Canvas 操作完後，自動點亮 Select 按鈕
    public void selectDefaultButton() {
        resetButtonColor();
        allButtons.get(0).setSelectedStyle(); 
    }
}
